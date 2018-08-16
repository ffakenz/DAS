package interactors.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.AprobarInteractor;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Ignore;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConcecionariasInteractorsTest {

    Dao daoLogin = new MSConcesionariasDao();

    @Ignore
    public void testAprobarConcecionaria() {
        final Interactor<List<ConcesionariaForm>> a1 = new ConsultarAprobadasInteractor();

        // consultar aprobadas
        final InteractorResponse<List<ConcesionariaForm>> r1 = a1.execute(null);
        // no hay aprobadas
        assertEquals(ResponseForward.SUCCESS, r1.getResponse());
        assertFalse(r1.getResult().get().isEmpty());

        // creamos concecionaria a aprobar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("nombre", "C9");
        form.setItem("config", "REST");
        form.setItem("direccion", "La Tablada9 5739");
        form.setItem("cuit", "29-93337511-9");
        form.setItem("tel", "+5493513059169");
        form.setItem("email", "c9@gmail.com");

        // aprobar concesionaria
        final Interactor<Long> a2 = new AprobarInteractor();
        final InteractorResponse<Long> r2 = a2.execute(form);
        // concesionaria no aprobada ya que no existe
        assertEquals(ResponseForward.WARNING, r2.getResponse());
        assertFalse(r2.getResult().isPresent());

        // registramos concesionaria
        final Interactor<Long> a3 = new RegistrarInteractor();
        final InteractorResponse<Long> r3 = a3.execute(form);
        // concesionaria registrada
        assertEquals(ResponseForward.SUCCESS, r3.getResponse());
        assertTrue(r3.getResult().isPresent());

        // aprobamos concesionaria
        form.setItem("id", r3.getResult().get().toString());
        final InteractorResponse<Long> r4 = a2.execute(form);
        // concesionaria aprobada
        assertEquals(ResponseForward.SUCCESS, r4.getResponse());
        assertTrue(r4.getResult().isPresent());

        // consultamos aprobadas
        final InteractorResponse<List<ConcesionariaForm>> r5 = a1.execute(null);
        // concesionaria aprobada
        assertEquals(ResponseForward.SUCCESS, r5.getResponse());
        assertFalse(r5.getResult().get().isEmpty());
    }


    @Ignore
    public void testAprobarConcesionariaOk() {

        // creamos concecionaria a aprobar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("id", "3");

        // aprobar concesionaria
        final Interactor a2 = new AprobarInteractor();
        final InteractorResponse r2 = a2.execute(form);

        assertEquals(ResponseForward.SUCCESS, r2.getResponse());

    }

    @Ignore
    public void testAprobarConcesionariaFail() {

        // creamos concecionaria a aprobar
        final DynaActionForm form = new DynaActionForm();
        form.setItem("id", "5");

        // aprobar concesionaria
        final Interactor a2 = new AprobarInteractor();
        final InteractorResponse r2 = a2.execute(form);

        assertEquals(ResponseForward.FAILURE, r2.getResponse());
    }


    @Ignore
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
        final InteractorResponse r2 = a2.execute(form);

        assertEquals(ResponseForward.FAILURE, r2.getResponse());
    }

}