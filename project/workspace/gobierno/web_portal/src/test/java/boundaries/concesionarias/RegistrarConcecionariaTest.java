package boundaries.concesionarias;

import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.boundaries.Registrar;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariasDaoMock;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarConcecionariaTest {

    @Test
    public void testMockDBIsEmpty() {
        final MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");

        assertEquals(false, dao.concesionarias.contains(concecionaria));
    }

    @Test
    public void testRegistrarConcecionarias() {
        final MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");
        concecionaria.setItem("direccion", "whatever");
        concecionaria.setItem("tel", "whatever");
        concecionaria.setItem("email", "whatever");

        final Registrar concImpl = new RegistrarInteractor();

        final Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(true, dao.concesionarias.contains(concecionaria));
        assertEquals(logInId, Optional.of(new Long(4)));
    }

    @Test
    public void testRegistrarTwice() {
        final MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");

        final Registrar concImpl = new RegistrarInteractor();
        final Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);
        final Optional<Long> logInId2 = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(logInId, Optional.of(new Long(4)));
        assertEquals(logInId2, Optional.empty());
    }
}

