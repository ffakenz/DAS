package ar.edu.ubp.das.src.jobs.consumo_absoluto.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.forms.ConsumoAbsolutoForm;

import java.sql.SQLException;
import java.util.List;

public class MSConsumoAbsolutoDao extends DaoImpl<ConsumoAbsolutoForm> {

    public MSConsumoAbsolutoDao() {
        super(ConsumoAbsolutoForm.class);
    }

    @Override
    public void insert(final ConsumoAbsolutoForm form) throws SQLException {

    }

    @Override
    public void update(final ConsumoAbsolutoForm form) throws SQLException {

    }

    @Override
    public void delete(final ConsumoAbsolutoForm form) throws SQLException {

    }

    @Override
    public List<ConsumoAbsolutoForm> select(final ConsumoAbsolutoForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final ConsumoAbsolutoForm form) throws SQLException {
        return false;
    }

    public void insertConsumoAbsoluto(final ConsumoAbsolutoForm form) throws SQLException {
        executeProcedure("dbo.log_consumo_absoluto(?,?,?)", form,
                "fecha", "estado", "cause");
    }

    public void insertConsumoAbsolutoConcesionaria(final ConsumoAbsolutoForm form) throws SQLException {
        executeProcedure("dbo.log_consumo_absoluto_concesionaria(?,?,?,?)", form,
                "concesionariaId", "fecha", "estado", "cause");
    }

    public void insertConsumoAbsolutoConcesionariaPlan(final ConsumoAbsolutoForm form) throws SQLException {
        executeProcedure("dbo.log_consumo_absoluto_concesionaria_plan(?,?,?,?,?,?,?)", form,
                "concesionariaId", "idRequestResp", "planId", "estadoCuentaId", "fecha", "estado", "cause");
    }
}
