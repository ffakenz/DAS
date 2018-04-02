package ar.edu.ubp.das.src.admin.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;

public class MSAdministradoresDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException { 
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
		return true;
	}
	
	public void validar_admin(DynaActionForm form) throws SQLException {

		this.connect();
		
		/**
		 * Aqui recibimos en el form el usuario y la contraseña
		 * y validamos el login en la base de datos utilizando
		 * el procedimiento almacenado validar_admin(?,?,?)
		 */
		
		this.setProcedure( "dbo.validar_admin(?,?,?)" );
		
		this.setParameter( 1, form.getItem( "usuario" ) );
		this.setParameter( 2, form.getItem( "clave" ) );
		this.setOutParameter( 3, Types.CHAR );
		
		this.getStatement().execute();
		 
		form.setItem("respuesta", this.getStatement().getString(3));
		
		this.disconnect();
	}
}
