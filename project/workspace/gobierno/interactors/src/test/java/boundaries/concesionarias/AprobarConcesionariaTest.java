package boundaries.concesionarias;

import concesionarias.AprobarInteractor;
import concesionarias.ConsultarAprobadasInteractor;
import concesionarias.boundaries.Aprobar;
import concesionarias.boundaries.ConsultarAprobadas;
import concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariaDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AprobarConcesionariaTest {

    @Test
    public void testAprobadasIsEmpty() {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConsultarAprobadas concecionaria = new ConsultarAprobadasInteractor();

        assertEquals(new ArrayList<>(), concecionaria.consultarAprobadas().apply(dao));
    }

    @Test
    public void testAprobarConcecionaria() throws SQLException {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        Aprobar concecionaria = new AprobarInteractor();
        ConsultarAprobadas consultor = new ConsultarAprobadasInteractor();

        Optional<ConcesionariaForm> conc =
                dao.select(null).stream().findFirst().map( c -> (ConcesionariaForm) c);

        Optional<String> codigo =
                conc.flatMap(c -> concecionaria.aprobarConcesionaria(c).apply(dao));

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
