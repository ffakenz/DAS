<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="properties.etiquetas" var="etq" scope="session"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="icon" type="image/gif" href="/web_portal/img/spinnerMD.gif">
    <!-- Main -->
    <link type="text/css" rel="stylesheet" href="/web_portal/util/StyleSheet.do/load=page,messages,table,flag-icon.min,language,flatly.bootstrap.min" />
    <title><fmt:message key="page_title" bundle="${etq}" /></title>
</head>