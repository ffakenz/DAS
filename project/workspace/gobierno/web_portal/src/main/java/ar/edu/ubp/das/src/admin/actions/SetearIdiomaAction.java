package ar.edu.ubp.das.src.admin.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetearIdiomaAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws RuntimeException {
		// TODO Auto-generated method stub
		
		request.setAttribute("idioma", form.getItem("idioma").orElseGet(()->"FAILURE"));
		
		return mapping.getForwardByName("success");
	}
}
