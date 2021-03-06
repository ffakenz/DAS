<%
  // get the current year/month/day
  Calendar theCal = Calendar.getInstance();
  Integer currentYearInt  = theCal.get(Calendar.YEAR);
  Integer currentMonthInt = theCal.get(Calendar.MONTH);
  Integer currentDayInt   = theCal.get(Calendar.DATE);
  String currentYearString  = new Integer(currentYearInt).toString();
  String currentMonthString = new Integer(currentMonthInt).toString();

  // get parameters the user might have sent by clicking fwd or back
  String newMonth = (String) request.getAttribute("month"); // important !!
  String newYear  = (String) request.getAttribute("year"); // important !!

  // reset the month and year if necessary
  if ( newMonth != null )
  {
    currentMonthString = newMonth;
  }
  if ( newYear != null )
  {
    currentYearString = newYear;
  }

  // determine the next/previous month and year
  int intMonth = new Integer(currentMonthString).intValue();
  int intYear  = new Integer(currentYearString).intValue();

  String lang = (String) request.getSession().getAttribute("lang");

  // determine the name of the current intMonth
  String englishMonthNames[] = {"January","February","March","April","May","June",
                         "July","August","September","October","November","December" };

  String spanishMonthNames[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio",
                           "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" };

  String portugueseMonthNames[] = {"Janeiro", "Fevereiro", "Mar&ccedilo", "Abril", "Maio", "Junho",
                                  "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };

  String monthName;
  if(lang.equals("pt")) monthName = portugueseMonthNames[intMonth];
  else if(lang.equals("es")) monthName = spanishMonthNames[intMonth];
  else monthName = englishMonthNames[intMonth];


  // determine the next/previous month and year.
  // this is really only needed by calendar.jsp, but i moved it here
  // to simplify calendar.jsp.
  int nextYear = intYear;
  int prevYear = intYear;

  int prevMonth = intMonth-1;
  if ( prevMonth==-1 ) {
    prevMonth=11;
    prevYear--;
  }

  int nextMonth = intMonth+1;
  if ( nextMonth==12 ) {
      nextMonth = 0;
      nextYear++;
  }

%>