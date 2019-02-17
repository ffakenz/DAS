package util.scenarios;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.jobs.consumo.ConsumoJob;
import beans.CuotaBean;
import beans.PlanBean;
import clients.factory.ClientType;
import util.ClientFactoryStub;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ISorteoSpec {

    default void executeConsumoJob(final DatasourceConfig dataSourceConfig,
                                   final Timestamp fechaEjecucion,
                                   final Pair<ClientType, String>... tuples) {

        final HashMap<ClientType, String> concesionariasXnotificationFileName = new HashMap<>();
        for (final Pair<ClientType, String> tuple : tuples) {
            concesionariasXnotificationFileName.put(tuple.fst, tuple.snd);
        }
        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName), fechaEjecucion);
        consumer.execute(null);
    }

    default Pair<ClientType, String> pair(final ClientType fst, final String snd) {
        return new Pair<>(fst, snd);
    }

    // PlanBeanLike is an interface
    // PlanBean implements that interface
    // PlanBeanBuilder implements that and has an inner _planBean to build;
    class PlanBeanBuilder {
        private PlanBean _planBean;
        private Long planId;
        private String planEstado;
        private Timestamp planFechaAlta;
        private Timestamp planFechaUltimaActualizacion;
        private String planTipoDePlan;
        private Long clienteDocumento;
        private String clienteNombre;
        private String clienteApellido;
        private String clienteNroTelefono;
        private String clienteEmail;
        private Long vehiculoId;
        private String vehiculoTipo;
        private String vehiculoNombre;
        private Long vehiculoPrecio;
        private String vehiculoMarca;
        private String vehiculoModelo;
        private String vehiculoColor;
        private List<CuotaBean> cuotas;

        public PlanBeanBuilder(final Long planId, final List<CuotaBean> cuotas) {
            this._planBean = new PlanBean();
            this.planId = planId;
            this.cuotas = cuotas;
            this.planFechaUltimaActualizacion = Timestamp.from(Instant.now());
            setPlanAttributes();
            setClienteAttributes();
            setVehiculoAttributes();
        }


        private void setPlanAttributes() {
            this._planBean.setPlanId(planId);
            this.planEstado = "en_proceso";
            this._planBean.setPlanEstado(planEstado);
            this.planFechaAlta = planFechaUltimaActualizacion;
            this._planBean.setPlanFechaAlta(planFechaAlta);
            this._planBean.setPlanFechaUltimaActualizacion(planFechaUltimaActualizacion);
        }

        private void setClienteAttributes() {
            this.clienteDocumento = Math.abs(new Random().nextLong());
            this._planBean.setClienteDocumento(clienteDocumento);
            this.clienteNombre = "Cliente Nombre";
            this._planBean.setClienteNombre(clienteNombre);
            this.clienteApellido = "Cliente Apellido";
            this._planBean.setClienteApellido(clienteApellido);
            this.clienteNroTelefono = "123";
            this._planBean.setClienteNroTelefono(clienteNroTelefono);
            this.clienteEmail = "email@email.com";
            this._planBean.setClienteEmail(clienteEmail);
        }

        private void setVehiculoAttributes() {
            this.vehiculoId = 1L;
            this._planBean.setVehiculoId(vehiculoId);
            this.vehiculoTipo = "taxi";
            this._planBean.setVehiculoTipo(vehiculoTipo);
            this.vehiculoColor = "rojo";
            this._planBean.setVehiculoColor(vehiculoColor);
            this.vehiculoPrecio = 1000L;
            this._planBean.setVehiculoPrecio(vehiculoPrecio);
            this.vehiculoNombre = "Corsa";
            this._planBean.setVehiculoNombre(vehiculoNombre);
            this.vehiculoModelo = "vehiculo modelo";
            this._planBean.setVehiculoModelo(vehiculoModelo);
            this.vehiculoMarca = "vehiculo marca";
            this._planBean.setVehiculoMarca(vehiculoMarca);
        }

        public PlanBean build() {
            this._planBean.setCuotas(this.cuotas);
            return _planBean;
        }
    }

    class CuotaBeanBuilder {
        private Integer cantCuotas;
        List<CuotaBean> cuotas;
        List<Integer> cuotasPagas;

        CuotaBean _current;

        private Long cuotaNroCuota;
        private LocalDate cuotaFechaVencimiento;
        private Integer cuotaMonto;
        private LocalDate cuotaFechaPago;
        private LocalDate cuotaFechaAlta;

        public CuotaBeanBuilder(final Integer cantCuotas, final Integer... cuotasPagas) {
            if (cantCuotas < cuotasPagas.length) {
                throw new IllegalArgumentException("cantCuotas < cuotasPagas.length");
            }
            this._current = new CuotaBean();
            this.cantCuotas = cantCuotas;
            this.cuotaNroCuota = 0L;
            this.cuotaFechaAlta = LocalDate.now();
            this.cuotaFechaVencimiento = this.cuotaFechaAlta.plusMonths(1L);
            this.cuotaMonto = 100;
            this.cuotasPagas = Stream.of(cuotasPagas).collect(Collectors.toList());
            this.cuotas = new ArrayList<>();
        }

        private void setNextNroCuota() {

            this.cuotaNroCuota = this.cuotaNroCuota + 1L;
            this._current.setCuotaNroCuota(cuotaNroCuota);
        }

        private void setNextFechas() {
            this.cuotaFechaAlta = this.cuotaFechaAlta.plusMonths(1L);
            this.cuotaFechaVencimiento = this.cuotaFechaVencimiento.plusMonths(1L);
            this._current.setCuotaFechaAlta(Timestamp.valueOf(cuotaFechaAlta.atStartOfDay()));
            this._current.setCuotaFechaVencimiento(Timestamp.valueOf(cuotaFechaVencimiento.atStartOfDay()));
        }

        public List<CuotaBean> build() {
            for (int i = 1; i <= cantCuotas; i++) {
                this._current = new CuotaBean();
                setNextNroCuota();
                setNextFechas();
                this._current.setCuotaMonto(cuotaMonto);
                if (cuotasPagas.contains(i)) {
                    final LocalDate localDate = this.cuotaFechaAlta.plusDays(10);
                    _current.setCuotaFechaPago(Timestamp.valueOf(localDate.atStartOfDay()));
                }
                cuotas.add(_current);
            }
            return cuotas;
        }
    }
}
