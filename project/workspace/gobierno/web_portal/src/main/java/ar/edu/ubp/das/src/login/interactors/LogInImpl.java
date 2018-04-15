package ar.edu.ubp.das.src.login.interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.boundaries.LogInReq;
import ar.edu.ubp.das.src.login.boundaries.LogInResp;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UserForm;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class LogInImpl implements LogIn {

    @Override
    public Function<MSUsuariosDao, LogInResp> logIn(LogInReq req) {
        return (msUsuariosDao) -> {
            Boolean respuesta = false;

            try {
                List<DynaActionForm> usuarios = msUsuariosDao .select(null);
                respuesta =
                        usuarios.stream().anyMatch( usr -> {
                            return ((UserForm) usr).getNombre().equals(req.getUsuario()) &&
                                    ((UserForm) usr).getPassword().equals(req.getClave());
                        });
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // TODO: insert into Login table
            return new LogInResp(respuesta ? "c" : "e");
        };
    }
}
