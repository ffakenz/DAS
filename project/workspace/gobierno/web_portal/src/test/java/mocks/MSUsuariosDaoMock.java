package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MSUsuariosDaoMock implements Dao {

    List<DynaActionForm> usuarios;

    public MSUsuariosDaoMock() {
        usuarios = new ArrayList<>();

        final UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("pepe");
        userMock.setPassword("123");
        userMock.setRol("gobierno");
        usuarios.add(userMock);
    }

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {

    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {

    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {


        return usuarios;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}