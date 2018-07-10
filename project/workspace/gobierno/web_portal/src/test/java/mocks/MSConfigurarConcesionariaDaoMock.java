package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MSConfigurarConcesionariaDaoMock implements Dao {

    public List<ConfigParamForm> configParams = new ArrayList<>();

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        configParams.add((ConfigParamForm) form);
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {

    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        return configParams.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}
