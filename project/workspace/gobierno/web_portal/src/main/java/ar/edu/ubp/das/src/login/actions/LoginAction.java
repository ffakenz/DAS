package ar.edu.ubp.das.src.login.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.login.LoginInteractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class LoginAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
																			HttpServletResponse response) throws SQLException, RuntimeException {

		Interactor action = new LoginInteractor();
		InteractorResponse result = action.execute(form).apply(DaoFactory::getDao);

		Long logInId = ((Optional<Long>)result.getResult()).orElse(Long.MIN_VALUE);

		HttpSession session = request.getSession();
		session.setAttribute("LogInId", logInId);

		return mapping.getForwardByName(result.getResponse().getForward());
	}

}
