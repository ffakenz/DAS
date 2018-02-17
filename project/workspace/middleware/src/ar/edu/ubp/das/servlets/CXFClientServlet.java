package ar.edu.ubp.das.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.ubp.edu.das.ws.ConcesionariaCXFOne;
import ar.ubp.edu.das.ws.ConcesionariaCXFOneService;
import ar.ubp.edu.das.ws.PlanBean;

/**
 * Servlet implementation class CXFClientServlet
 */
@WebServlet("/CXFClientServlet")
public class CXFClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CXFClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ConcesionariaCXFOneService service = new ConcesionariaCXFOneService();
		ConcesionariaCXFOne concesionaria = service.getConcesionariaCXFOnePort();
		PlanBean plan = concesionaria.consultarPlan(3L);
		
		StringBuilder str = new StringBuilder();
		str.append("PlanID: " + plan.getId().toString());
		str.append("CuotasPagas: " + plan.getCuotasPagadas().toString());
		str.append("FechaAlta: " + plan.getFechaAlta().toString());
		str.append("ClientId: " + plan.getClientId());
		
		response.getWriter().println(str);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
