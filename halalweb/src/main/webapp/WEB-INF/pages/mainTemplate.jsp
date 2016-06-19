<%@ page import="com.halal.web.sa.common.HalalGlobalConstants"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html>
<html>
<head>
<title>Truly Halal</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--webfont-->
<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic,700,700italic' rel='stylesheet' type='text/css'>
<!--Animation-->
<script src="js/wow.min.js"></script>
<link href="css/animate.css" rel='stylesheet' type='text/css' />

<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>

<script src="js/common.js"></script>
</head>
<body>

<%@ include file="header.jsp"%>

<c:if test="${pageType eq 'homepage'}">
	<c:out value="${finalHTML}" escapeXml="false"></c:out>
</c:if>

<c:if test="${pageType eq 'loginpage'}">
	<c:out value="${loginHTML}" escapeXml="false"></c:out>
</c:if>

<c:if test="${pageType eq 'businesspage'}">
	<c:out value="${businessHTML}" escapeXml="false"></c:out>
</c:if>

<%@ include file="footer.jsp"%>
</body>
</html>