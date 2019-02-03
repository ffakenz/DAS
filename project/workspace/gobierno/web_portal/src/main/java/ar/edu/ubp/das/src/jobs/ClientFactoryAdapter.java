package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import clients.ConcesionariaServiceContract;
import clients.factory.ClientType;
import clients.factory.IClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientFactoryAdapter {
    private static final Logger log = LoggerFactory.getLogger(ClientFactoryAdapter.class);

    private IClientFactory clientFactory;

    public ClientFactoryAdapter(final IClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }


    public Optional<ConcesionariaServiceContract> getClientForConcesionaria(
            final Long concesionariaId,
            final ConfigurarConcesionariaManager configurarConcesionariaManager
    ) {
        try {
            final List<ConfigurarConcesionariaForm> configurarConcesionariaForms =
                    configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(concesionariaId);
            if (configurarConcesionariaForms.isEmpty()) {
                return Optional.empty();
            }
            return getClientFor(configurarConcesionariaForms);
        } catch (final SQLException ex) {
            ex.printStackTrace();
            log.error("getClientForConcesionaria [FAILED] for [ConcesionariaId {}]", concesionariaId);
            return Optional.empty();
        }
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
