package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MSUsuariosDaoMock extends MSUsuariosDao {
    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("ffakenz");
        userMock.setPassword("123");
        userMock.setRol("gobierno");
        List<DynaActionForm> usuarios  = Arrays.asList(userMock);
        return usuarios;
    }
}