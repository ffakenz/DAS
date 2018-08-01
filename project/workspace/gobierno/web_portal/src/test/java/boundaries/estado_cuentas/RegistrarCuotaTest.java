package boundaries.estado_cuentas;

import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotaForm;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class RegistrarCuotaTest {

    final MSCuotaDao cuotaDao = new MSCuotaDao();

    @Test
    public void validarRegistrarSuccessfully() throws SQLException {

        final CuotaForm cuota = new CuotaForm();
        cuota.setEstadoCuentaId(Long.valueOf(5));
        cuota.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuota.setMonto(10000);
        cuota.setFechaPago(Timestamp.valueOf("2018-01-27 21:58:01"));

        cuotaDao.insert(cuota);

        assertEquals(true, cuotaDao.select(null).contains(cuota));

        final CuotaForm cuota2 = new CuotaForm();
        cuota2.setEstadoCuentaId(Long.valueOf(5));
        cuota2.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuota2.setMonto(10000);
        cuota2.setFechaPago(Timestamp.valueOf("2018-01-27 21:58:01"));

        cuotaDao.insert(cuota2);

        assertEquals(14, cuotaDao.select(null).size());
    }

}
