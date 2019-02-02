package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;

import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class FrontUtils {

    public static String listOfConcesionarias(final List<ConcesionariaForm> concList) {

        final StringBuilder lista = new StringBuilder();

        lista.append("<ul id=\"conc_aprobadas_ul\" class=\"list-group\">");
        concList.forEach(c -> lista.append("<li class=\"list-group-item\">"+c.getNombre()+"</li>"));
        lista.append("</ul>");

        return lista.toString();
    }

    public static String estadoCuentasFormRow(final EstadoCuentasForm e) {
        final StringBuilder rows = new StringBuilder();
        rows.append("<tr id=\"" + ESTADO_CUENTA_ROW + "-" + e.getId() + "\">");
        rows.append("<td>" + e.getId() + "</td>");
        rows.append("<td>" + e.getEstado() + "</td>");
        rows.append("<td>" + e.getConcesionariaId() + "</td>");
        rows.append("<td>" + e.getNroPlanConcesionaria() + "</td>");
        rows.append("<td>" + e.getDocumentoCliente() + "</td>");
        rows.append("<td>" + e.getFechaAltaConcesionaria() + "</td>");
        rows.append("<td>" + e.getFechaAltaSistema() + "</td>");
        rows.append("<td>" + e.getVehiculo() + "</td>");
        rows.append("</tr>");
        return rows.toString();
    }

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
            final String aprobarBtn = getButton(APROBAR_BTN + "-" + c.getId(), APROBAR_BTN, "Aprobar");
            rows.append("<td>")
                    .append(aprobarBtn)
                    .append("</td>");
        } else {
            final String desAprobarBtn = getButton(DESAPROBAR_BTN + "-" + c.getId(), DESAPROBAR_BTN, "DesAprobar");
            rows.append("<td>")
                    .append(desAprobarBtn)
                    .append("</td>");
        }
        final String configBtn = getButton(CONFIG_BTN + "-" + c.getId(), CONFIG_BTN, "Configurar");
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
}
