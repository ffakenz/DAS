package ar.edu.ubp.das.src.login.actions;

import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

abstract class AValidarUsuario implements Action {

	abstract String getTipo();

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {

		MSUsuariosDao dao = (MSUsuariosDao) DaoFactory.getDao("Usuarios", "login");
		MSLogInDao logInDao = (MSLogInDao) DaoFactory.getDao("LogIn", "login");

		Optional<ForwardConfig> respuesta =
			form.getItem( "username").flatMap( u -> {
				return form.getItem( "password").map( p -> {
					UsuarioForm usr = new UsuarioForm();
					usr.setTipo(getTipo());
					usr.setUsername(u);
					usr.setPassword(p);
					ValidarUsuario auth = new ValidarUsuarioImpl();
					Boolean isUserValid = auth.validarUsuario(usr).apply(dao);

					if(isUserValid) {
						LogInForm logInForm = new LogInForm();
						logInForm.setTipo(getTipo());
						logInForm.setUsername(u);
						LogIn logger = new LogInImpl();
						logger.login(logInForm).accept(logInDao);

						return mapping.getForwardByName( "success" ); // LogIn Successfully
					} else {
						return mapping.getForwardByName( "failure" ); // LogIn Failed
					}
				});
			});

		return respuesta.orElse(mapping.getForwardByName( "error" )); // Some error occur with username / password
	}

}
