package clientes.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import clientes.forms.ClienteForm;
import concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Utils {

    default Function<Dao, Boolean> testClienteRegistrado(ClienteForm form) {
        return dao -> {
            return exists(cl ->
                        cl.getDocumento().equals(form.getDocumento()) &&
                        cl.getConcesionaria().equals(form.getConcesionaria()))
                    .apply(dao);
        };
    }


    default Function<Dao, Boolean> exists(Predicate<ClienteForm> predicate) {
        return dao -> {
            try {
                return dao.select(null).stream().anyMatch( c -> {
                    ClienteForm cli = (ClienteForm) c;
                    return predicate.test(cli);
                });
            } catch(SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }
}
