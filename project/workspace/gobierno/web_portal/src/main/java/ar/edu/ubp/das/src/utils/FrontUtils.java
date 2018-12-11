package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import clients.factory.ClientType;

import java.util.List;
import java.util.function.Predicate;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class FrontUtils {

    public static String concesionariaFormRow(final ConcesionariaForm c) {

        final StringBuilder rows = new StringBuilder();
        final String clase = c.getCodigo() == null ? CONCESIONARIA_NO_APROBADA : CONCESIONARIA_APROBADA;
        rows.append("<tr id=\"" + CONCESIONARIA_ROW + "-" + c.getId() + "\" class=" + clase + ">");
        rows.append("<td>" + c.getId() + "</td>");
        rows.append("<td>" + c.getNombre() + "</td>");
        rows.append("<td>" + c.getCuit() + "</td>");
        rows.append("<td>" + c.getCodigo() + "</td>");
        rows.append("<td>" + c.getEmail() + "</td>");
        rows.append("<td>" + c.getDireccion() + "</td>");
        rows.append("<td>" + c.getTel() + "</td>");
        rows.append("<td>" + c.getFechaAlta() + "</td>");
        rows.append("<td>" + c.getFechaRegistracion() + "</td>");
        if (clase.equals(CONCESIONARIA_NO_APROBADA)) {
            final String aprobarBtn = getButton(BTN_APROBAR + "-" + c.getId(), BTN_APROBAR, "Aprobar");
            rows.append("<td>")
                    .append(aprobarBtn)
                    .append("</td>");
        } else {
            final String desAprobarBtn = getButton(BTN_DESAPROBAR + "-" + c.getId(), BTN_DESAPROBAR, "DesAprobar");
            rows.append("<td>")
                    .append(desAprobarBtn)
                    .append("</td>");
        }
        final String configBtn = getButton(BTN_CONFIGURAR + "-" + c.getId(), BTN_CONFIGURAR, "Configurar");
        rows.append("<td>")
                .append(configBtn)
                .append("</td>");
        rows.append("</tr>");
        return rows.toString();
    }

    public static String getButton(final String id, final String clase, final String description) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<button type=\"button\"")
                .append(" id=\"" + id + "\"")
                .append(" class=\"" + clase + "\">")
                .append(description)
                .append("</button>");
        return sb.toString();
    }

    public static String getInput(final String type, final String id, final String clase, final String name, final String value, final String label) {
        final StringBuilder sb = new StringBuilder();

        sb.append("<label class=\"col-md-4 control-label\"").append(" for=\"" + id + "\">")
                .append(label)
                .append("</label>");
        sb.append("<input")
                .append(" type=\"" + type + "\" ")
                .append(" id=\"" + id + "\" ")
                .append(" class=\"form-control form-control-lg " + clase + "\" ")
                .append(" name=\"" + name + "\" ")
                .append(" value=\"" + value + "\" ")
                .append(" size=120 ")
                .append(" required/>");
        return sb.toString();
    }

    public static String makeFormConfigTecnologica(final List<ConfigurarConcesionariaForm> configsParams) {
        final String configTecno = configsParams.get(0).getConfigTecno();
        final Long concId = configsParams.get(0).getConcesionariaId();
        final Predicate<String> isAxisSelected = cnfg -> cnfg.equals(ClientType.AXIS.getName());
        final Predicate<String> isCxfSelected = cnfg -> cnfg.equals(ClientType.CXF.getName());
        final Predicate<String> isRestSelected = cnfg -> cnfg.equals(ClientType.REST.getName());

        final StringBuilder sb = new StringBuilder();
        sb.append("<form id=\"" + UPDATE_CONFIG_FORM + "\" class=\"form-horizontal\" method=\"post\" >");
        sb.append("<div id=\"" + INNER_UPDATE_CONFIG_FORM_DIV + "\" >");
        sb.append("<div class=\"form-group\">");
        sb.append("<select id=\"" + UPDATE_CONFIG_SELECT + "\">")
                .append("<option value=" +
                        ClientType.REST.getName() +
                        (isRestSelected.test(configTecno) ? " selected " : " ") + ">" +
                        ClientType.REST.getName() +
                        "</option>")
                .append("<option value=" +
                        ClientType.AXIS.getName() +
                        (isAxisSelected.test(configTecno) ? " selected " : " ") + ">" +
                        ClientType.AXIS.getName() +
                        "</option>")
                .append("<option value=" +
                        ClientType.CXF.getName() +
                        (isCxfSelected.test(configTecno) ? " selected " : " ") + ">" +
                        ClientType.CXF.getName() +
                        "</option>")
                .append("</select>");
        sb.append("</div>");


        sb.append("<div id=\"config_tecno_params_div\">");
        if (configsParams.isEmpty()) {
            sb.append("<div class=\"form-group\">");
            sb.append(getInput(
                    "text",
                    "url",
                    "config_param",
                    "url",
                    "",
                    "Param url"
            ));
            sb.append("</div>");
        } else {
            for (final ConfigurarConcesionariaForm config : configsParams) {
                sb.append("<div class=\"form-group\">");
                sb.append(getInput(
                        "text",
                        config.getConfigParam(),
                        "config_param",
                        config.getConfigParam(),
                        config.getValue(),
                        "Param " + config.getConfigParam() + " "
                ));

                sb.append("</div>");
            }
        }
        sb.append("</div>");

        sb.append("<div class=\"form-group\">")
                .append(getButton(BTN_UPDATE_CONFIG, BTN_UPDATE_CONFIG, "Update"))
                .append("</div>");

        sb.append("<div class=\"form-group\" id=\"config_test_div\">")
                .append(getButton(BTN_TEST_CONFIG, BTN_TEST_CONFIG, "Test"))
                .append("<label id=\"test_config_label\"></label>")
                .append("</div>");

        sb.append("</div>");
        sb.append("</form>");

        return sb.toString();
    }
}
