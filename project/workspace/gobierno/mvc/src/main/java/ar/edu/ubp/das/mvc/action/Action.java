package ar.edu.ubp.das.mvc.action;

import ar.edu.ubp.das.mvc.config.ForwardConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Enumeration;

public interface Action {

    ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException;

    Logger log = LoggerFactory.getLogger(Action.class);


    default void logRequest(final HttpServletRequest request) {
        log.info("[HttpServletRequest - URL] = {}", request.getRequestURL().toString());
        log.info("[HttpServletRequest - QUERY] = {}", request.getQueryString());
        log.info("[HttpServletRequest - METHOD] = {}", request.getMethod());
        log.info("[HttpServletRequest - SESSION] = {}", request.getSession().toString());
        final Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final String attributeName = attributeNames.nextElement();
            final Object attribute = request.getAttribute(attributeName);
            log.info("[HttpServletRequest - ATTRIBUTE_NAME] = {}", attributeName);
            log.info("[HttpServletRequest - ATTRIBUTE] = {}", attribute.toString());
        }
    }

    default void logResponse(final HttpServletResponse response) {
        log.info("[HttpServletResponse - STATUS] = {}", response.getStatus());
    }

    default void logAction(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) {
        log.info("[ActionMapping] = {}", mapping.toString());
        log.info("[DynaActionForm] = {}", form.toString());
        logRequest(request);
        logResponse(response);
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
