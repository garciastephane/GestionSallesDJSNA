<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<title>Liste des salle</title>
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
		<br /> <br />
		<h2>
			<strong>CHOIX D'UNE SALLE</strong>
		</h2>
	</div>
	<br />
	<br />
	<br />
	<div class="container">
		<div class="form-row">
			<div class="col-md-10 mb-3">
				<table class="table">
					<thead>
						<tr class="table table-primary">
							<td>Id</td>
							<td>Numero</td>
							<td>Type de salle</td>
							<td>Nom</td>
							<td>Capacité</td>
							<td>Surface</td>
							<td>Batiment</td>
						</tr>
					</thead>
					<tbody>
						<c:out value="${ allroom }" escapeXml="false"></c:out>

					</tbody>
				</table>
			</div>
		</div>
		<form action="Retour" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</body>
</html>