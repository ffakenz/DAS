package ar.edu.ubp.das.mvc.config;

import java.util.HashMap;
import java.util.Map;

public final class ActionConfig {

    private String path;
    private String type;
    private String form;
    private boolean validate;
    private boolean noForward;
    private Map<String, ParameterConfig> parameters;
    private Map<String, ForwardConfig> forwards;
    private boolean json;

    public ActionConfig() {
        this.validate = false;
        this.noForward = false;
        this.parameters = new HashMap<>();
        this.forwards = new HashMap<>();
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getForm() {
        return form;
    }

    public boolean isValidate() {
        return validate;
    }

    public boolean isNoForward() {
        return noForward;
    }

    public ParameterConfig getParameterByName(final String name) {
        if (this.parameters.containsKey(name)) {
            return this.parameters.get(name);
        }
        return null;
    }

    public ForwardConfig getForwardByName(final String name) {
        if (this.forwards.containsKey(name)) {
            return this.forwards.get(name);
        }
        return null;
    }

    public Map<String, ParameterConfig> getParameters() {
        return parameters;
    }

    public Map<String, ForwardConfig> getForwards() {
        return forwards;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setForm(final String form) {
        this.form = form;
    }

    public void setValidate(final String validate) {
        this.validate = validate.isEmpty() ? false : validate.equals("true") ? true : false;
    }

    public void setNoForward(final String noForward) {
        this.noForward = noForward.isEmpty() ? false : noForward.equals("true") ? true : false;
    }

    public void addParameter(final ParameterConfig parameter) {
        this.parameters.put(parameter.getName(), parameter);
    }

    public void addForward(final ForwardConfig forward) {
        this.forwards.put(forward.getName(), forward);
    }

    @Override
    public String toString() {
        return "ActionConfig [path=" + path + ", type=" + type + ", form="
                + form + ", validate=" + validate + ", noForward=" + noForward
                + ", parameters=" + parameters + ", forwards=" + forwards + "]";
    }

    public void setJson(final String json) {
        this.json = json.isEmpty() ? false : json.equals("true") ? true : false;
    }

    public boolean isJson() {
        return this.json;
    }
}
