package util;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoXConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import clients.factory.ClientType;

import static utils.MiddlewareConstants.REST_PARAM_URL;

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

    public ConfigurarConcesionariaForm getConfigRest(Long concesionariaId) {

        ConfigurarConcesionariaForm configConcesionariaForm = new ConfigurarConcesionariaForm();
        configConcesionariaForm.setConcesionariaId(concesionariaId);
        configConcesionariaForm.setConfigTecno(ClientType.REST.getName());
        configConcesionariaForm.setConfigParam(REST_PARAM_URL);
        configConcesionariaForm.setValue("http://localhost:8001/concesionaria_rest_one/concesionariaRestOne");

        return configConcesionariaForm;
    }

    public ConfigTecnoXConcesionariaForm getConfigTecnoXConcesionaria(Long concesionariaId, String tecno) {

        ConfigTecnoXConcesionariaForm configTecnoXConcesionariaForm = new ConfigTecnoXConcesionariaForm();
        configTecnoXConcesionariaForm.setConcesionariaId(concesionariaId);
        configTecnoXConcesionariaForm.setConfigTecnologica(tecno);
        return configTecnoXConcesionariaForm;
    }


}
