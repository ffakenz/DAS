package scenarios;

import dbaccess.config.DatasourceConfig;
import dbaccess.config.ModuleConfigImpl;
import utils.ScenarioBuilder;
import utils.ScenarioRunner;

public class ScenarioExecutor {
    public static void main(final String[] args) {
        final ScenarioRunner runner = new ScenarioRunner();

        ModuleConfigImpl.load(ScenarioExecutor.class.getClassLoader());
        final DatasourceConfig config = ModuleConfigImpl.getDefaultDatasource();
        final ScenarioBuilder builder = ScenarioBuilder.getInstance(config);

        runner.run(builder);
    }
}
