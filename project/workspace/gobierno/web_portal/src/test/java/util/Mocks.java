package util;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoXConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import clients.factory.ClientType;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static utils.MiddlewareConstants.*;

public enum Mocks {
    INSTANCE;

    public ConcesionariaForm getConcesionariaFormFiat(String cuit) {
        ConcesionariaForm concesionariaForm = new ConcesionariaForm();
        concesionariaForm.setCuit(cuit);
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

    public EstadoCuentasForm getEstadoCuentaForm(ConcesionariaForm concesionariaForm, Long documento) {

        EstadoCuentasForm estadoCuentasForm = new EstadoCuentasForm();

        estadoCuentasForm.setConcesionariaId(concesionariaForm.getId());
        estadoCuentasForm.setDocumentoCliente(documento);
        estadoCuentasForm.setNroPlanConcesionaria(99L);
        estadoCuentasForm.setVehiculo(1L);
        estadoCuentasForm.setEstado("en_proceso");
        estadoCuentasForm.setFechaAltaConcesionaria(Timestamp.from(Instant.now().minusSeconds(3600 * 24 * 2)));

        return estadoCuentasForm;
    }


    public ConsumerForm getConsumerForm(Long documento) {
        ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(documento);
        consumerForm.setNombre("Pepe");
        consumerForm.setApellido("Perez");
        consumerForm.setNroTelefono("35156345678");
        consumerForm.setEmail("carliperezozo@mail.com");

        return consumerForm;
    }

    public UsuarioForm getUsuarioForm(Long documento) {
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setRol("consumer");
        usuarioForm.setDocumento(documento);

        return usuarioForm;
    }
}
