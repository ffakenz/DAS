package boundaries.concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.AprobarInteractor;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.boundaries.ConsultarAprobadas;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AprobarConcesionariaTest {

    MSConcesionariasDao dao;
    DatasourceConfig dataSourceConfig;
    ConsultarAprobadas concesionariaInteractor;

    @Before
    public void setup() throws SQLException {
        dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        dao = new MSConcesionariasDao();
        dao.setDatasource(dataSourceConfig);

        concesionariaInteractor = new ConsultarAprobadasInteractor();
    }


    @Test
    public void test01AprobadasIsEmpty() {
        assertEquals(0, concesionariaInteractor.consultarAprobadas().apply(dao).size());
    }

    @Test
    public void test02AprobarConcecionaria() throws SQLException {
        final AprobarInteractor aprobador = new AprobarInteractor();
        final ConsultarAprobadasInteractor consultor = new ConsultarAprobadasInteractor();

        final ConcesionariaForm concesionariaForm = new ConcesionariaForm();
        concesionariaForm.setNombre("C4");
        concesionariaForm.setConfig("REST");
        concesionariaForm.setCuit("23-93337511-4");


        final Optional<String> codigo = aprobador.aprobarConcesionaria(concesionariaForm).apply(dao);

        final Optional<ConcesionariaForm> concAprobada =
                dao.select(null).stream().filter(c -> {
                    ConcesionariaForm concForm = (ConcesionariaForm) c;
                    return codigo.map(cod ->
                            concForm.getCodigo() != null && concForm.getCodigo().equals(cod)
                    ).orElse(false);
                }).map(c -> (ConcesionariaForm) c).findFirst();

        assertEquals(true, consultor.consultarAprobadas().apply(dao).contains(concAprobada.get()));
        assertEquals(true, concAprobada.get().getFechaAlta() != null);
        assertEquals(true, codigo.isPresent());
    }

    @Test
    public void test03AprobadasIsNotEmpty() {
        assertEquals(1, concesionariaInteractor.consultarAprobadas().apply(dao).size());
    }
}
