package interactors.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioManager;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UsuarioManagmentTest {
    MSUsuariosDao msUsuariosDao;
    UsuarioManager usuarioManager;

    @Before
    public void setup() {
        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");

        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

//        interactor = new LoginInteractor(loginDao, msUsuariosDao);
        usuarioManager = new UsuarioManager(msUsuariosDao);
    }

    @Test
    public void test01() throws SQLException {
        // Verify we can retrieve a valid user from db

        final UsuarioForm usuarioForm = new UsuarioForm("pepe", "123");
//        usuarioManager
    }


}
