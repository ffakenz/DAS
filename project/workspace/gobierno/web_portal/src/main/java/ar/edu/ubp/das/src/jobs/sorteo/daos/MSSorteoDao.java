package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.DateUtils;

import java.sql.Date;
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
        this.executeProcedure("dbo.log_sorteo(?)", form, "fechaEjecucion");
    }

    public void insertWithEstado(final SorteoForm form) throws SQLException {
        this.executeProcedure("dbo.log_sorteo_with_estado(?,?)", form, "fechaEjecucion", "estado");
    }

    @Override
    public void update(final SorteoForm form) throws SQLException {
        this.executeProcedure("dbo.update_estado_sorteo(?,?)", form,
                "id", "estado");
    }

    public void updateEstado(final SorteoForm form) throws SQLException {
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

    public void actualizarFechaSorteo(final SorteoForm form) throws SQLException {
        this.executeProcedure("dbo.actualizar_fecha_sorteo(?,?)", form,
                "id", "fechaEjecucion");
    }

    public Optional<SorteoForm> getSorteoPendiente() throws SQLException {
        return this.executeQueryProcedure("dbo.get_sorteos_pendientes")
                .stream()
                .findFirst();
    }

    public Optional<SorteoForm> getSorteoById(final Long sorteoId) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getId().equals(sorteoId))
                .findFirst();
    }

    public Optional<SorteoForm> getSorteosByFecha(final Date fechaEjecucion) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getFechaEjecucion().equals(fechaEjecucion))
                .findFirst();
    }

    public List<SorteoForm> getSorteosByMes(final Integer mesSorteo) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getMesSorteo().equals(mesSorteo))
                .collect(Collectors.toList());
    }

    public List<SorteoForm> getSorteosByAnio(final Integer anioSorteo) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getAnioSorteo().equals(anioSorteo))
                .collect(Collectors.toList());
    }

    public Optional<SorteoForm> getSorteoNuevoParaHoy() throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getEstado().equals(EstadoSorteo.NUEVO.getTipo()))
                .filter(s -> s.getFechaEjecucion().equals(DateUtils.getDayDate()))
                .findFirst();
    }

    public List<SorteoForm> getSorteosByEstado(final EstadoSorteo estadoSorteo) throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getEstado().equals(estadoSorteo.getTipo()))
                .collect(Collectors.toList());
    }

    public List<SorteoForm> getSorteoViejosEnEstadoNuevo() throws SQLException {
        return this.select()
                .stream()
                .filter(s -> s.getEstado().equals(EstadoSorteo.NUEVO.getTipo()))
                .filter(s -> s.getFechaEjecucion().compareTo(DateUtils.getDayDate()) < 0)
                .collect(Collectors.toList());
    }

}
