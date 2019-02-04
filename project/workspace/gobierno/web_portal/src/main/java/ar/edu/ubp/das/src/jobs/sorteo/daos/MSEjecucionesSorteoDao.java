package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EjecucionesSorteoForm;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MSEjecucionesSorteoDao extends DaoImpl<EjecucionesSorteoForm> {

    public MSEjecucionesSorteoDao() {
        super(EjecucionesSorteoForm.class);
    }

    @Override
    public void insert(final EjecucionesSorteoForm form) throws SQLException {
        this.executeProcedure("dbo.log_ejecuciones_sorteo(?,?,?)", form, "idSorteo", "estado", "comments");
    }

    @Override
    public void update(final EjecucionesSorteoForm form) throws SQLException {
    }

    @Override
    public void delete(final EjecucionesSorteoForm form) throws SQLException {
    }

    @Override
    public List<EjecucionesSorteoForm> select(EjecucionesSorteoForm form) throws SQLException {
        return this.select();
    }

    @Override
    public List<EjecucionesSorteoForm> select() throws SQLException {
        return this.executeQueryProcedure("dbo.get_ejecuciones_sorteo");
    }

    @Override
    public boolean valid(EjecucionesSorteoForm form) throws SQLException {
        return false;
    }

    public List<EjecucionesSorteoForm> getEjecucionesBySorteoId(final Long sorteoId) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getIdSorteo().equals(sorteoId))
                .collect(Collectors.toList());
    }

    public List<EjecucionesSorteoForm> getEjecucionesByEstado(final String estado) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getEstado().equals(estado))
                .collect(Collectors.toList());
    }
}
