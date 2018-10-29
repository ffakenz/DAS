package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.model.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.model.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.daos.MSParticipanteDao;
import ar.edu.ubp.das.src.jobs.daos.MSSorteoDao;
import ar.edu.ubp.das.src.jobs.forms.ParticipanteForm;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.SQLException;
import java.util.Optional;

public class SorteoJob implements Job {

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumerManager consumerManager;
    private CuotasManager cuotasManager;
    private EstadoCuentasManager estadoCuentasManager;

    // inners
    private MSParticipanteDao msParticipanteDao;
    private MSSorteoDao msSorteoDao;

    public SorteoJob() {
        this.concesionariasManager = new ConcesionariasManager(new MSConcesionariasDao());
        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(new MSConfigurarConcesionariaDao());
        this.consumerManager = new ConsumerManager(new MSConsumerDao());
        this.cuotasManager = new CuotasManager(new MSCuotasDao());
        this.estadoCuentasManager = new EstadoCuentasManager(new MSEstadoCuentasDao());

        this.msParticipanteDao = new MSParticipanteDao();
        this.msSorteoDao = new MSSorteoDao();
    }

    private Optional<ParticipanteForm> verificarCancelacionCuenta() throws JobExecutionException {
        try {
            final Optional<ParticipanteForm> ultimoGanador = msParticipanteDao.getUltimoGanador();
            if (!ultimoGanador.isPresent()) {
                return ultimoGanador;
            }

            final ParticipanteForm ganador = ultimoGanador.get();
            final Optional<EstadoCuentasForm> estadoCuentaGanador =
                    estadoCuentasManager.getDao().selectEstadoCuentasById(ganador.getIdPlan());

            final Optional<ConcesionariaForm> concesionariaGanador =
                    concesionariasManager.getDao().selectById(estadoCuentaGanador.get().getId());

            configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(concesionariaGanador.get().getId());


            // final EstadoPlanCuenta estadoPlanGanador = ConcesionariaClient.getEstadoCuenta(ultimoGanador.get());
            return Optional.empty();

        } catch (final SQLException ex) {
            throw new JobExecutionException(ex);
        }
    }

    @Override

    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {

        final Optional<ParticipanteForm> ganador = verificarCancelacionCuenta();
        /*
        // Verificar Cancelacion de Cuenta
        Optional<Ganador> ganador = Db.getUltimoGanador();
        if(ganador.isPresente) {
            EstadoPlanCuenta estadoPlanGanador = ConcesionariaClient.getEstadoCuenta(ganador);
            if(!esCancelado(estadoPlanGanador)) {
                return error;
            }

            return true;
            Db.actualizarEstadoGanador(estadoPlanGanador);
        }
         */

        System.out.println("SORTEO");
    }
}
