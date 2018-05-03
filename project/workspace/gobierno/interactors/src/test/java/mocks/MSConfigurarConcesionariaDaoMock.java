package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import concesionarias.forms.ConfigParamForm;
import login.forms.LogInForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MSConfigurarConcesionariaDaoMock implements Dao {

    public List<ConfigParamForm> configParams = new ArrayList<>();

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        configParams.add((ConfigParamForm)form);
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {

    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        return configParams.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
