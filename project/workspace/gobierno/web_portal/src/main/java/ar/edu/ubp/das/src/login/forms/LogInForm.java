package ar.edu.ubp.das.src.login.forms;

import annotations.Column;
import annotations.Entity;
import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Timestamp;

@Entity
public class LogInForm extends DynaActionForm {
    @Column(name = "id")
    private Long id; // TODO: change id to UUID
    @Column(name = "username")
    private String username;
    @Column(name = "log_in_time")
    private Timestamp loginTime;
    @Column(name = "log_out_time")
    private Timestamp logoutTime;

    public LogInForm() {
    }

    public LogInForm(final String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(final Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(final Timestamp logoutTime) {
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
