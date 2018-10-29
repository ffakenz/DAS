package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConfigurarConcesionariaDao extends DaoImpl<ConfigurarConcesionariaForm> {

    public MSConfigurarConcesionariaDao() {

        super(ConfigurarConcesionariaForm.class);
    }

    @Override
    public void insert(final ConfigurarConcesionariaForm form) throws SQLException {

        this.executeProcedure("dbo.log_concesionaria_config_params(?, ?, ?, ?)", form,
                "concesionariaId", "configTecno", "configParam", "value");
    }

    @Override
    public void update(final ConfigurarConcesionariaForm form) throws SQLException {
    }


    @Override
    public void delete(final ConfigurarConcesionariaForm form) throws SQLException {

    }

    @Override
    public List<ConfigurarConcesionariaForm> select(final ConfigurarConcesionariaForm form) throws SQLException {

        return this.executeQueryProcedure("dbo.get_concesionaria_config_params");
    }


    public List<ConfigurarConcesionariaForm> selectParamsByConcesionariaId(final Long id) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionaria_config_params_by_concesionaria_id(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, id);
        final List<ConfigurarConcesionariaForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    @Override
    public boolean valid(final ConfigurarConcesionariaForm form) throws SQLException {
        return false;
    }
}
