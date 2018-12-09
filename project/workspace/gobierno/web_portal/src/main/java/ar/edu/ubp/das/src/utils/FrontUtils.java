package ar.edu.ubp.das.src.utils;

import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import clients.factory.ClientType;

import java.util.List;
import java.util.function.Predicate;

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
            sb.append("<button")
              .append(" id=\""+id+"\"")
              .append(" class=\""+clase+"\">")
              .append(description)
              .append("</button>");
        return sb.toString();
    }

    public static String getInput(String type, String id, String clase, String name, String value, String label) {
        StringBuilder sb = new StringBuilder();

        sb.append("<label").append(" for=\""+id+"\">")
                    .append(label)
                .append("</label>");
        sb.append("<input")
                .append(" type=\""+type+"\"")
                .append(" id=\""+id+"\"")
                .append(" class=\""+clase+"\"")
                .append(" name=\""+name+"\"")
                .append(" value=\""+value+"\"")
                .append("/>");
        return sb.toString();
    }

    public static String makeFormConfigTecnologica(List<ConfigurarConcesionariaForm> configsParams) {
        String configTecno = configsParams.get(0).getConfigTecno();

        Predicate<String> isAxisSelected = cnfg -> cnfg.equals(ClientType.AXIS.getName());
        Predicate<String> isCxfSelected = cnfg -> cnfg.equals(ClientType.CXF.getName());
        Predicate<String> isRestSelected = cnfg -> cnfg.equals(ClientType.REST.getName());

        StringBuilder sb = new StringBuilder();
        sb.append("<select>")
                .append("<option value="+
                        ClientType.REST.getName()+
                        (isRestSelected.test(configTecno) ? " selected " : " ") +
                        ">"+
                        ClientType.REST.getName()+
                        "</option>")
                .append("<option value="+
                        ClientType.AXIS.getName()+">"+
                        ClientType.AXIS.getName() +
                        (isAxisSelected.test(configTecno) ? " selected " : " ") +
                        "</option>")
                .append("<option value="+
                        ClientType.CXF.getName()+
                        (isCxfSelected.test(configTecno) ? " selected " : " ") +
                        ">"+
                        ClientType.CXF.getName()+
                        "</option>")
            .append("</select>");


        if(configsParams.isEmpty()) {
            sb.append(getInput(
                    ClientType.REST.getName(),
                    "config_param-url",
                    "config_param",
                    "url",
                    "",
                    "Param url"
            ));
        } else {
            for(ConfigurarConcesionariaForm config: configsParams) {
                sb.append(getInput(
                        config.getConfigTecno(),
                        "config_param-" + config.getConfigParam(),
                        "config_param",
                        config.getName(),
                        config.getValue(),
                        "Param " + config.getName()
                ));
            }
        }

        return sb.toString();
    }
}
