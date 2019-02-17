package ar.edu.ubp.das.src.jobs.consumo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo.forms.NotificationUpdateForm;

import java.sql.SQLException;
import java.util.List;

public class MSNotificationUpdateDao extends DaoImpl<NotificationUpdateForm> {

    public MSNotificationUpdateDao() {
        super(NotificationUpdateForm.class);
    }

    @Override
    public void insert(final NotificationUpdateForm form) throws SQLException {
        this.executeProcedure("dbo.log_notification_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", form,
                "planId", "planEstado", "planFechaAlta", "planFechaUltimaActualizacion",
                "planTipoDePlan", "cuotaNroCuota", "cuotaFechaVencimiento", "cuotaMonto", "cuotaFechaPago", "cuotaFechaAlta",
                "clienteDocumento", "clienteNombre", "clienteApellido", "clienteNroTelefono", "clienteEmail", "vehiculoId", "vehiculoTipo",
                "vehiculoNombre", "vehiculoPrecio", "vehiculoMarca", "vehiculoModelo", "vehiculoColor", "concesionariaId");
    }

    @Override
    public void update(final NotificationUpdateForm form) throws SQLException {

    }

    @Override
    public void delete(final NotificationUpdateForm form) throws SQLException {

    }

    @Override
    public List<NotificationUpdateForm> select(final NotificationUpdateForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final NotificationUpdateForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.is_valid_notification_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                form, "planId", "planEstado", "planFechaAlta", "planFechaUltimaActualizacion",
                "planTipoDePlan", "cuotaNroCuota", "cuotaFechaVencimiento", "cuotaMonto", "cuotaFechaPago", "cuotaFechaAlta",
                "clienteDocumento", "clienteNombre", "clienteApellido", "clienteNroTelefono", "clienteEmail", "vehiculoId", "vehiculoTipo",
                "vehiculoNombre", "vehiculoPrecio", "vehiculoMarca", "vehiculoModelo", "vehiculoColor", "concesionariaId").isEmpty();
    }
}



