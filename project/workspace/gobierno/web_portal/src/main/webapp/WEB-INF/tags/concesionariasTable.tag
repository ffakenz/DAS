<%@ tag language="java" pageEncoding="ISO-8859-1" %>

<%@ tag import="ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager" %>
<%@ tag import="ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao" %>
<%@ tag import="ar.edu.ubp.das.mvc.config.DatasourceConfig" %>
<%@ tag import="ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm" %>
<%@ tag import="java.util.List" %>
<%@ tag import="java.sql.SQLException" %>
<%@ tag import="ar.edu.ubp.das.src.utils.Constants" %>
<%@ tag import="ar.edu.ubp.das.src.utils.FrontUtils" %>

<%@ attribute name="idTable" required="true" %>
<%
    StringBuilder result = new StringBuilder();

    try {

        DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

        ConcesionariasManager concesionariasManager = new ConcesionariasManager(msConcesionariasDao);
        List<ConcesionariaForm> concesionariaFormList = concesionariasManager.getDao().select();

        StringBuilder rows = new StringBuilder();
        for( ConcesionariaForm c : concesionariaFormList) {
            String row = FrontUtils.concesionariaFormRow(c);
            rows.append(row);
        }

        result.append("<table id=\""+idTable+"\" class=\"table table-striped table-bordered\" style=\"width:100%\">");
        result.append("<thead>                                                                                     ");
        result.append("<tr>                                                                                        ");
        result.append("   <th>Id</th>                                                                              ");
        result.append("   <th>Nombre</th>                                                                          ");
        result.append("   <th>Cuit</th>                                                                            ");
        result.append("   <th>Codigo</th>                                                                          ");
        result.append("   <th>Email</th>                                                                           ");
        result.append("   <th>Direccion</th>                                                                       ");
        result.append("   <th>Tel</th>                                                                             ");
        result.append("   <th>FechaAlta</th>                                                                       ");
        result.append("   <th>FechaRegistracion</th>                                                               ");
        result.append("   <th>Action</th>                                                                          ");
        result.append("   <th>Configurar</th>                                                                      ");
        result.append("</tr>                                                                                       ");
        result.append("</thead>                                                                                    ");
        result.append("<tbody>                                                                                     ");
        result.append(    rows.toString()                                                                           );
        result.append("</tbody>                                                                                    ");
        result.append("</table>                                                                                    ");


    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<%= result.toString() %>