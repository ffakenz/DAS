package boundaries.concesionarias;

import concesionarias.AprobarInteractor;
import concesionarias.ConsultarAprobadasInteractor;
import concesionarias.RegistrarInteractor;
import concesionarias.boundaries.Aprobar;
import concesionarias.boundaries.ConsultarAprobadas;
import concesionarias.boundaries.Registrar;
import concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariaDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AprobarConcesionariaTest {

    @Test
    public void testAprobadasIsNotEmpty() {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConsultarAprobadas concecionaria = new ConsultarAprobadasInteractor();

        assert(concecionaria.consultarAprobadas().apply(dao).size() == 3);
    }

    @Test
    public void testAprobarConcecionaria() throws SQLException {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        Aprobar aprobador = new AprobarInteractor();
        ConsultarAprobadas consultor = new ConsultarAprobadasInteractor();

        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");


        Registrar registrador = new RegistrarInteractor();
        Optional<Long> registroID = registrador.registrarConcesionaria(concecionaria).apply(dao);

        registroID.ifPresent(System.out::println);
        assert(registroID.isPresent());


        Optional<String> codigo =
                aprobador.aprobarConcesionaria(concecionaria).apply(dao);

        Optional<ConcesionariaForm> concAprobada = dao.select(null).stream().filter(c -> {
            ConcesionariaForm concForm = (ConcesionariaForm) c;
            return codigo.map(cod -> {
                return concForm.getCodigo() != null && concForm.getCodigo().equals(cod);
            }).orElse(false);
        }).map(c -> (ConcesionariaForm) c).findFirst();

        assertEquals(true, consultor.consultarAprobadas().apply(dao).contains(concAprobada.get()));
        assertEquals(true, concAprobada.get().getFechaAlta() != null);
        assertEquals(true, codigo.isPresent());
    }
}
