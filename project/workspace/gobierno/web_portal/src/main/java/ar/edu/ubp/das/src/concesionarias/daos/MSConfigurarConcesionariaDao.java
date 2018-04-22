package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import concesionarias.forms.ConcesionariaForm;
import concesionarias.forms.ConfigParamForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MSConfigurarConcesionariaDao extends DaoImpl{
    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(result.getLong("concesionaria_id"));
        configParam.setConfigTecno(result.getString("config_tecno"));
        configParam.setConfigParam(result.getString("config_param"));
        configParam.setValue(result.getString("value"));
        return configParam;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure( "dbo.log_concesionaria_config_params(?, ?, ?, ?)" );
        this.setParameter( 1, ((ConfigParamForm) form).getConcesionariaId());
        this.setParameter( 2, ((ConfigParamForm) form).getConfigTecno());
        this.setParameter( 3, ((ConfigParamForm) form).getConfigParam());
        this.setParameter( 4, ((ConfigParamForm) form).getValue());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {

    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionaria_config_params", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        List<DynaActionForm> concesionarias  = this.executeQuery();
        this.disconnect();
        return concesionarias;
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
