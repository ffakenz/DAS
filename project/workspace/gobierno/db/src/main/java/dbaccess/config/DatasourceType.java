package dbaccess.config;

public enum DatasourceType {
    DEFAULT {
        @Override
        public String getValue() {
            return "default";
        }
    };

    public abstract String getValue();
}
