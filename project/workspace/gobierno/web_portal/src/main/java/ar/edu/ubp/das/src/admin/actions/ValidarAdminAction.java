package ar.edu.ubp.das.src.admin.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.admin.daos.MSAdministradoresDao;

public class ValidarAdminAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		
		if( form.getItem( "usuario" ) != null &&
		    form.getItem( "clave" ) != null ) {
	
			// Enviamos los datos para validacion

			//MSAdministradoresDao dao = (MSAdministradoresDao)DaoFactory.getDao( "Administradores", "admin" );
			//     				 dao.validar_admin( form );

			form.setItem("respuesta", "c"); // debuggin purpouses
			request.setAttribute("respuesta", form.getItem("respuesta"));
			
			if( form.getItem( "respuesta" ).equals( "c" ) ) {
				
				// Nombre de usuario y contrase�a correcta
				// Almacenamos el nombre de usuario en la sesi�n
				
				HttpSession session = request.getSession();
				
				session.setAttribute( "usuario",  form.getItem( "usuario" ) );
			}
			
			
			return mapping.getForwardByName( "success" );
		}
		else {
			
			return  mapping.getForwardByName( "failure" );
		}
	}

}
