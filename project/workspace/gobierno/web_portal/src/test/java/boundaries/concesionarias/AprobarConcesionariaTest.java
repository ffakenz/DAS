package boundaries.concesionarias;

import ar.edu.ubp.das.src.concesionarias.AprobarInteractor;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.boundaries.Aprobar;
import ar.edu.ubp.das.src.concesionarias.boundaries.ConsultarAprobadas;
import ar.edu.ubp.das.src.concesionarias.boundaries.Registrar;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariasDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AprobarConcesionariaTest {

    @Test
    public void testAprobadasIsNotEmpty() {
        final MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        final ConsultarAprobadas concecionaria = new ConsultarAprobadasInteractor();

        assertEquals(2, concecionaria.consultarAprobadas().apply(dao).size());
    }

    @Test
    public void testAprobarConcecionaria() throws SQLException {
        final MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        final Aprobar aprobador = new AprobarInteractor();
        final ConsultarAprobadas consultor = new ConsultarAprobadasInteractor();

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");


        final Registrar registrador = new RegistrarInteractor();
        final Optional<Long> registroID = registrador.registrarConcesionaria(concecionaria).apply(dao);

        assert (registroID.isPresent());


        final Optional<String> codigo =
                aprobador.aprobarConcesionaria(concecionaria).apply(dao);

        final Optional<ConcesionariaForm> concAprobada = dao.select(null).stream().filter(c -> {
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
