package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigTecnoParamDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.GeneralConfigConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static utils.MiddlewareConstants.*;

public class ConfigurarConcesionariaInteractorTest {

    MSConcesionariasDao msConcesionariasDao;
    MSConfigurarConcesionariaDao msConfigurarConcesionariaDao;
    MSConfigTecnoParamDao msConfigTecnoParamDao;
    ConfigurarConcesionariaInteractor configurarConcesionariaInteractor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(dataSourceConfig);

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

        msConfigTecnoParamDao = new MSConfigTecnoParamDao();
        msConfigTecnoParamDao.setDatasource(dataSourceConfig);

        configurarConcesionariaInteractor = new ConfigurarConcesionariaInteractor(msConfigurarConcesionariaDao, msConcesionariasDao, msConfigTecnoParamDao);
    }

    @Test
    public void test12_convert_to_axis_ok() {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", AXIS);
        form.setItem(AXIS_PARAM_ENDP_URL, "endpoint_url_param");
        form.setItem(AXIS_PARAM_TARGET, "target_namespace_url_param");

        final GeneralConfigConcesionariaForm generalConfigConcesionariaForm = configurarConcesionariaInteractor.convertTo(form);

        assertEquals(new Long(1), generalConfigConcesionariaForm.getConcesionariaId());
        assertEquals(AXIS, generalConfigConcesionariaForm.getParams().get(0).getConfigTecno());
        assertEquals(AXIS_PARAM_TARGET, generalConfigConcesionariaForm.getParams().get(0).getConfigParam());
        assertEquals(AXIS_PARAM_ENDP_URL, generalConfigConcesionariaForm.getParams().get(1).getConfigParam());
        assertEquals("target_namespace_url_param", generalConfigConcesionariaForm.getParams().get(0).getValue());
        assertEquals("endpoint_url_param", generalConfigConcesionariaForm.getParams().get(1).getValue());
    }

    @Test
    public void test12_convert_to_rest_ok() {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", REST);
        form.setItem(REST_PARAM_URL, "new_url");

        final GeneralConfigConcesionariaForm generalConfigConcesionariaForm = configurarConcesionariaInteractor.convertTo(form);

        assertEquals(new Long(1), generalConfigConcesionariaForm.getConcesionariaId());
        assertEquals(REST, generalConfigConcesionariaForm.getParams().get(0).getConfigTecno());
        assertEquals("url", generalConfigConcesionariaForm.getParams().get(0).getConfigParam());
        assertEquals("new_url", generalConfigConcesionariaForm.getParams().get(0).getValue());

    }

    @Test
    public void test13_convert_to_cxf_ok() {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", CXF);
        form.setItem(CXF_PARAM_WSDL_URL, "new_url");

        final GeneralConfigConcesionariaForm generalConfigConcesionariaForm = configurarConcesionariaInteractor.convertTo(form);

        assertEquals(new Long(1), generalConfigConcesionariaForm.getConcesionariaId());
        assertEquals(CXF, generalConfigConcesionariaForm.getParams().get(0).getConfigTecno());
        assertEquals("wsdlUrl", generalConfigConcesionariaForm.getParams().get(0).getConfigParam());
        assertEquals("new_url", generalConfigConcesionariaForm.getParams().get(0).getValue());

    }

    @Test
    public void test14_is_valid_ok_rest() throws SQLException {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", REST);
        form.setItem(REST_PARAM_URL, "new_url");

        assertTrue(configurarConcesionariaInteractor.isValid(form));
    }

    @Test
    public void test15_is_valid_ok_cxf() throws SQLException {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", CXF);
        form.setItem(CXF_PARAM_WSDL_URL, "new_url");

        assertTrue(configurarConcesionariaInteractor.isValid(form));
    }

    @Test
    public void test16_is_valid_ok_axis() throws SQLException {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", AXIS);
        form.setItem(AXIS_PARAM_TARGET, "targe_param");
        form.setItem(AXIS_PARAM_ENDP_URL, "endpoint_url_param");

        assertTrue(configurarConcesionariaInteractor.isValid(form));
    }

    @Test
    public void test17_execute_ok_rest() throws Exception {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "1");
        form.setItem("techno", REST);
        form.setItem(REST_PARAM_URL, "url_rest");

        final InteractorResponse<Boolean> response = configurarConcesionariaInteractor.execute(form);

        assertTrue(response.getResult());
        assertEquals(ResponseForward.SUCCESS.getForward(), response.getResponse().getForward());

        final ConfigurarConcesionariaForm configConcesionariaForm = msConfigurarConcesionariaDao.selectParamsByConcesionariaId(1L).get(0);

        if (configConcesionariaForm.getConfigParam().equals(REST_PARAM_URL)) {
            assertEquals("url_rest", configConcesionariaForm.getValue());
        } else {
            throw new Exception("invalid test");
        }
    }

    @Test
    public void test18_execute_ok_cxf() throws Exception {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "2");
        form.setItem("techno", CXF);
        form.setItem(CXF_PARAM_WSDL_URL, "url_cxf");

        final InteractorResponse<Boolean> response = configurarConcesionariaInteractor.execute(form);

        assertTrue(response.getResult());
        assertEquals(ResponseForward.SUCCESS.getForward(), response.getResponse().getForward());

        final ConfigurarConcesionariaForm configConcesionariaForm = msConfigurarConcesionariaDao.selectParamsByConcesionariaId(2L).get(0);

        if (configConcesionariaForm.getConfigParam().equals(CXF_PARAM_WSDL_URL)) {
            assertEquals("url_cxf", configConcesionariaForm.getValue());
        } else {
            throw new Exception("invalid test");
        }
    }

    @Test
    public void test19_execute_ok_axis() throws SQLException {

        final DynaActionForm form = new DynaActionForm();
        form.setItem("concesionariaId", "3");
        form.setItem("techno", AXIS);
        form.setItem(AXIS_PARAM_ENDP_URL, "endpoint_url");
        form.setItem(AXIS_PARAM_TARGET, "target");

        final InteractorResponse<Boolean> response = configurarConcesionariaInteractor.execute(form);

        assertTrue(response.getResult());
        assertEquals(ResponseForward.SUCCESS.getForward(), response.getResponse().getForward());

        final List<ConfigurarConcesionariaForm> configurarConcesionariaForms = msConfigurarConcesionariaDao.selectParamsByConcesionariaId(3L);

        configurarConcesionariaForms.forEach(c -> {

            if (c.getConfigParam().equals(AXIS_PARAM_ENDP_URL)) {
                assertEquals("endpoint_url", c.getValue());
            } else if (c.getConfigParam().equals(AXIS_PARAM_TARGET)) {
                assertEquals("target", c.getValue());
            }

        });
    }
}
