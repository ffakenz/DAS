package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.jobs.consumo.forms.ViewConsumoResultsForm;

import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.VIEW_CONSUMO_RESULTS_ROW;

public class FrontUtils {


    public static String listOfConcesionarias(final List<ConcesionariaForm> concList) {

        final StringBuilder lista = new StringBuilder();
        lista.append("<ul id=\"conc_aprobadas_ul\" class=\"list-group\">");
        concList.forEach(c -> lista.append("<li class=\"list-group-item\">" + c.getNombre() + "</li>"));
        lista.append("</ul>");

        return lista.toString();
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
