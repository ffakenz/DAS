package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSSorteoDao extends DaoImpl<SorteoForm> {

    public MSSorteoDao() {
        super(SorteoForm.class);
    }

    @Override
    public void insert(final SorteoForm form) throws SQLException {
        this.executeProcedure("dbo.log_sorteo(?)", form,
                "mesSorteo");
    }

    @Override
    public void update(final SorteoForm form) throws SQLException {
        this.executeProcedure("dbo.update_estado_sorteo(?,?)", form,
                "id", "estado");
    }

    @Override
    public void delete(final SorteoForm form) throws SQLException {

    }

    @Override
    public List<SorteoForm> select(final SorteoForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_sorteos");
    }

    @Override
    public boolean valid(final SorteoForm form) throws SQLException {
        return false;
    }

    public void ejecutar(final SorteoForm form) throws SQLException {
        this.executeProcedure("dbo.ejecutar_sorteo(?,?)", form,
                "id", "fechaEjecucion");
    }

    public Optional<SorteoForm> getUltimoSorteo() throws SQLException {
        return this.select()
                .stream()
                .findFirst();
    }

    public Optional<SorteoForm> getSorteoById(final Long sorteoId) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getId().equals(sorteoId))
                .findFirst();
    }

    public List<SorteoForm> getSorteosByMes(final Integer mesSorteo) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getMesSorteo().equals(mesSorteo))
                .collect(Collectors.toList());
    }
}
