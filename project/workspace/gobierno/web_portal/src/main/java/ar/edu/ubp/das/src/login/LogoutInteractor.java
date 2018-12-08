package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.model.LoginManager;

import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.SSID;


public class LogoutInteractor implements Interactor<Long> {

    private LoginManager loginManager;

    public LogoutInteractor(final DaoImpl loginDao) {
        this.loginManager = new LoginManager(loginDao);
    }

    @Override
    public InteractorResponse execute(final DynaActionForm form) throws SQLException {

        final Pair<String, Boolean> ssid = form.isItemValid(SSID);

        if (!ssid.snd)
            return new InteractorResponse<>(ResponseForward.WARNING); // Some error occur with ssid

        loginManager.logout(new LogInForm(Long.parseLong(ssid.fst)));

        return new InteractorResponse(ResponseForward.SUCCESS);
    }

}
