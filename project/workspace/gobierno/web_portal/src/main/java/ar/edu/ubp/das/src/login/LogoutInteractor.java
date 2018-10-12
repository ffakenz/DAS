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


public class LogoutInteractor implements Interactor<Long> {

    private LoginManager loginManager;

    public LogoutInteractor(final DaoImpl loginDao) {
        this.loginManager = new LoginManager(loginDao);
    }

    @Override
    public InteractorResponse execute(final DynaActionForm form) throws SQLException {
        final Pair<String, Boolean> username = form.isItemValid("username");

        if (!username.snd)
            return new InteractorResponse<>(ResponseForward.WARNING); // Some error occur with username

        loginManager.logout(form.convertTo(LogInForm.class));

        // if we are here it means the logout was successfully or the user was already logged out
        return new InteractorResponse(ResponseForward.SUCCESS);
    }

}
