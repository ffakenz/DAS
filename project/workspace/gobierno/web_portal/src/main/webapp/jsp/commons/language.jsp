<div id="lang_div" class="dropdown">
   <a class="btn btn-warning dropdown-toggle" href="#" data-toggle="dropdown">
       <c:choose>
          <c:when test="${sessionScope.idioma == 'en'}">
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
        <a class="lang_btn dropdown-item" href="#" data-id="en"><span class="flag-icon flag-icon-us"></span> <fmt:message key="en_lang" bundle="${etq}" /></a>
     </li>
     <li>
        <a class="lang_btn dropdown-item" href="#" data-id="es"><span class="flag-icon flag-icon-ar"></span> <fmt:message key="es_lang" bundle="${etq}" /></a>
     </li>
  </ul>
</div>