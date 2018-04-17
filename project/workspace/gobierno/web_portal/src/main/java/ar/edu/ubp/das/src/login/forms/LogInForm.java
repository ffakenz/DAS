package ar.edu.ubp.das.src.login.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Date;

public class LogInForm extends DynaActionForm {
    String tipo;
    String username;
    Date loginTime;
    Date logoutTime;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}
