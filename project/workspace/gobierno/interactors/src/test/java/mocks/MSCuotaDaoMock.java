package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import estado_cuentas.forms.CuotaForm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSCuotaDaoMock implements Dao {

    public List<CuotaForm> cuotas;

    public MSCuotaDaoMock() {
        cuotas = new ArrayList<>();
    }

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        // cast form
        CuotaForm cuota = (CuotaForm) form;
        Long ecId = cuota.getEstadoCuentaId();
        // get max id from dbMock
        Optional<Long> max =
                cuotas.stream()
                        .filter( c -> c.getEstadoCuentaId() == ecId)
                        .map(c -> c.getId())
                        .max(Long::compareTo);

        // set id to next and default table values
        cuota.setId(max.orElse(Long.valueOf(0)) + 1);
        cuotas.add(cuota);
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {

    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        return cuotas.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
