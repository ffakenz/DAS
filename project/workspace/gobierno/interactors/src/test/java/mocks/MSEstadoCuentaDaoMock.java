package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;
import estado_cuentas.forms.EstadoCuentaForm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSEstadoCuentaDaoMock implements Dao {

    public List<EstadoCuentaForm> estadoCuentas;

    public MSEstadoCuentaDaoMock() {
        estadoCuentas = new ArrayList<>();
    }

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        // get max id from dbMock
        Optional<Long> max =
                estadoCuentas.stream()
                        .map( c -> c.getId())
                        .max(Long::compareTo);
        // cast form
        EstadoCuentaForm estadoCuenta = (EstadoCuentaForm) form;
        // set id to next and default table values
        estadoCuenta.setId(max.orElse(Long.valueOf(0)) + 1);
        estadoCuenta.setFechaAltaSistema(Timestamp.from(Instant.now()));
        estadoCuenta.setFechaUltimaActualizacion(Timestamp.from(Instant.now()));
        estadoCuentas.add(estadoCuenta);
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {
        estadoCuentas.stream().filter( c -> {
            EstadoCuentaForm formConc = (EstadoCuentaForm) form;
            return
                    c.getConcesionariaId() == formConc.getConcesionariaId() &&
                    c.getNroPlanConcesionaria() == formConc.getNroPlanConcesionaria();
        }).findFirst().ifPresent( c -> {
            estadoCuentas.remove(c);
            estadoCuentas.add((EstadoCuentaForm) form);
        });
    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        return estadoCuentas.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
