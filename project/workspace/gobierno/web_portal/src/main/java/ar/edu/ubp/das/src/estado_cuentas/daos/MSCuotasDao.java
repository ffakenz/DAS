package ar.edu.ubp.das.src.estado_cuentas.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSCuotasDao extends DaoImpl<CuotasForm> {


    public MSCuotasDao() {
        super(CuotasForm.class);
    }

    @Override
    public void insert(final CuotasForm bean) throws SQLException {

        this.executeProcedure("dbo.log_cuota(?, ?, ?, ?, ?, ?)", bean,
                "nroCuota", "estadoCuentaId", "fechaAltaConcesionaria", "fechaVencimiento", "monto", "fechaPago");
    }

    @Override
    public void update(final CuotasForm form) throws SQLException {
        this.pagarCuota(form);
    }

    @Override
    public void delete(final CuotasForm form) throws SQLException {
    }

    @Override
    public List<CuotasForm> select(final CuotasForm form) throws SQLException {
        return this.select();
    }

    public List<CuotasForm> select() throws SQLException {
        return this.executeQueryProcedure("dbo.get_cuotas");
    }

    @Override
    public boolean valid(final CuotasForm form) throws SQLException {
        return selectCuota(form).isPresent();
    }

    public void pagarCuota(final CuotasForm form) throws SQLException {
        this.executeProcedure("dbo.pagar_cuota(?, ?, ?, ?)", form,
                "nroCuota", "estadoCuentaId", "monto", "fechaPago");
    }

    // select by unique
    public Optional<CuotasForm> selectCuota(final CuotasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_cuota(?, ?)",
                form, "nroCuota", "estadoCuentaId")
                .stream()
                .findFirst();
    }

    public List<CuotasForm> selectByEstadoCuenta(final CuotasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_cuotas_by_estado_cuenta_id(?)",
                form, "estadoCuentaId");
    }

    public void upsert(final CuotasForm form) throws SQLException {
        if (!this.valid(form)) {
            this.insert(form);
        } else {
            this.update(form);
        }
        // return this.selectCuota(form); // return the updated or inserted
    }
}
