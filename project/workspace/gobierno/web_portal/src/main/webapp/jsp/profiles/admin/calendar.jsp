<%@ page import="ar.edu.ubp.das.src.utils.Month,java.util.*,util.*,java.io.*" errorPage="error.jsp" %>
<%-- TODO: CLEAN UP THE PAGE TAG ABOVE --%>

<%@ include file="calendarCommon.jsp" %>
<link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=calendar" />

<div id="calendar_main_div">
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
      Month aMonth = Month.getMonth( Integer.parseInt(currentMonthString), Integer.parseInt(currentYearString) );
      int [][] days = aMonth.getDays();
      for( int i=0; i<aMonth.getNumberOfWeeks(); i++ )
      {
        %><tr class="week_data_row"><%
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
              %><td class="today_cell"><%=days[i][j]%></td><%
            }
            else
            {
              %><td class="day_cell"><%=days[i][j]%></td><% // important !!
            }
          } // end outer if
        } // end for
        %>
        </tr>
      <%}
    }
    %>
    </table>
</div>
<%-- end of "calendar_div" --%>

<!-- navigation links -->
<table id="calendar_nav_table" border="0">
  <tr>
    <td id="prev_link">
      <form method="post">
        <input type="submit" name="PREV" value=" << ">
        <input type="hidden" name="month" value="<%=prevMonth%>">
        <input type="hidden" name="year" value="<%=prevYear%>">
      </form>
    </td>
    <td id="link_to_month_view">
      <form action="calendarMonthPrintView.jsp" method="post">
        <input type="submit" value="  Full-Screen Print View  " class="submit_button">
        <input type="hidden" name="month" value="<%=intMonth%>">
        <input type="hidden" name="year"  value="<%=intYear%>">
      </form>
    </td>
    <td id="next_link">
      <form method="post">
        <input type="submit" name="NEXT" value=" >> ">
        <input type="hidden" name="month" value="<%=nextMonth%>">
        <input type="hidden" name="year" value="<%=nextYear%>">
      </form>
    </td>
  </tr>
</table>
<!-- navigation links end -->



