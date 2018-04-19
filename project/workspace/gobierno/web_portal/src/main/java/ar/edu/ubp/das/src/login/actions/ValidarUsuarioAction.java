package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.login.boundaries.*;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.interactors.LoginInteractor;

public class ValidarUsuarioAction implements Action {

	@Override
	public Function<BiFunction<String, String, Dao>, ForwardConfig> execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
																			HttpServletResponse response) throws SQLException, RuntimeException {


		return (daoFactory) -> {
			MSUsuariosDao dao = (MSUsuariosDao) daoFactory.apply("Usuarios", "login");
			MSLogInDao logInDao = (MSLogInDao) daoFactory.apply("LogIn", "login");

			Optional<ForwardConfig> respuesta =
					form.getItem( "username").flatMap( u -> {
						return form.getItem( "password").map( p -> {
							UsuarioForm usr = new UsuarioForm();
							usr.setUsername(u);
							usr.setPassword(p);
							LoginInteractor auth = new LoginInteractor();
							Boolean isUserValid = auth.validarUsuario(usr).apply(dao);

							if(isUserValid) {
								LogInForm logInForm = new LogInForm();
								logInForm.setUsername(u);
								Optional<Long> LogInId = auth.login(logInForm).apply(logInDao);

								HttpSession session = request.getSession();
								session.setAttribute("LogInId", LogInId.orElse(Long.MIN_VALUE));

								return mapping.getForwardByName( "success" ); // LogIn Successfully
							} else {
								return mapping.getForwardByName( "failure" ); // LogIn Failed
							}
						});
					});

			return respuesta.orElse(mapping.getForwardByName( "warning" )); // Some error occur with username / password
		};
	}

}
