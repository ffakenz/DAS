package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import clients.ConcesionariaServiceContract;
import clients.IClientFactory;
import clients.factory.ClientType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientFactoryAdapter {
    private IClientFactory clientFactory;

    public ClientFactoryAdapter(final IClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    public Optional<ConcesionariaServiceContract> getClientFor(final List<ConfigurarConcesionariaForm> configs) {
        final List<String> configTecnos =
                configs.stream()
                        .map(c -> c.getConfigTecno())
                        .distinct().collect(Collectors.toList());

        if (configTecnos.size() > 1)
            return Optional.empty();

        final Optional<String> configTecno = configTecnos.stream().findFirst();
        if (!configTecno.isPresent())
            return Optional.empty();

        final String cnfgTecno = configTecno.get();
        final ClientType clientType = ClientType.valueOf(cnfgTecno);
        final Map<String, String> params =
                configs.stream().collect(Collectors.toMap(
                        ConfigurarConcesionariaForm::getConfigParam,
                        ConfigurarConcesionariaForm::getValue
                ));

        return clientFactory.getClientFor(clientType, params);
    }
}
