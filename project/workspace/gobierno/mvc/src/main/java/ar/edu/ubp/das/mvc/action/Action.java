package ar.edu.ubp.das.mvc.action;

import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Action {

    ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException;

    Logger log = LoggerFactory.getLogger(Action.class);


    default void logRequest(final HttpServletRequest request) {
        log.info("[HttpServletRequest - ] = {}", request.getSession().toString());
    }
    default void logAction(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) {
        log.info("[ActionMapping] = {}", mapping.toString());
        log.info("[DynaActionForm] = {}", form.toString());
        logRequest(request);
        System.out.println();
    }


    default ForwardConfig jsonResult(final Object object) {
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                .create();
        final ForwardConfig forwardConfig = new ForwardConfig();
        final String json = gson.toJson(object);
        forwardConfig.setJson(json);
        return forwardConfig;
    }
}
