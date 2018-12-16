package ar.edu.ubp.das.src.login.forms;


import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;
import java.util.StringJoiner;

@Entity
public class LogInForm extends DynaActionForm {
    @Column(name = "id")
    private Long id; // TODO: change id to UUID
    @Column(name = "documento")
    private Long documento;
    @Column(name = "log_in_time")
    private Timestamp loginTime;
    @Column(name = "log_out_time")
    private Timestamp logoutTime;

    public LogInForm() {
    }

    public LogInForm(final Long ssid) {
        this.id = ssid;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
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
        return new StringJoiner(", ", LogInForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("documento=" + documento)
                .add("loginTime=" + loginTime)
                .add("logoutTime=" + logoutTime)
                .toString();
    }
}
