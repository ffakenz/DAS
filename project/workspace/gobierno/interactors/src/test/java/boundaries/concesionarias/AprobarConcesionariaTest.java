package boundaries.concesionarias;

import concesionarias.ConcesionariaInteractor;
import concesionarias.forms.ConcesionariaForm;
import mocks.MSConcesionariaDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AprobarConcesionariaTest {

    @Test
    public void testAprobadasIsEmpty() {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConcesionariaInteractor concecionaria = new ConcesionariaInteractor();

        assertEquals(new ArrayList<>(), concecionaria.consultarAprobadas().apply(dao));
    }

    @Test
    public void testAprobarConcecionaria() throws SQLException {
        MSConcesionariaDaoMock dao = new MSConcesionariaDaoMock();
        ConcesionariaInteractor concecionaria = new ConcesionariaInteractor();

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

        assertEquals(true, concecionaria.consultarAprobadas().apply(dao).contains(concAprobada.get()));
        assertEquals(true, concAprobada.get().getFechaAlta() != null);
        assertEquals(true, codigo.isPresent());
    }
}
