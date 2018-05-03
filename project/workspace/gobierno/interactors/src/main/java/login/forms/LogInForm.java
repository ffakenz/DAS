package login.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Date;

public class LogInForm extends DynaActionForm {
    private Long id;
    private String username;
    private Date loginTime;
    private Date logoutTime;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    @Override
    public String toString() {
        return "LogInForm{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                '}';
    }
}
