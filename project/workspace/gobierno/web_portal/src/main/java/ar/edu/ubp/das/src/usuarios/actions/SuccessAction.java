package ar.edu.ubp.das.src.usuarios.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessAction implements Action {

	private static final Logger LOGGER = LoggerFactory.getLogger(SuccessAction.class);


	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws RuntimeException {


		return mapping.getForwardByName( "success" );
	}
}
