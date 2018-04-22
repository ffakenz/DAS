package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class MSConcesionariaDaoMock implements Dao {
    public List<ConcesionariaForm> concesionarias;

    public MSConcesionariaDaoMock() {
        concesionarias = new ArrayList<>();

        ConcesionariaForm c1 = new ConcesionariaForm();
        c1.setNombre("C1");
        c1.setConfig("REST");
        c1.setId(Long.valueOf(1));
        c1.setFechaRegistracion(new Date(System.currentTimeMillis()));
        c1.setFechaAlta(new Date(System.currentTimeMillis()));
        c1.setCodigo("CODIGO SECRETO: C1");
        ConcesionariaForm c2 = new ConcesionariaForm();
        c2.setNombre("C2");
        c2.setConfig("CXF");
        c2.setId(Long.valueOf(2));
        c2.setFechaRegistracion(new Date(System.currentTimeMillis()));
        c2.setFechaAlta(new Date(System.currentTimeMillis()));
        c2.setCodigo("CODIGO SECRETO: C2");
        ConcesionariaForm c3 = new ConcesionariaForm();
        c3.setNombre("C3");
        c3.setConfig("AXIS");
        c3.setId(Long.valueOf(3));
        c3.setFechaRegistracion(new Date(System.currentTimeMillis()));
        c3.setFechaAlta(new Date(System.currentTimeMillis()));
        c3.setCodigo("CODIGO SECRETO: C3");
        concesionarias.addAll(Arrays.asList(c1, c2, c3));
    }

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        Optional<Long> max =
                concesionarias.stream()
                        .map( c -> c.getId())
                        .max(Comparable::compareTo);

        ((ConcesionariaForm) form).setId(max.orElse(Long.valueOf(0)) + 1);
        ((ConcesionariaForm) form).setFechaRegistracion(new Date(System.currentTimeMillis()));
        concesionarias.add((ConcesionariaForm)form);
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {
        concesionarias.stream().filter( c -> {
            ConcesionariaForm formConc = (ConcesionariaForm) form;
            return c.getId() == formConc.getId();
        }).findFirst().ifPresent( c -> {
            concesionarias.remove(c);
            concesionarias.add((ConcesionariaForm) form);
        });
    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        return concesionarias.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
