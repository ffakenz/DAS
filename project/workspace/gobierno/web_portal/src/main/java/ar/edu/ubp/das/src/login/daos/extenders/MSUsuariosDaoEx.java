package ar.edu.ubp.das.src.login.daos.extenders;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.DaoExtender;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSUsuariosDaoEx extends DaoExtender<UsuarioForm> {
    public MSUsuariosDaoEx(final DaoImpl dao) {
        super(dao);
    }

    public List<UsuarioForm> selectByUserNameAndPassword(final UsuarioForm form) throws SQLException {
        dao.connect();
        dao.setProcedure(
                "dbo.get_usuarios_by_username_password(?, ?)",
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        dao.setParameter(1, form.getUsername());
        dao.setParameter(2, form.getPassword());
        final List<UsuarioForm> usuarios = dao.executeQuery();
        dao.disconnect();
        return usuarios;
    }
}
