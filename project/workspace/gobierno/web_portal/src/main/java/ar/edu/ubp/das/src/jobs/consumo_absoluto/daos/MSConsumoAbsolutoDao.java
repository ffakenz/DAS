package ar.edu.ubp.das.src.jobs.consumo_absoluto.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.forms.ConsumoAbsolutoForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ar.edu.ubp.das.src.jobs.consumo.forms.EstadoConsumo.SUCCESS;

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

    public Optional<ConsumoAbsolutoForm> selectConsumoByPlanIdAndConcesionariaAndSorteoId(final ConsumoAbsolutoForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_consumo_absoluto_by_plan_and_cid_and_sid(?,?,?)", form,
                "concesionariaId", "planId", "idSorteo")
                .stream()
                .findFirst();
    }


    public List<ConsumoAbsolutoForm> selectConsumoByConcesionariaAndSorteo(final ConsumoAbsolutoForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_consumo_absoluto_by_cid_and_sid(?,?)", form,
                "concesionariaId", "idSorteo");
    }

    public List<ConsumoAbsolutoForm> selectConsumoBySorteo(final ConsumoAbsolutoForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_consumo_absoluto_by_sorteo(?)", form,
                "idSorteo");
    }

    public boolean areAllConsumedSuccess(final ConsumoAbsolutoForm form) {
        try {
            return selectConsumoBySorteo(form)
                    .stream()
                    .allMatch( c -> c.getEstadoConsumo().equals(SUCCESS) );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
