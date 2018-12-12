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
import java.util.List;
import java.util.Optional;

import static utils.MiddlewareConstants.*;

public class TestConfigInteractor implements Interactor<String> {

    public TestConfigInteractor() {
    }

    @Override
    public InteractorResponse<String> execute(final DynaActionForm form) {

        final List<ConfigurarConcesionariaForm> concesionariaFormList = formToConfigParams(form);

        if (concesionariaFormList.isEmpty())
            return new InteractorResponse<>(ResponseForward.WARNING, "FML-B");

        final ClientFactoryAdapter clientFactoryAdapter = new ClientFactoryAdapter(ClientFactory.getInstance());
        final Optional<ConcesionariaServiceContract> clientOpt = clientFactoryAdapter.getClientFor(concesionariaFormList);

        if (!clientOpt.isPresent())
            return new InteractorResponse<>(ResponseForward.FAILURE, "FML-C");

        final ConcesionariaServiceContract client = clientOpt.get();
        final Optional<String> identificador_test = client.health("GOBIERNO-INCENTIVO-2018");

        if (!identificador_test.isPresent())
            return new InteractorResponse<>(ResponseForward.FAILURE, "FML-D");

        return new InteractorResponse<>(ResponseForward.SUCCESS, identificador_test.get());
    }

    private List<ConfigurarConcesionariaForm> formToConfigParams(final DynaActionForm form) {

        final List<ConfigurarConcesionariaForm> list = new ArrayList<>();

        final String configTecno = form.isItemValid("configTecno").fst;
        final String concesionariaId = form.isItemValid("concesionariaId").fst;

        if (configTecno.equals(ClientType.AXIS.getName())) {
            final Pair<String, Boolean> targetNameSpace = form.isItemValid(AXIS_PARAM_TARGET);
            final Pair<String, Boolean> endpointUrl = form.isItemValid(AXIS_PARAM_ENDP_URL);
            if (targetNameSpace.snd && endpointUrl.snd) {
                list.add(new ConfigurarConcesionariaForm(Long.valueOf(concesionariaId), ClientType.AXIS.getName(), AXIS_PARAM_TARGET, targetNameSpace.fst));
                list.add(new ConfigurarConcesionariaForm(Long.valueOf(concesionariaId), ClientType.AXIS.getName(), AXIS_PARAM_ENDP_URL, endpointUrl.fst));
            }
        } else if (configTecno.equals(ClientType.REST.getName())) {
            final Pair<String, Boolean> url = form.isItemValid(REST_PARAM_URL);
            if (url.snd) {
                list.add(new ConfigurarConcesionariaForm(Long.valueOf(concesionariaId), ClientType.REST.getName(), REST_PARAM_URL, url.fst));
            }

        } else if (configTecno.equals(ClientType.CXF.getName())) {
            final Pair<String, Boolean> wsdlUrl = form.isItemValid(CXF_PARAM_WSDL_URL);
            if (wsdlUrl.snd) {

                list.add(new ConfigurarConcesionariaForm(Long.valueOf(concesionariaId), ClientType.CXF.getName(), CXF_PARAM_WSDL_URL, wsdlUrl.fst));
            }
        }
        return list;
    }
}
