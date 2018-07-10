package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSConcesionariasDaoMock implements Dao {
    public List<ConcesionariaForm> concesionarias;

    public MSConcesionariasDaoMock() {
        concesionarias = new ArrayList<>();

        final ConcesionariaForm c1 = new ConcesionariaForm();
        c1.setNombre("C1");
        c1.setConfig("REST");
        c1.setCuit("CUIT1");
        c1.setId(Long.valueOf(1));
        c1.setFechaRegistracion(Timestamp.from(Instant.now()));
        c1.setFechaAlta(Timestamp.from(Instant.now()));
        c1.setCodigo("CODIGO SECRETO: C1");
        final ConcesionariaForm c2 = new ConcesionariaForm();
        c2.setNombre("C2");
        c2.setConfig("CXF");
        c2.setCuit("CUIT2");
        c2.setId(Long.valueOf(2));
        c2.setFechaRegistracion(Timestamp.from(Instant.now()));
        c2.setFechaAlta(Timestamp.from(Instant.now()));
        c2.setCodigo("CODIGO SECRETO: C2");
        final ConcesionariaForm c3 = new ConcesionariaForm();
        c3.setNombre("C3");
        c3.setConfig("AXIS");
        c3.setCuit("21-93337511-1");
        c3.setId(Long.valueOf(3));
        c3.setFechaRegistracion(Timestamp.from(Instant.now()));
        concesionarias.addAll(Arrays.asList(c1, c2, c3));
    }

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        final Optional<Long> max =
                concesionarias.stream()
                        .map(c -> c.getId())
                        .max(Comparable::compareTo);

        ((ConcesionariaForm) form).setId(max.orElse(Long.valueOf(0)) + 1);
        ((ConcesionariaForm) form).setFechaRegistracion(Timestamp.from(Instant.now()));
        concesionarias.add((ConcesionariaForm) form);
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        concesionarias.stream().filter(c -> {
            ConcesionariaForm formConc = (ConcesionariaForm) form;
            return c.getId() == formConc.getId();
        }).findFirst().ifPresent(c -> {
            concesionarias.remove(c);
            concesionarias.add((ConcesionariaForm) form);
        });
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        return concesionarias.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}
