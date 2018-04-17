package ar.edu.ubp.das.src.login.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

public class MSUsuariosDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		UsuarioForm user = new UsuarioForm();
		user.setTipo(result.getString("tipo"));
		user.setUsername(result.getString("username"));
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
