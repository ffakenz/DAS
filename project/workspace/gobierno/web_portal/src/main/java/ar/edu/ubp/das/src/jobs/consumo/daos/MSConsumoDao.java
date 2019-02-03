package ar.edu.ubp.das.src.jobs.consumo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo.forms.ConsumoForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class MSConsumoDao extends DaoImpl<ConsumoForm> {

    public MSConsumoDao() {
        super(ConsumoForm.class);
    }

    @Override
    public void insert(final ConsumoForm form) throws SQLException {
        this.executeProcedure("dbo.log_consumo(?, ?, ?, ?, ?, ?)", form,
                "idConcesionaria", "idJobConsumo", "offset", "estado", "description", "idRequestResp");
    }

    @Override
    public void update(final ConsumoForm form) throws SQLException {

    }

    @Override
    public void delete(final ConsumoForm form) throws SQLException {

    }

    @Override
    public List<ConsumoForm> select(final ConsumoForm form) throws SQLException {
        return executeQueryProcedure("dbo.get_consumos", form);
    }

    @Override
    public boolean valid(final ConsumoForm form) throws SQLException {
        return false;
    }

    public Optional<ConsumoForm> getLastConsumo(final Long concesionariaId) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_last_consumo(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, concesionariaId);
        final List<ConsumoForm> result = this.executeQuery();
        this.disconnect();
        return result
                .stream()
                .findFirst();
    }

    public Optional<Timestamp> getLastOffsetForConcesionaria(final Long concesionariaId) throws SQLException {
        return this.getLastConsumo(concesionariaId).map(j -> j.getOffset());
    }

    public Optional<String> getLastEstadoForConcesionaria(final Long concesionariaId) throws SQLException {
        return this.getLastConsumo(concesionariaId).map(j -> j.getEstado());
    }
}
