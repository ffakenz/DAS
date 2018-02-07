<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${empty header['X-Requested-With']}">
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="/util/StyleSheet.do/load=page,messages" />
</head>
<body>
</c:if>
	<div class="information">
		${requestScope.message}
	</div>
<c:if test="${empty header['X-Requested-With']}">
</body>
</html>
</c:if>