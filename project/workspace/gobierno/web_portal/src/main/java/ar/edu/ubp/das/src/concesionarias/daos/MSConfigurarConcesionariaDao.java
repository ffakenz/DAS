package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConfigurarConcesionariaDao extends DaoImpl<ConfigParamForm> {
    @Override
    public ConfigParamForm make(final ResultSet result) throws SQLException {
        final ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(result.getLong("concesionaria_id"));
        configParam.setConfigTecno(result.getString("config_tecno"));
        configParam.setConfigParam(result.getString("config_param"));
        configParam.setValue(result.getString("value"));
        return configParam;
    }

    @Override
    public void insert(final ConfigParamForm f) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_concesionaria_config_params(?, ?, ?, ?)");
        this.setParameter(1, f.getConcesionariaId());
        this.setParameter(2, f.getConfigTecno());
        this.setParameter(3, f.getConfigParam());
        this.setParameter(4, f.getValue());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final ConfigParamForm form) throws SQLException {

    }

    @Override
    public void delete(final ConfigParamForm form) throws SQLException {

    }

    @Override
    public List<ConfigParamForm> select(final ConfigParamForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionaria_config_params", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<ConfigParamForm> concesionarias = this.executeQuery();
        this.disconnect();
        return concesionarias;
    }

    @Override
    public boolean valid(final ConfigParamForm form) throws SQLException {
        return false;
    }
}
