package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoParamsForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.GeneralConfigConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ConfigurarConcesionariaInteractor implements Interactor<Boolean> {

    ConfigurarConcesionariaManager configurarConcesionariaManager;
    ConcesionariasManager concesionariasManager;

    public ConfigurarConcesionariaInteractor(final DaoImpl msConfigurarConcesionariaDao,
                                             final DaoImpl msConcesionariasDao) {

        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);
        this.concesionariasManager= new ConcesionariasManager(msConcesionariasDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(DynaActionForm form) throws SQLException {


        final Pair<String, Boolean> concesionariaId = form.isItemValid("concesionariaId");
        final Pair<String, Boolean> params = form.isItemValid("params");

        Boolean someIsMissing = Arrays.asList(concesionariaId, params)
                .stream().anyMatch(v -> v.snd == false);

        if(someIsMissing)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        GeneralConfigConcesionariaForm genConfigConcForm = form.convertTo(GeneralConfigConcesionariaForm.class);

        List<ConcesionariaForm> concesionariaForms = concesionariasManager.getDao().selectAprobadas();

        Boolean isApproved = concesionariaForms.stream()
                .anyMatch(c -> c.getFechaAlta().equals(genConfigConcForm.getConcesionariaId()));

        if(!isApproved)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        ConfigurarConcesionariaForm configurarConcesionariaForm = new ConfigurarConcesionariaForm();
        configurarConcesionariaForm.setConcesionariaId(genConfigConcForm.getConcesionariaId());

        for(ConfigTecnoParamsForm c : genConfigConcForm.getParams()) {

            configurarConcesionariaForm.setConfigParam(c.getConfigParam());
            configurarConcesionariaForm.setConfigTecno(c.getConfigTecno());
            configurarConcesionariaForm.setValue(c.getValue());

            configurarConcesionariaManager.getDao().insert(configurarConcesionariaForm);

        }

        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
