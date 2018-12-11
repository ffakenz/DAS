package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import clients.factory.ClientType;

import java.util.HashMap;

import static utils.MiddlewareConstants.AXIS_PARAM_ENDP_URL;
import static utils.MiddlewareConstants.AXIS_PARAM_TARGET;

public class TestConfigInteractor implements Interactor<Boolean> {

    public TestConfigInteractor() {}

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) {

        final Pair<String, Boolean> configTecno = form.isItemValid("tecno");

        if(!configTecno.snd)
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        HashMap<String, String> params = new HashMap<>();

        if (configTecno.equals(ClientType.AXIS)) {
            final Pair<String, Boolean> targetNameSpace = form.isItemValid(AXIS_PARAM_TARGET);
            final Pair<String, Boolean> endpointUrl = form.isItemValid(AXIS_PARAM_ENDP_URL);

            if(targetNameSpace.snd && endpointUrl.snd) {
//                params.put("")
            }

        } else if (configTecno.equals(ClientType.REST)) {

            final Pair<String, Boolean> url = form.isItemValid("url");

            if(url.snd)
                params.put("url", url.fst);

        } else if (configTecno.equals(ClientType.CXF)) {
            final Pair<String, Boolean> wsdlUrl = form.isItemValid("wsdlUrl");

        } else return new InteractorResponse<>(ResponseForward.WARNING, false);


        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
