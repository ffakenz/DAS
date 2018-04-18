package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.login.boundaries.*;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import ar.edu.ubp.das.src.login.interactors.LogInImpl;
import ar.edu.ubp.das.src.login.interactors.ValidarUsuarioImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;

class ValidarUsuarioAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {

		MSUsuariosDao dao = (MSUsuariosDao) DaoFactory.getDao("Usuarios", "login");
		MSLogInDao logInDao = (MSLogInDao) DaoFactory.getDao("LogIn", "login");

		Optional<ForwardConfig> respuesta =
			form.getItem( "username").flatMap( u -> {
				return form.getItem( "password").map( p -> {
					UsuarioForm usr = new UsuarioForm();
					usr.setUsername(u);
					usr.setPassword(p);
					ValidarUsuario auth = new ValidarUsuarioImpl();
					Boolean isUserValid = auth.validarUsuario(usr).apply(dao);

					if(isUserValid) {
						LogInForm logInForm = new LogInForm();
						logInForm.setUsername(u);
						LogIn logger = new LogInImpl();
						Optional<Long> LogInId = logger.login(logInForm).apply(logInDao);

						HttpSession session = request.getSession();
						session.setAttribute("LogInId", LogInId.orElse(Long.MIN_VALUE));

						return mapping.getForwardByName( "success" ); // LogIn Successfully
					} else {
						return mapping.getForwardByName( "failure" ); // LogIn Failed
					}
				});
			});

		return respuesta.orElse(mapping.getForwardByName( "warning" )); // Some error occur with username / password
	}

}
