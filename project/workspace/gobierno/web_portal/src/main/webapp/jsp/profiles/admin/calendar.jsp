<%@ page

        language="java"
                contentType="text/html; charset=utf-8"
                pageEncoding="utf-8"
                %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<fmt:setLocale value="${lang}" scope="session" />

<%@ page import="ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.Exception" %>

<%@ page import="static ar.edu.ubp.das.src.utils.Constants.SORTEOS_LIST_RQST_ATTRIBUTE" %>
<%@ page import="ar.edu.ubp.das.src.utils.FrontUtils" %>
<%@ page import="ar.edu.ubp.das.src.utils.Month" %>
<%@ page import="ar.edu.ubp.das.src.utils.DateUtils" %>
<%@ page import="java.util.*,util.*,java.io.*,java.sql.Date" %>

<%-- TODO: CLEAN UP THE PAGE TAG ABOVE --%>

<%@ include file="calendarCommon.jsp" %>
<div class="card">
    <div class="card-header">
        <h2 class="title" ><fmt:message key="calendar_title" bundle="${etq}" /></h2>
    </div>
</div>
<table border="1" cellspacing="0" cellpadding="4" id="calendar_table">
  <tr>
    <td width="100%" colspan="7" class="month_year_header">
      <%=monthName%>, <%=intYear%>
    </td>
  </tr>
  <tr class="week_header_row">
    <th width="14%" class="th_day_cell day">Sun</th>
    <th width="14%" class="th_day_cell day">Mon</th>
    <th width="14%" class="th_day_cell day">Tue</th>
    <th width="14%" class="th_day_cell day">Wed</th>
    <th width="14%" class="th_day_cell day">Thu</th>
    <th width="15%" class="th_day_cell day">Fri</th>
    <th width="15%" class="th_day_cell day">Sat</th>
  </tr>
<%
{
  List<SorteoForm> sorteosMes = (List<SorteoForm>) request.getAttribute(SORTEOS_LIST_RQST_ATTRIBUTE);

  final HashMap<Integer, SorteoForm> mapMes = new HashMap();
  for(SorteoForm s: sorteosMes) {
    mapMes.put(s.getDiaSorteo(), s);
  }

  Month aMonth = Month.getMonth( Integer.parseInt(currentMonthString), Integer.parseInt(currentYearString) );
  final Date hoy = DateUtils.getDayDate();
  int [][] days = aMonth.getDays();
  for( int i=0; i<aMonth.getNumberOfWeeks(); i++ )
  {
    %>
    <tr class="week_data_row">
            <%
            for( int j=0; j<7; j++ )
            {
              if( days[i][j] == 0 )
              {
                %><td class="empty_day_cell">&nbsp;</td><%
              }
              else
              {
                // this is "today"
                if( currentDayInt == days[i][j] && currentMonthInt == aMonth.getMonth() && currentYearInt == aMonth.getYear() )
                {
                    if(mapMes.containsKey(days[i][j])) {
                        %>
                            <td id="cell_day-<%=days[i][j]%>" class="today_cell sorteo_cell calendar_cell">
                                <%=days[i][j]%>
                                <form method="post">
                                    <input type="hidden" name="cell_id" value="<%=mapMes.get(days[i][j]).getId()%>"/>
                                    <input type="hidden" name="cell_day" value="<%=days[i][j]%>"/>
                                    <input type="hidden" name="cell_month" value="<%=aMonth.getMonth() + 1%>"/>
                                    <input type="hidden" name="cell_year" value="<%=aMonth.getYear()%>"/>
                                </form>
                            </td>
                        <%
                    } else {
                        %>
                            <td id="cell_day-<%=days[i][j]%>" class="today_cell empty_cell calendar_cell">
                                <%=days[i][j]%>
                                <form method="post">
                                    <input type="hidden" name="cell_day" value="<%=days[i][j]%>"/>
                                    <input type="hidden" name="cell_month" value="<%=aMonth.getMonth() + 1%>"/>
                                    <input type="hidden" name="cell_year" value="<%=aMonth.getYear()%>"/>
                                </form>
                            </td>
                        <%
                    }
                }

                else {

                  final String theMonth = String.valueOf(Integer.valueOf(currentMonthString) + 1);
                  final String theDay = String.valueOf(days[i][j]);
                  final String theYear = String.valueOf(intYear);
                  final String currentDayStr = String.format("%s-%s-%s", theYear, theMonth, theDay);
                  final Date currentDay = Date.valueOf(currentDayStr);
                  if(mapMes.containsKey(days[i][j])) {
                      %>
                        <td id="cell_day-<%=days[i][j]%>" class="day_cell sorteo_cell calendar_cell">
                            <%=days[i][j]%>
                            <form method="post">
                                <input type="hidden" name="cell_id" value="<%=mapMes.get(days[i][j]).getId()%>"/>
                                <input type="hidden" name="cell_day" value="<%=days[i][j]%>"/>
                                <input type="hidden" name="cell_month" value="<%=theMonth%>"/>
                                <input type="hidden" name="cell_year" value="<%=theYear%>"/>
                            </form>
                        </td>
                      <%
                  } else if(currentDay.after(hoy)) {
                      %>
                        <td id="cell_day-<%=days[i][j]%>" class="day_cell empty_cell calendar_cell">
                            <%=days[i][j]%>
                            <form method="post">
                                <input type="hidden" name="cell_day" value="<%=days[i][j]%>"/>
                                <input type="hidden" name="cell_month" value="<%=theMonth%>"/>
                                <input type="hidden" name="cell_year" value="<%=theYear%>"/>
                            </form>
                        </td>
                    <%
                  } else {
                    %>
                      <td class="day_cell empty_cell">
                          <%=days[i][j]%>
                      </td>
                    <%
                  }
                }
              } // end outer if
            } // end for
            %>
    </tr>
  <%}
}
%>
</table>
<!-- navigation links -->
<table id="calendar_nav_table" border="0">
  <tr>
    <td id="prev_link">
      <form method="post">
        <input type="button" id="calendar_prev" value=" << "/>
        <input type="hidden" name="month" value="<%=prevMonth%>"/>
        <input type="hidden" name="year" value="<%=prevYear%>"/>
      </form>
    </td>
    <td id="link_to_month_view">
    </td>
    <td id="next_link">
      <form method="post">
        <input type="button" id="calendar_next" value=" >> "/>
        <input type="hidden" name="month" value="<%=nextMonth%>"/>
        <input type="hidden" name="year" value="<%=nextYear%>"/>
      </form>
    </td>
  </tr>
</table>
<!-- navigation links end -->
