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

<title>Choix de l'utilisateur</title>
</head>
<body>
	<%-- <c:choose>
		<c:when test="${ empty sessionScope.authentification }">
			<c:redirect url="index.jsp" />
		</c:when>
		<c:when test="${ not empty sessionScope.authentification }">
			<c:set scope="session" var="authentification" value="true" />
		</c:when>
	</c:choose>--%>
	<div class="container">
		<br /> <br />
		<h2>
			<strong>CHOIX D'UN UTILISATEUR</strong>
		</h2>
	</div>
	<br />
	<br />
	<br />
	<div class="container">
		<form class="needs-validation" action="SChU" method="post">
			<div class="form-row">
				<div class="col-md-2 mb-3">
					<label>id utilisateur</label> <input class="form-control"
						type="number" name="${ choix }">
					<button class="btn btn-success" type="submit" value="Valider">Valider</button>
				</div>
			</div>
		</form>
		<div class="form-row">
			<div class="col-md-10 mb-3">
				<table class="table">
					<thead>
						<tr class="table table-primary">
							<td>Id</td>
							<td>Nom</td>
							<td>Prenom</td>
							<td>Date de naissance</td>
							<td>Mail</td>
							<td>Adresse</td>
							<td>Role</td>
						</tr>
					</thead>
					<tbody>
						<c:out value="${ alluser }" escapeXml="false"></c:out>

					</tbody>
				</table>
			</div>
		</div>
		<form action="menu" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</body>
</html>