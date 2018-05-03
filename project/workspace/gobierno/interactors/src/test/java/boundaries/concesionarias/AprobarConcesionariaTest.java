package boundaries.concesionarias;

import concesionarias.AprobarInteractor;
import concesionarias.ConsultarAprobadasInteractor;
import concesionarias.RegistrarInteractor;
import concesionarias.boundaries.Aprobar;
import concesionarias.boundaries.ConsultarAprobadas;
import concesionarias.boundaries.Registrar;
import concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariasDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AprobarConcesionariaTest {

    @Test
    public void testAprobadasIsNotEmpty() {
        MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        ConsultarAprobadas concecionaria = new ConsultarAprobadasInteractor();

        assertEquals(2, concecionaria.consultarAprobadas().apply(dao).size());
    }

    @Test
    public void testAprobarConcecionaria() throws SQLException {
        MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        Aprobar aprobador = new AprobarInteractor();
        ConsultarAprobadas consultor = new ConsultarAprobadasInteractor();

        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");


        Registrar registrador = new RegistrarInteractor();
        Optional<Long> registroID = registrador.registrarConcesionaria(concecionaria).apply(dao);

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
