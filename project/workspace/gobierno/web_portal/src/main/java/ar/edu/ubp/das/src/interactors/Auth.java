package ar.edu.ubp.das.src.interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.boundries.login.LogIn;
import ar.edu.ubp.das.src.boundries.login.LogInReq;
import ar.edu.ubp.das.src.boundries.login.LogInResp;
import ar.edu.ubp.das.src.login.forms.UserForm;

import java.sql.SQLException;
import java.util.List;

public class Auth implements LogIn {

    private Dao usuariosDao;

    public Auth(Dao usuariosDao) {
        this.usuariosDao = usuariosDao;
    }

    @Override
    public LogInResp logIn(LogInReq req) {

        Boolean respuesta = false;

        try {

            List<DynaActionForm> usuarios = usuariosDao.select(null);

            respuesta =
                    usuarios.stream().anyMatch( usr -> {
                        return ((UserForm) usr).getNombre().equals(req.getUsuario()) &&
                                ((UserForm) usr).getPassword().equals(req.getClave());
                    });



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new LogInResp(respuesta ? "c" : "e");
    }
}
