package interactors.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.AprobarInteractor;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import mocks.MSConcesionariasDaoMock;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public class ConcecionariasInteractorsTest {

    Dao daoLogin = new MSConcesionariasDaoMock();
    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if (daoName.equals("Concesionarias") && daoPackage.equals("concesionarias")) {
            return daoLogin;
        } else return null;
    };


    @Test
    public void testAprobarConcecionaria() {
        final Interactor a1 = new ConsultarAprobadasInteractor();

        // consultar aprobadas
        final InteractorResponse r1 = a1.execute(null).apply(daoFactoryMock);
        // no hay aprobadas
        assertEquals(ResponseForward.SUCCESS, r1.getResponse());
        assertEquals(false, ((List<ConcesionariaForm>) r1.getResult()).isEmpty());
        assertEquals(2, ((List<ConcesionariaForm>) r1.getResult()).size());

        // creamos concecionaria a aprobar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("nombre", "C9");
        form.setItem("config", "REST");
        form.setItem("direccion", "La Tablada9 5739");
        form.setItem("cuit", "29-93337511-9");
        form.setItem("tel", "+5493513059169");
        form.setItem("email", "c9@gmail.com");

        // aprobar concesionaria
        final Interactor a2 = new AprobarInteractor();
        final InteractorResponse r2 = a2.execute(form).apply(daoFactoryMock);
        // concesionaria no aprobada ya que no existe
        assertEquals(ResponseForward.WARNING, r2.getResponse());
        assertEquals(false, ((Optional<String>) r2.getResult()).isPresent());

        // registramos concesionaria
        final Interactor a3 = new RegistrarInteractor();
        final InteractorResponse r3 = a3.execute(form).apply(daoFactoryMock);
        // concesionaria registrada
        assertEquals(ResponseForward.SUCCESS, r3.getResponse());
        assertEquals(true, ((Optional<Long>) r3.getResult()).isPresent());

        // aprobamos concesionaria
        form.setItem("id", ((Optional<Long>) r3.getResult()).get().toString());
        final InteractorResponse r4 = a2.execute(form).apply(daoFactoryMock);
        // concesionaria aprobada
        assertEquals(ResponseForward.SUCCESS, r4.getResponse());
        assertEquals(true, ((Optional<String>) r4.getResult()).isPresent());

        // consultamos aprobadas
        final InteractorResponse r5 = a1.execute(null).apply(daoFactoryMock);
        // concesionaria aprobada
        assertEquals(ResponseForward.SUCCESS, r5.getResponse());
        assertEquals(false, ((List<ConcesionariaForm>) r5.getResult()).isEmpty());
    }


    @Test
    public void testAprobarConcesionariaOk() {

        // creamos concecionaria a aprobar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("id", "3");

        // aprobar concesionaria
        final Interactor a2 = new AprobarInteractor();
        final InteractorResponse r2 = a2.execute(form).apply(daoFactoryMock);

        assertEquals(ResponseForward.SUCCESS, r2.getResponse());

    }

    @Test
    public void testAprobarConcesionariaFail() {

        // creamos concecionaria a aprobar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("id", "5");

        // aprobar concesionaria
        final Interactor a2 = new AprobarInteractor();
        final InteractorResponse r2 = a2.execute(form).apply(daoFactoryMock);

        assertEquals(ResponseForward.FAILURE, r2.getResponse());
    }


    @Test
    public void testRegistrarConcesionariaFail() {

        // creamos concecionaria a registrar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("nombre", "whatever");
        form.setItem("config", "LOL");
        form.setItem("direccion", "whatever");
        form.setItem("cuit", "CUIT11");
        form.setItem("tel", "whatever");
        form.setItem("email", "whatever");

        // aprobar concesionaria
        final Interactor a2 = new RegistrarInteractor();
        final InteractorResponse r2 = a2.execute(form).apply(daoFactoryMock);

        assertEquals(ResponseForward.FAILURE, r2.getResponse());
    }

}