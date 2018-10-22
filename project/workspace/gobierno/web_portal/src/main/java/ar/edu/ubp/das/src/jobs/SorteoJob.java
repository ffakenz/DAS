package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.model.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.model.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.daos.MSGanadorDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SorteoJob implements Job {

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumerManager consumerManager;
    private CuotasManager cuotasManager;
    private EstadoCuentasManager estadoCuentasManager;

    // inners
    private MSGanadorDao msGanadorDao;

    public SorteoJob() {
        this.concesionariasManager = new ConcesionariasManager(new MSConcesionariasDao());
        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(new MSConfigurarConcesionariaDao());
        this.consumerManager = new ConsumerManager(new MSConsumerDao());
        this.cuotasManager = new CuotasManager(new MSCuotasDao());
        this.estadoCuentasManager = new EstadoCuentasManager(new MSEstadoCuentasDao());
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

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
