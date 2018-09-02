package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;

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

    public List<ConfigurarConcesionariaForm> select() throws SQLException {

        return this.executeQueryProcedure("dbo.get_concesionaria_config_params");
    }

    @Override
    public boolean valid(final ConfigurarConcesionariaForm form) throws SQLException {
        return false;
    }
}
