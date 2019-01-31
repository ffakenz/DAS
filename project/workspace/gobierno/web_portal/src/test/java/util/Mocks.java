package util;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoXConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import clients.factory.ClientType;

import java.util.Arrays;
import java.util.List;

import static utils.MiddlewareConstants.*;

public enum Mocks {
    INSTANCE;

    public ConcesionariaForm getConcesionariaFormFiat() {
        ConcesionariaForm concesionariaForm = new ConcesionariaForm();
        concesionariaForm.setCuit("20-93337511-1");
        concesionariaForm.setEmail("concesionaria_fiat@gmail.com");
        concesionariaForm.setDireccion("direccion concesionaria fiat 123");
        concesionariaForm.setNombre("Concesionaria Fiat");
        concesionariaForm.setTel("351-7696448");

        return concesionariaForm;
    }

    public List<ConfigurarConcesionariaForm> getConfigRest(Long concesionariaId) {

        ConfigurarConcesionariaForm configConcesionariaForm = new ConfigurarConcesionariaForm();
        configConcesionariaForm.setConcesionariaId(concesionariaId);
        configConcesionariaForm.setConfigTecno(ClientType.REST.getName());
        configConcesionariaForm.setConfigParam(REST_PARAM_URL);
        configConcesionariaForm.setValue("http://localhost:8001/concesionaria_rest_one/concesionariaRestOne");

        return Arrays.asList(configConcesionariaForm);
    }

    public List<ConfigurarConcesionariaForm> getConfigCxf(Long concesionariaId) {

        ConfigurarConcesionariaForm configConcesionariaForm = new ConfigurarConcesionariaForm();
        configConcesionariaForm.setConcesionariaId(concesionariaId);
        configConcesionariaForm.setConfigTecno(ClientType.CXF.getName());
        configConcesionariaForm.setConfigParam(CXF_PARAM_WSDL_URL);
        configConcesionariaForm.setValue("http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl");

        return Arrays.asList(configConcesionariaForm);
    }

    public List<ConfigurarConcesionariaForm> getConfigAxis(Long concesionariaId) {

        ConfigurarConcesionariaForm configConcesionariaForm1 = new ConfigurarConcesionariaForm();
        configConcesionariaForm1.setConcesionariaId(concesionariaId);
        configConcesionariaForm1.setConfigTecno(ClientType.AXIS.getName());
        configConcesionariaForm1.setConfigParam(AXIS_PARAM_ENDP_URL);
        configConcesionariaForm1.setValue("http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/");


        ConfigurarConcesionariaForm configConcesionariaForm2 = configConcesionariaForm1;
        configConcesionariaForm2.setConfigParam(AXIS_PARAM_TARGET);
        configConcesionariaForm2.setValue("http://ws.ConcesionariaAxisOne/");

        return Arrays.asList(configConcesionariaForm1, configConcesionariaForm2);
    }

    public ConfigTecnoXConcesionariaForm getConfigTecnoXConcesionaria(Long concesionariaId, String tecno) {

        ConfigTecnoXConcesionariaForm configTecnoXConcesionariaForm = new ConfigTecnoXConcesionariaForm();
        configTecnoXConcesionariaForm.setConcesionariaId(concesionariaId);
        configTecnoXConcesionariaForm.setConfigTecnologica(tecno);
        return configTecnoXConcesionariaForm;
    }


}
