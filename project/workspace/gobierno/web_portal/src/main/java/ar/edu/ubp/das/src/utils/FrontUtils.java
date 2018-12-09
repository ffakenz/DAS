package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class FrontUtils {
    public static String concesionariaFormRow(final ConcesionariaForm c) {
        StringBuilder rows = new StringBuilder();
        String clase = c.getCodigo() == null ? CONCESIONARIA_NO_APROBADA : CONCESIONARIA_APROBADA;
        rows.append("<tr id=\""+CONCESIONARIA_ROW+"-"+c.getId()+"\" class="+clase+">");
            rows.append("<td>"+c.getId()+"</td>");
            rows.append("<td>"+c.getNombre()+"</td>");
            rows.append("<td>"+c.getCuit()+"</td>");
            rows.append("<td>"+c.getCodigo()+"</td>");
            rows.append("<td>"+c.getEmail()+"</td>");
            rows.append("<td>"+c.getDireccion()+"</td>");
            rows.append("<td>"+c.getTel()+"</td>");
            rows.append("<td>"+c.getFechaAlta()+"</td>");
            rows.append("<td>"+c.getFechaRegistracion()+"</td>");
        if(clase.equals(CONCESIONARIA_NO_APROBADA)) {
            String aprobarBtn = getButton(BTN_APROBAR+"-"+c.getId(), BTN_APROBAR, "Aprobar");
            rows.append("<td>")
                    .append(aprobarBtn)
                    .append("</td>");
        } else {
            String desAprobarBtn = getButton(BTN_DESAPROBAR+"-"+c.getId(), BTN_DESAPROBAR, "DesAprobar");
            rows.append("<td>")
                    .append(desAprobarBtn)
                    .append("</td>");
        }
        String configBtn = getButton(BTN_CONFIGURAR+"-"+c.getId(), BTN_CONFIGURAR, "Configurar");
        rows.append("<td>")
            .append(configBtn)
            .append("</td>");
        rows.append("</tr>");
        return rows.toString();
    }

    public static String getButton(String id, String clase, String description) {
        StringBuilder sb = new StringBuilder();
            sb.append("<button id=\""+id+"\"")
              .append("class=\""+clase+"\">")
              .append(description)
              .append("</button>");
        return sb.toString();
    }
}
