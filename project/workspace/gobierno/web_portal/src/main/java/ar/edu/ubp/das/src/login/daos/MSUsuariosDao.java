package ar.edu.ubp.das.src.login.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.UserForm;

public class MSUsuariosDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException { 
		// TODO Auto-generated method stub
		UserForm user = new UserForm();
		user.setNombre(result.getString("nombre"));
		user.setPassword(result.getString("password"));
		return user;
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

		this.connect();

		this.setProcedure("dbo.get_usuarios", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		List<DynaActionForm> usuarios  = this.executeQuery();

		this.disconnect();

		return usuarios;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		
		return true;
	}
}
