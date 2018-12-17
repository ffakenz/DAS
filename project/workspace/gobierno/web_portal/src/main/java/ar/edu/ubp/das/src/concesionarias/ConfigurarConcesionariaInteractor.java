package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoParamsForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoXConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.GeneralConfigConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigTecnoParamManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.MiddlewareConstants.*;

public class ConfigurarConcesionariaInteractor implements Interactor<Boolean> {

    ConfigurarConcesionariaManager configurarConcesionariaManager;
    ConcesionariasManager concesionariasManager;
    ConfigTecnoParamManager configTecnoParamManager;

    public ConfigurarConcesionariaInteractor(final DaoImpl msConfigurarConcesionariaDao,
                                             final DaoImpl msConcesionariasDao,
                                             final DaoImpl msConfigTecnoParamDao) {

        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);
        this.concesionariasManager = new ConcesionariasManager(msConcesionariasDao);
        this.configTecnoParamManager = new ConfigTecnoParamManager(msConfigTecnoParamDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException {

        if (!isValid(form))
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        final GeneralConfigConcesionariaForm genConfigConcForm = convertTo(form);

        // TODO => this should be moved below next if ?
        configurarConcesionariaManager.getDao().invalidateParams(genConfigConcForm.getConcesionariaId());

        final ConfigTecnoXConcesionariaForm configTecnoXConc = new ConfigTecnoXConcesionariaForm();
        configTecnoXConc.setConcesionariaId(genConfigConcForm.getConcesionariaId());
        configTecnoXConc.setConfigTecnologica(genConfigConcForm.getParams().get(0).getConfigTecno());

        if (!configTecnoParamManager.getDao().valid(configTecnoXConc)) {
            // si no existe la config para la concesionaria la agregamos para que no rompa por foranea
            configTecnoParamManager.getDao().insert(configTecnoXConc);
        }

        for (final ConfigTecnoParamsForm c : genConfigConcForm.getParams()) {

            final ConfigurarConcesionariaForm configurarConcesionariaForm = new ConfigurarConcesionariaForm(
                    genConfigConcForm.getConcesionariaId(),
                    c.getConfigTecno(),
                    c.getConfigParam(),
                    c.getValue());

            configurarConcesionariaManager.getDao().insert(configurarConcesionariaForm);
        }

        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }

    public Boolean isValid(final DynaActionForm form) {

        final Pair<String, Boolean> concesionariaId = form.isItemValid("concesionariaId");
        final Pair<String, Boolean> techno = form.isItemValid("configTecno");

        if (!concesionariaId.snd) {
            return false;
        } else if (techno.fst.equals(AXIS)) {
            return form.isItemValid(AXIS_PARAM_ENDP_URL).snd && form.isItemValid(AXIS_PARAM_TARGET).snd;
        } else if (techno.fst.equals(REST)) {
            return form.isItemValid(REST_PARAM_URL).snd;
        } else if (techno.fst.equals(CXF)) {
            return form.isItemValid(CXF_PARAM_WSDL_URL).snd;
        }

        return false;
    }

    public GeneralConfigConcesionariaForm convertTo(final DynaActionForm form) {

        final Long concesionariaId = Long.parseLong(form.getItem("concesionariaId").get());
        final String techno = form.getItem("configTecno").get();


        final GeneralConfigConcesionariaForm generalConfigConcesionariaForm = new GeneralConfigConcesionariaForm();
        generalConfigConcesionariaForm.setConcesionariaId(concesionariaId);

        if (techno.equals(AXIS)) {
            generalConfigConcesionariaForm.setParams(
                    getConfigTecnoParam(techno, form, AXIS_PARAM_TARGET, AXIS_PARAM_ENDP_URL));
        } else if (techno.equals(REST)) {
            generalConfigConcesionariaForm.setParams(getConfigTecnoParam(techno, form, REST_PARAM_URL));
        } else if (techno.equals(CXF)) {
            generalConfigConcesionariaForm.setParams(getConfigTecnoParam(techno, form, CXF_PARAM_WSDL_URL));
        }

        return generalConfigConcesionariaForm;
    }

    private List<ConfigTecnoParamsForm> getConfigTecnoParam(final String techno, final DynaActionForm form, final String... params) {

        final List<ConfigTecnoParamsForm> configTecnoParamsFormList = new ArrayList<>();

        for (final String p : params) {

            final ConfigTecnoParamsForm configTecnoParamsForm = new ConfigTecnoParamsForm();
            configTecnoParamsForm.setConfigTecno(techno);
            configTecnoParamsForm.setConfigParam(p);
            configTecnoParamsForm.setValue(form.getItem(p).get());
            configTecnoParamsFormList.add(configTecnoParamsForm);
        }


        return configTecnoParamsFormList;
    }
}
