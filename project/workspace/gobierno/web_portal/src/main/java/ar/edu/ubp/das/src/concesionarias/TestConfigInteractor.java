package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import clients.ConcesionariaServiceContract;
import clients.factory.ClientFactory;
import clients.factory.ClientType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static utils.MiddlewareConstants.*;

public class TestConfigInteractor implements Interactor<Boolean> {

    public TestConfigInteractor() {}

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) {

        final Pair<String, Boolean> configTecno = form.isItemValid("tecno");

        if(!configTecno.snd)
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        HashMap<String, String> params = new HashMap<>();

        if(!areParamsValid(form, configTecno.fst, params))
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        List<ConfigurarConcesionariaForm> concesionariaFormList = new ArrayList<>();

        params.forEach( (k, v) ->
            concesionariaFormList.add(new ConfigurarConcesionariaForm(configTecno.fst, k, v))
        );

        ClientFactoryAdapter clientFactoryAdapter = new ClientFactoryAdapter(ClientFactory.getInstance());
        Optional<ConcesionariaServiceContract> clientOpt = clientFactoryAdapter.getClientFor(concesionariaFormList);

        if(!clientOpt.isPresent())
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        final ConcesionariaServiceContract client = clientOpt.get();
        client.health("identificador_test");

        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }

    private boolean areParamsValid(DynaActionForm form, String configTecno, HashMap<String, String> params) {

        if (configTecno.equals(ClientType.AXIS.getName())) {

            final Pair<String, Boolean> targetNameSpace = form.isItemValid(AXIS_PARAM_TARGET);
            final Pair<String, Boolean> endpointUrl = form.isItemValid(AXIS_PARAM_ENDP_URL);

            if (targetNameSpace.snd && endpointUrl.snd) {
                params.put(AXIS_PARAM_TARGET, targetNameSpace.fst);
                params.put(AXIS_PARAM_ENDP_URL, endpointUrl.fst);
                return true;
            }
        } else if (configTecno.equals(ClientType.REST.getName())) {

            final Pair<String, Boolean> url = form.isItemValid("url");

            if(url.snd) {
                params.put(REST_PARAM_URL, url.fst);
                return true;
            }

        } else if (configTecno.equals(ClientType.CXF.getName())) {
            final Pair<String, Boolean> wsdlUrl = form.isItemValid("wsdlUrl");

            if(wsdlUrl.snd) {
                params.put(CXF_PARAM_WSDL_URL, wsdlUrl.fst);
                return true;
            }

        }

        return false;
    }
}
