package interactors.concesionarias;

import ar.edu.ubp.das.mvc.db.Dao;
import mocks.MSUsuariosDaoMock;

import java.util.function.BiFunction;

public class ConcecionariaInteractorTest {

    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if(daoName.equals("Concecionarias") && daoPackage.equals("concecionarias")) {
            Dao dao = new MSUsuariosDaoMock();
            return dao;
        }
        else return null;
    };


}
