package ar.edu.ubp.das.src.jobs.consumo_absoluto.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.forms.ConsumoAbsolutoForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class MSConsumoAbsolutoDao extends DaoImpl<ConsumoAbsolutoForm> {
    private static final Logger log = LoggerFactory.getLogger(MSConsumoAbsolutoDao.class);

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

    public void insertConsumoAbsoluto(final ConsumoAbsolutoForm form) {
        try {
            executeProcedure("dbo.log_consumo_absoluto(?,?,?,?)", form,
                    "idSorteo","fecha", "estado", "cause");
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[ConsumoAbsoluto.ejecutar][FAILED insertConsumoAbsoluto]");
        }

    }

    public void insertConsumoAbsolutoConcesionaria(final ConsumoAbsolutoForm form) {
        try {
            executeProcedure("dbo.log_consumo_absoluto_concesionaria(?,?,?,?,?)", form,
                    "idSorteo", "concesionariaId", "fecha", "estado", "cause");
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[ConsumoAbsoluto.ejecutar][FAILED insertConsumoAbsolutoConcesionaria]");
        }
    }

    public void insertConsumoAbsolutoConcesionariaPlan(final ConsumoAbsolutoForm form) {
        try {
            executeProcedure("dbo.log_consumo_absoluto_concesionaria_plan(?,?,?,?,?,?,?,?)", form,
                    "idSorteo", "concesionariaId", "idRequestResp", "planId", "estadoCuentaId", "fecha", "estado", "cause");
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[ConsumoAbsoluto.ejecutar][FAILED insertConsumoAbsolutoConcesionariaPlan]");
        }
    }
}
