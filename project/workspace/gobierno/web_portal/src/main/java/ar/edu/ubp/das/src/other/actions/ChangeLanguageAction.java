package ar.edu.ubp.das.src.other.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLanguageAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {
        final Locale requestLocale = request.getLocale();

        final String country = requestLocale.getCountry();
        final String displayCountry = requestLocale.getDisplayCountry();
        final String language = requestLocale.getLanguage();
        final String displayLanguage = requestLocale.getDisplayLanguage();
        final String iso3Country = requestLocale.getISO3Country();
        final String iso3Language = requestLocale.getISO3Language();

        log.info("ChangeLanguageAction " +
                        "[country = {}],[displayCountry = {}]," +
                        "[language = {}],[displayLanguage = {}]," +
                        "[iso3Country = {}],[iso3Language = {}]",
                country, displayCountry, language, displayLanguage, iso3Country, iso3Language);


        final HttpSession session = request.getSession();
        session.setAttribute("idioma", form.getItem("idioma").orElse("en"));

        logAction(mapping, form, request, response);
        return mapping.getForwardByName("success");
    }
}
