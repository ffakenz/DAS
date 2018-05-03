package boundaries.concesionarias;

import concesionarias.RegistrarInteractor;
import concesionarias.boundaries.Registrar;
import concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariasDaoMock;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarConcecionariaTest {

    @Test
    public void testMockDBIsEmpty() {
        MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");

        assertEquals(false, dao.concesionarias.contains(concecionaria));
    }

    @Test
    public void testRegistrarConcecionarias() {
        MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");
        concecionaria.setItem("direccion", "whatever");
        concecionaria.setItem("tel", "whatever");
        concecionaria.setItem("email", "whatever");

        Registrar concImpl = new RegistrarInteractor();

        Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(true, dao.concesionarias.contains(concecionaria));
        assertEquals(logInId, Optional.of(new Long(4)));
    }

    @Test
    public void testRegistrarTwice(){
        MSConcesionariasDaoMock dao = new MSConcesionariasDaoMock();
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");

        Registrar concImpl = new RegistrarInteractor();
        Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);
        Optional<Long> logInId2 = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(logInId, Optional.of(new Long(4)));
        assertEquals(logInId2, Optional.empty());
    }
}

