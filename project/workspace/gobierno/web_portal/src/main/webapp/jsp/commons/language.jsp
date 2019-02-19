<div id="lang_div" class="dropdown">
   <a class="btn btn-warning dropdown-toggle" href="#" data-toggle="dropdown">
       <c:choose>
          <c:when test="${sessionScope.lang == 'en'}">
            <span class="flag-icon flag-icon-us"></span> <fmt:message key="lang_title" bundle="${etq}" />
            </a>
          </c:when>
          <c:otherwise>
            <span class="flag-icon flag-icon-ar"></span> <fmt:message key="lang_title" bundle="${etq}" />
          </c:otherwise>
        </c:choose>
    </a>
  <ul class="dropdown-menu">
     <li>
        <form method="post" action="javascript:void(0)">
            <a class="lang_btn dropdown-item" href="#" ><span class="flag-icon flag-icon-us"></span> <fmt:message key="en_lang" bundle="${etq}" /></a>
            <input type="hidden" name="lang" value="en" />
        </form>
     </li>
     <li>
        <form method="post" action="javascript:void(0)">
            <a class="lang_btn dropdown-item" href="#" ><span class="flag-icon flag-icon-ar"></span> <fmt:message key="es_lang" bundle="${etq}" /></a>
            <input type="hidden" name="lang" value="es" />
        </form>
     </li>
  </ul>
</div>