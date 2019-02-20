package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.util.List;

public class FrontUtils {

    public static String listOfConcesionarias(final List<ConcesionariaForm> concList) {
        final StringBuilder lista = new StringBuilder();
        lista.append("<ul id=\"conc_aprobadas_ul\" class=\"list-group\">");
        concList.forEach(c -> lista.append("<li class=\"list-group-item\">" + c.getNombre() + "</li>"));
        lista.append("</ul>");

        return lista.toString();
    }

    public static String emptyIfNull(final Object value) {
        return value == null ? "" : value.toString();
    }
}
