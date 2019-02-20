package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.jobs.consumo.forms.ViewConsumoResultsForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import java.sql.Timestamp;
import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class FrontUtils {


    public static String listOfConcesionarias(final List<ConcesionariaForm> concList) {

        final StringBuilder lista = new StringBuilder();
        lista.append("<ul id=\"conc_aprobadas_ul\" class=\"list-group\">");
        concList.forEach(c -> lista.append("<li class=\"list-group-item\">" + c.getNombre() + "</li>"));
        lista.append("</ul>");

        return lista.toString();
    }

    public static String sorteosFormRow(final SorteoForm sorteo) {

        final StringBuilder rows = new StringBuilder();
        rows.append("<tr id=\"" + SORTEO_ROW + "-" + sorteo.getId() + "\" >");
        rows.append("<td>" + sorteo.getEstado() + "</td>");
        rows.append("<td>" + sorteo.getFechaCreacion() + "</td>");
        rows.append("<td>" + emptyIfNull(sorteo.getFechaEjecucion()) + "</td>");
        rows.append("</tr>");
        return rows.toString();
    }

    public static String viewConsumoResultsFormRow(final ViewConsumoResultsForm view) {

        // todo: show and check possible null values
        final StringBuilder rows = new StringBuilder();
        rows.append("<tr id=\"" + VIEW_CONSUMO_RESULTS_ROW + "-" + view.getJobId() + "\" >");
        rows.append("<td>" + view.getJobId() + "</td>");
        rows.append("<td>" + view.getJobFechaEjecucion() + "</td>");
        rows.append("<td>" + view.getConsumoId() + "</td>");
        rows.append("<td>" + view.getConcesionariaId() + "</td>");
        rows.append("<td>" + view.getEstadoConsumo() + "</td>");
        rows.append("<td>" + view.getFromConsumo() + "</td>");
        rows.append("<td>" + view.getToConsumo() + "</td>");
        rows.append("<td>" + view.getIdRequestRespConsumo() + "</td>");
        rows.append("<td>" + view.getEstadoDescription() + "</td>");
        rows.append("<td>" + view.getConsumoResultId() + "</td>");
        rows.append("<td>" + view.getConsumoResult() + "</td>");
        rows.append("<td>" + view.getConsumoResultDescription() + "</td>");
        rows.append("</tr>");
        return rows.toString();
    }

    public static String estadoCuentasFormRow(final EstadoCuentasForm e) {

        // todo: implement procedure to obtain concesionaria name, and vehiculo name
        final StringBuilder rows = new StringBuilder();
        rows.append("<tr id=\"" + ESTADO_CUENTA_ROW + "-" + e.getId() + "\" >");
        rows.append("<td>" + e.getEstado() + "</td>");
        rows.append("<td>" + e.getConcesionariaId() + "</td>");
        rows.append("<td>" + e.getNroPlanConcesionaria() + "</td>");
        rows.append("<td>" + e.getDocumentoCliente() + "</td>");
        rows.append("<td>" + e.getFechaAltaConcesionaria() + "</td>");
        rows.append("<td>" + e.getVehiculo() + "</td>");
        rows.append("</tr>");
        return rows.toString();
    }

    public static String concesionariaFormRow(final ConcesionariaForm c) {

        final StringBuilder rows = new StringBuilder();
        final String clase = c.getCodigo() == null ? CONCESIONARIA_NO_APROBADA : CONCESIONARIA_APROBADA;
        rows.append("<tr id=\"" + CONCESIONARIA_ROW + "-" + c.getId() + "\" class=\"" + clase + "\">");
        rows.append("<td>" + c.getNombre() + "</td>");
        rows.append("<td>" + c.getCuit() + "</td>");
        rows.append("<td>" + emptyIfNull(c.getCodigo()) + "</td>");
        rows.append("<td>" + c.getEmail() + "</td>");
        rows.append("<td>" + c.getDireccion() + "</td>");
        rows.append("<td>" + c.getTel() + "</td>");
        rows.append("<td>" + c.getFechaAlta() + "</td>");
        rows.append("<td>" + c.getFechaRegistracion() + "</td>");
        if (clase.equals(CONCESIONARIA_NO_APROBADA)) {
            final String aprobarBtn = getButton(APROBAR_BTN + "-" + c.getId(), APROBAR_BTN + " btn btn-success", "Aprobar");
            rows.append("<td>")
                    .append(aprobarBtn)
                    .append("</td>");
        } else {
            final String desAprobarBtn = getButton(DESAPROBAR_BTN + "-" + c.getId(), DESAPROBAR_BTN + " btn btn-danger", "DesAprobar");
            rows.append("<td>")
                    .append(desAprobarBtn)
                    .append("</td>");
        }
        final String configBtn = getButton(CONFIG_BTN + "-" + c.getId(), CONFIG_BTN + " btn btn-info", "Configurar");
        rows.append("<td>")
                .append(configBtn)
                .append("</td>");
        rows.append("</tr>");
        return rows.toString();
    }

    public static String showDateFormatted(final Timestamp fecha) {
        //return "<fmt:formatDate dateStyle=\"full\" timeStyle=\"medium\" value=\"" + emptyIfNull(fecha) + "\" pattern=\"${pattern}\" />";
        return "<fmt:formatdate datestyle=\"full\" timestyle=\"medium\" value=\"2019-02-19 18:11:00.043\" pattern=\"yyyy/MM/d HH:mm\"></fmt:formatdate>";
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

    public static String emptyIfNull(final Object value) {
        return value == null ? "" : value.toString();
    }
}
