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
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.InteractorResponse;
import ar.edu.ubp.das.src.login.boundaries.*;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.interactors.LoginInteractor;

public class LoginAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
																			HttpServletResponse response) throws SQLException, RuntimeException {


		LoginInteractor action = new LoginInteractor();
		InteractorResponse result = action.execute(mapping, form).apply(DaoFactory::getDao);

		Long logInId = ((Optional<Long>)result.getResult()).orElse(Long.MIN_VALUE);

		HttpSession session = request.getSession();
		session.setAttribute("LogInId", logInId);

		return result.getForwardConfig();
	}

}
