package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoXConcesionariaForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSConfigTecnoParamDao extends DaoImpl<ConfigTecnoXConcesionariaForm> {

    public MSConfigTecnoParamDao() {
        super(ConfigTecnoXConcesionariaForm.class);
    }

    @Override
    public void insert(final ConfigTecnoXConcesionariaForm form) throws SQLException {
        this.executeProcedure("dbo.log_concesionaria_x_config_tecnologica(?, ?)", form,
                "concesionariaId", "configTecnologica");
    }

    @Override
    public void update(final ConfigTecnoXConcesionariaForm form) throws SQLException {

    }

    @Override
    public void delete(final ConfigTecnoXConcesionariaForm form) throws SQLException {

    }

    @Override
    public List<ConfigTecnoXConcesionariaForm> select(final ConfigTecnoXConcesionariaForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final ConfigTecnoXConcesionariaForm form) throws SQLException {
        return selectByConcesionariaAndTecno(form).isPresent();
    }

    public Optional<ConfigTecnoXConcesionariaForm> selectByConcesionariaAndTecno(final ConfigTecnoXConcesionariaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionaria_x_config_tecnologica(?, ?)",
                form, "concesionariaId", "configTecnologica")
                .stream()
                .findFirst();
    }
}
