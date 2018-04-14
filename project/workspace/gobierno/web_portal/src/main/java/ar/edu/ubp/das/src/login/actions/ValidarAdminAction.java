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
import ar.edu.ubp.das.src.boundries.login.LogIn;
import ar.edu.ubp.das.src.interactors.Auth;
import ar.edu.ubp.das.src.boundries.login.LogInReq;
import ar.edu.ubp.das.src.boundries.login.LogInResp;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;

public class ValidarAdminAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {

		MSUsuariosDao dao = (MSUsuariosDao) DaoFactory.getDao("Usuarios", "login");

		Optional<ForwardConfig> respuesta =
				form.getItem( "usuario").flatMap( u -> {
					return form.getItem( "clave").map( c -> {
						LogInReq req = new LogInReq(u, c);

						// CREA UN INTERACTOR
						LogIn auth = new Auth(dao);

						// EJECUTA EL INTERACTOR Y OBTIENE RESP
						LogInResp resp = auth.logIn(req);

						// form.setItem("respuesta", resp.getResult()); ??
						request.setAttribute("respuesta", resp.getResult());

						return mapping.getForwardByName( "success" );
					});
				});

		return respuesta.orElseGet(() -> mapping.getForwardByName( "failure" ));
	}

}
