package dbaccess.config;

public enum DatasourceEnum {
    DEFAULT {
        @Override
        public String getValue() {
            return "default";
        }
    };

    public abstract String getValue();
}
