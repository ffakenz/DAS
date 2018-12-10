package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import clients.AxisClient;
import clients.CXFClient;
import clients.ConcesionariaServiceContract;
import clients.RestClient;
import clients.factory.ClientFactory;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

public class ClientFactoryAdapterSpec {

    DatasourceConfig dataSourceConfig;
    MSConfigurarConcesionariaDao dao;
    final String identificador = "GOBIERNO-INCENTIVO-2018";

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        dao = new MSConfigurarConcesionariaDao();
        dao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test_ClientFactoryAdapterDynamicAxis() throws Exception {
        final ClientFactoryAdapter clientFactoryAdapter =
                new ClientFactoryAdapter(ClientFactory.getInstance());

        final List<ConfigurarConcesionariaForm> configurarConcesionariaForms = dao.selectParamsByConcesionariaId(3L);

        configurarConcesionariaForms.forEach(System.out::println);

        final Optional<ConcesionariaServiceContract> clientFor = clientFactoryAdapter.getClientFor(configurarConcesionariaForms);
        assertTrue(clientFor.isPresent());

        final ConcesionariaServiceContract client = clientFor.get();

        assertTrue(client instanceof AxisClient);

        /*
        client.consultarPlanes(identificador, "2018-01-08T20:58:00").forEach(System.out::println);
        ConfigurarConcesionariaForm{concesionariaId=3, configTecno='AXIS', configParam='endpointUrl', value='http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/'}
        ConfigurarConcesionariaForm{concesionariaId=3, configTecno='AXIS', configParam='targetNameSpace', value='http://ws.ConcesionariaAxisOne/'}
        {"plan_id":1,"plan_estado":"en_proceso","plan_fecha_alta":"2018-01-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.273","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-02-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-01-18 08:58:00.000","cuota_fecha_alta":"2018-01-08 08:58:00.000","cliente_documento":1000,"cliente_nombre":"AXIS","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"axis.puto@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":1,"plan_estado":"en_proceso","plan_fecha_alta":"2018-01-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.273","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":2,"cuota_fecha_vencimiento":"2018-03-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-02-18 08:58:00.000","cuota_fecha_alta":"2018-02-09 12:00:00.000","cliente_documento":1000,"cliente_nombre":"AXIS","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"axis.puto@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":2,"plan_estado":"en_proceso","plan_fecha_alta":"2018-02-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.273","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-03-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-02-18 08:58:00.000","cuota_fecha_alta":"2018-02-08 08:58:00.000","cliente_documento":1000,"cliente_nombre":"AXIS","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"axis.puto@gmail.com","vehiculo_id":2,"vehiculo_tipo":"particular","vehiculo_nombre":"Gol","vehiculo_precio":10000,"vehiculo_marca":"volkswagen","vehiculo_modelo":"v3","vehiculo_color":"c2"}
        {"plan_id":3,"plan_estado":"en_proceso","plan_fecha_alta":"2018-03-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.273","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-04-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-03-18 08:58:00.000","cuota_fecha_alta":"2018-03-08 08:58:00.000","cliente_documento":1001,"cliente_nombre":"AXIS2","cliente_apellido":"Puto2","cliente_nro_telefono":"8888888","cliente_email":"axis2.puto@gmail.com","vehiculo_id":2,"vehiculo_tipo":"particular","vehiculo_nombre":"Gol","vehiculo_precio":10000,"vehiculo_marca":"volkswagen","vehiculo_modelo":"v3","vehiculo_color":"c2"}
        {"plan_id":4,"plan_estado":"en_proceso","plan_fecha_alta":"2018-04-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.273","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-05-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-05-18 08:58:00.000","cuota_fecha_alta":"2018-04-08 08:58:00.000","cliente_documento":100,"cliente_nombre":"Diego","cliente_apellido":"Maradona","cliente_nro_telefono":"101010","cliente_email":"diego.10@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":4,"plan_estado":"en_proceso","plan_fecha_alta":"2018-04-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.273","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":2,"cuota_fecha_vencimiento":"2018-06-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-03-18 08:58:00.000","cuota_fecha_alta":"2018-06-09 12:00:00.000","cliente_documento":100,"cliente_nombre":"Diego","cliente_apellido":"Maradona","cliente_nro_telefono":"101010","cliente_email":"diego.10@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        */
    }

    @Test
    public void test_ClientFactoryAdapterDynamicRest() throws Exception {
        final ClientFactoryAdapter clientFactoryAdapter =
                new ClientFactoryAdapter(ClientFactory.getInstance());

        final List<ConfigurarConcesionariaForm> configurarConcesionariaForms = dao.selectParamsByConcesionariaId(1L);

        configurarConcesionariaForms.forEach(System.out::println);

        final Optional<ConcesionariaServiceContract> clientFor = clientFactoryAdapter.getClientFor(configurarConcesionariaForms);
        assertTrue(clientFor.isPresent());

        final ConcesionariaServiceContract client = clientFor.get();

        assertTrue(client instanceof RestClient);

        /*
        client.consultarPlanes(identificador, "2018-01-08T20:58:00").forEach(System.out::println);
        ConfigurarConcesionariaForm{concesionariaId=1, configTecno='REST', configParam='url', value='http://localhost:8001/concesionaria_rest_one/concesionariaRestOne'}
        http://localhost:8001/concesionaria_rest_one/concesionariaRestOne/consultarPlanes?identificador=GOBIERNO-INCENTIVO-2018&offset=2018-01-08T20:58:00
        http://localhost:8001/concesionaria_rest_one/concesionariaRestOne/consultarPlanes?identificador=GOBIERNO-INCENTIVO-2018&offset=2018-01-08T20:58:00
        {"plan_id":1,"plan_estado":"en_proceso","plan_fecha_alta":"2018-01-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.937","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-02-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-01-18 08:58:00.000","cuota_fecha_alta":"2018-01-08 08:58:00.000","cliente_documento":3000,"cliente_nombre":"REST","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"rest.puto@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":1,"plan_estado":"en_proceso","plan_fecha_alta":"2018-01-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.937","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":2,"cuota_fecha_vencimiento":"2018-03-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-02-18 08:58:00.000","cuota_fecha_alta":"2018-02-09 12:00:00.000","cliente_documento":3000,"cliente_nombre":"REST","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"rest.puto@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":2,"plan_estado":"en_proceso","plan_fecha_alta":"2018-02-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.937","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-03-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-02-18 08:58:00.000","cuota_fecha_alta":"2018-02-08 08:58:00.000","cliente_documento":3000,"cliente_nombre":"REST","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"rest.puto@gmail.com","vehiculo_id":2,"vehiculo_tipo":"particular","vehiculo_nombre":"Gol","vehiculo_precio":10000,"vehiculo_marca":"volkswagen","vehiculo_modelo":"v3","vehiculo_color":"c2"}
        {"plan_id":3,"plan_estado":"en_proceso","plan_fecha_alta":"2018-03-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.937","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-04-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-03-18 08:58:00.000","cuota_fecha_alta":"2018-03-08 08:58:00.000","cliente_documento":3003,"cliente_nombre":"REST2","cliente_apellido":"Puto2","cliente_nro_telefono":"8888888","cliente_email":"rest2.puto@gmail.com","vehiculo_id":2,"vehiculo_tipo":"particular","vehiculo_nombre":"Gol","vehiculo_precio":10000,"vehiculo_marca":"volkswagen","vehiculo_modelo":"v3","vehiculo_color":"c2"}
        {"plan_id":4,"plan_estado":"en_proceso","plan_fecha_alta":"2018-04-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.937","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-05-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-05-18 08:58:00.000","cuota_fecha_alta":"2018-04-08 08:58:00.000","cliente_documento":100,"cliente_nombre":"Diego","cliente_apellido":"Maradona","cliente_nro_telefono":"101010","cliente_email":"diego.10@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":4,"plan_estado":"en_proceso","plan_fecha_alta":"2018-04-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:07.937","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":2,"cuota_fecha_vencimiento":"2018-06-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-03-18 08:58:00.000","cuota_fecha_alta":"2018-06-09 12:00:00.000","cliente_documento":100,"cliente_nombre":"Diego","cliente_apellido":"Maradona","cliente_nro_telefono":"101010","cliente_email":"diego.10@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        */
    }

    @Test
    public void test_ClientFactoryAdapterDynamicCxf() throws Exception {
        final ClientFactoryAdapter clientFactoryAdapter =
                new ClientFactoryAdapter(ClientFactory.getInstance());

        final List<ConfigurarConcesionariaForm> configurarConcesionariaForms = dao.selectParamsByConcesionariaId(2L);

        configurarConcesionariaForms.forEach(System.out::println);

        final Optional<ConcesionariaServiceContract> clientFor = clientFactoryAdapter.getClientFor(configurarConcesionariaForms);
        assertTrue(clientFor.isPresent());

        final ConcesionariaServiceContract client = clientFor.get();

        assertTrue(client instanceof CXFClient);

        /*
        client.consultarPlanes(identificador, "2018-01-08T20:58:00").forEach(System.out::println);
        ConfigurarConcesionariaForm{concesionariaId=2, configTecno='CXF', configParam='wsdlUrl', value='http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl'}
        {"plan_id":1,"plan_estado":"en_proceso","plan_fecha_alta":"2018-01-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:08.647","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-02-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-01-18 08:58:00.000","cuota_fecha_alta":"2018-01-08 08:58:00.000","cliente_documento":2000,"cliente_nombre":"CXF","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"cxf.puto@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":1,"plan_estado":"en_proceso","plan_fecha_alta":"2018-01-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:08.647","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":2,"cuota_fecha_vencimiento":"2018-03-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-02-18 08:58:00.000","cuota_fecha_alta":"2018-02-09 12:00:00.000","cliente_documento":2000,"cliente_nombre":"CXF","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"cxf.puto@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":2,"plan_estado":"en_proceso","plan_fecha_alta":"2018-02-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:08.647","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-03-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-02-18 08:58:00.000","cuota_fecha_alta":"2018-02-08 08:58:00.000","cliente_documento":2000,"cliente_nombre":"CXF","cliente_apellido":"Puto","cliente_nro_telefono":"4444444","cliente_email":"cxf.puto@gmail.com","vehiculo_id":2,"vehiculo_tipo":"particular","vehiculo_nombre":"Gol","vehiculo_precio":10000,"vehiculo_marca":"volkswagen","vehiculo_modelo":"v3","vehiculo_color":"c2"}
        {"plan_id":3,"plan_estado":"en_proceso","plan_fecha_alta":"2018-03-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:08.647","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-04-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-03-18 08:58:00.000","cuota_fecha_alta":"2018-03-08 08:58:00.000","cliente_documento":2002,"cliente_nombre":"CXF2","cliente_apellido":"Puto2","cliente_nro_telefono":"8888888","cliente_email":"cxf2.puto@gmail.com","vehiculo_id":2,"vehiculo_tipo":"particular","vehiculo_nombre":"Gol","vehiculo_precio":10000,"vehiculo_marca":"volkswagen","vehiculo_modelo":"v3","vehiculo_color":"c2"}
        {"plan_id":4,"plan_estado":"en_proceso","plan_fecha_alta":"2018-04-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:08.647","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":1,"cuota_fecha_vencimiento":"2018-05-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-05-18 08:58:00.000","cuota_fecha_alta":"2018-04-08 08:58:00.000","cliente_documento":100,"cliente_nombre":"Diego","cliente_apellido":"Maradona","cliente_nro_telefono":"101010","cliente_email":"diego.10@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"}
        {"plan_id":4,"plan_estado":"en_proceso","plan_fecha_alta":"2018-04-08 08:58:00.000","plan_fecha_ultima_actualizacion":"2018-12-09 11:56:08.647","plan_tipo_de_plan":"GOBIERNO-INCENTIVO-2018","cuota_nro_cuota":2,"cuota_fecha_vencimiento":"2018-06-08 11:59:59.000","cuota_monto":100,"cuota_fecha_pago":"2018-03-18 08:58:00.000","cuota_fecha_alta":"2018-06-09 12:00:00.000","cliente_documento":100,"cliente_nombre":"Diego","cliente_apellido":"Maradona","cliente_nro_telefono":"101010","cliente_email":"diego.10@gmail.com","vehiculo_id":1,"vehiculo_tipo":"taxi","vehiculo_nombre":"Corsa","vehiculo_precio":10000,"vehiculo_marca":"chevrolet","vehiculo_modelo":"v1","vehiculo_color":"c1"} */
    }
}
