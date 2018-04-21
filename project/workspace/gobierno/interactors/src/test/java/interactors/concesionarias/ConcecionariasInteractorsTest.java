package interactors.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.AprobarInteractor;
import concesionarias.ConsultarAprobadasInteractor;
import concesionarias.RegistrarInteractor;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;
import mocks.MSConcesionariaDaoMock;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class ConcecionariasInteractorsTest {

    Dao daoLogin = new MSConcesionariaDaoMock();;
    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if(daoName.equals("Concesionarias") && daoPackage.equals("concesionarias")) {
            return daoLogin;
        }
        else return null;
    };


    @Test
    public void testAprobarConcecionaria(){
        Interactor a1 = new ConsultarAprobadasInteractor();

        // consultar aprobadas
        InteractorResponse r1 = a1.execute(null).apply(daoFactoryMock);
        // no hay aprobadas
        assert(r1.getResponse() == ResponseForward.WARNING);
        assert(((List<ConcesionariaForm>)r1.getResult()).isEmpty() == true);

        // creamos concecionaria a aprobar
        ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C9");
        concecionaria.setConfig("REST");
        // aprobar concesionaria
        Interactor a2 = new AprobarInteractor();
        InteractorResponse r2 =  a2.execute(concecionaria).apply(daoFactoryMock);
        // concesionaria no aprobada ya que no existe
        assert(r2.getResponse() == ResponseForward.FAILURE);
        assert(((Optional<String>)r2.getResult()).isPresent() == false);


        // registramos concesionaria
        Interactor a3 = new RegistrarInteractor();
        InteractorResponse r3 = a3.execute(concecionaria).apply(daoFactoryMock);
        // concesionaria registrada
        assert(r3.getResponse() == ResponseForward.SUCCESS);
        assert(((Optional<Long>)r3.getResult()).isPresent() == true);

        // aprobamos concesionaria
        InteractorResponse r4 =  a2.execute(concecionaria).apply(daoFactoryMock);
        // concesionaria aprobada
        assert(r4.getResponse() == ResponseForward.SUCCESS);
        assert(((Optional<String>)r4.getResult()).isPresent() == true);

        // consultamos aprobadas
        InteractorResponse r5 = a1.execute(null).apply(daoFactoryMock);
        // concesionaria aprobada
        assert(r5.getResponse() == ResponseForward.SUCCESS);
        assert(((List<ConcesionariaForm>)r5.getResult()).isEmpty() == false);
    }


}