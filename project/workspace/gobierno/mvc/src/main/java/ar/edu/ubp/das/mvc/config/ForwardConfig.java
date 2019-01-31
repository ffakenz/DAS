package ar.edu.ubp.das.mvc.config;

public final class ForwardConfig {

    private String name;
    private String path;
    private boolean redirect;

    private String json;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public void setRedirect(final String redirect) {
        this.redirect = !redirect.isEmpty() && redirect.equals("true");
    }

    @Override
    public String toString() {
        return "ForwardConfig [name=" + name + ", path=" + path + ", redirect="
                + redirect + "]";
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(final String json) {
        this.json = json;
    }

    public boolean isJson() {
        return this.json != null;
    }
}
