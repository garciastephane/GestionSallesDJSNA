<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<title>Menu</title>
</head>
<body>

	<c:choose>
		<c:when test="${ empty sessionScope.personneCourante }">
			<c:redirect url="index.jsp" />
		</c:when>
		<%--<c:when test="${ not empty sessionScope.personneCourante }">
			<c:set scope="session" var="personneCourante" value="true" />
		</c:when>--%>
	</c:choose>
	
	<div class="container">
		<h2>
			<strong>Menu</strong>
		</h2>
		<br /> <br />
	</div>

	<div class="container">

		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="NM">Nouveau message</a>
			</div>
		</div>
		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="BR">Boite de réception</a>
			</div>
		</div>

		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="ME">Message envoyés</a>
			</div>
		</div>

		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="MA">Message archivés</a>
			</div>
		</div>

	</div>
	<br />
	<br />
	<div>
		<a href="Retour" class="btn btn-danger">Retour</a>
	</div>
</body>
</html>