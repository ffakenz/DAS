package boundaries.concesionarias;

import concesionarias.ConcesionariaInteractor;
import concesionarias.boundaries.Concesionaria;
import concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariaDaoMock;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarConcecionariaTest {

    @Test
    public void testMockDBIsEmpty() {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");

        assertEquals(false, dao.concesionarias.contains(concecionaria));
    }

    @Test
    public void testRegistrarConcecionarias() {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");

        Concesionaria concImpl = new ConcesionariaInteractor();

        Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(true, dao.concesionarias.contains(concecionaria));
        assertEquals(logInId, Optional.of(new Long(4)));
    }

    @Test
    public void testRegistrarTwice(){
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");

        Concesionaria concImpl = new ConcesionariaInteractor();
        Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);
        Optional<Long> logInId2 = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(logInId, Optional.of(new Long(4)));
        assertEquals(logInId2, Optional.of(new Long(4)));
    }
}
