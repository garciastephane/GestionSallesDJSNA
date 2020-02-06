<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Gestion des Utilisateurs</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

	<%--<c:choose>
		<c:when test="${ empty sessionScope.authentification }">
			<c:redirect url="index.jsp" />
		</c:when>
		<c:when test="${ not empty sessionScope.authentification }">
			<c:set scope="session" var="authentification" value="true" />
		</c:when>
	</c:choose> --%>


	<div class="container text-center">
		<h2>
			<strong>GESTION DES UTILISATEURS</strong>
		</h2>
		<br /> <br />
	</div>

	<div class="container">
		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="">Liste des Salles</a>
			</div>
		</div>
		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="">Création de Salle</a>
			</div>
		</div>
		
		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="SVU">Liste des Utilisateurs</a>
			</div>
		</div>

		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="SRC">Création Utilisateurs</a>
			</div>
		</div>

		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="SRCU">Modifier un Utilisateur</a>
			</div>
		</div>

		<div class="card text-center" style="width: 18rem;">
			<div class="card-body">
				<a href="menu">Messagerie</a>
			</div>
		</div>

	</div>
	<br />
	<br />
	<div class="container">
		<form action="SD" method="post">
			<button class="btn btn-primary" type="submit" name="deconnection"
				value="deconnection">Déconnection</button>

		</form>
		<br />
	</div>
</body>
</html>