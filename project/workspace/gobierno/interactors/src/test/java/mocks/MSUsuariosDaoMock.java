package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import login.forms.UsuarioForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MSUsuariosDaoMock implements Dao {

    List<DynaActionForm> usuarios;

    public MSUsuariosDaoMock() {
        usuarios  = new ArrayList<>();

        UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("pepe");
        userMock.setPassword("123");
        userMock.setRol("gobierno");
        usuarios.add(userMock);
    }

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {

    }

    @Override
    public void update(DynaActionForm form) throws SQLException {

    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {


        return usuarios;
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}