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

<title>Visualisation Utilisateur</title>
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
	<br/>
	<h3>Liste de toutes les personnes </h3>
		<br/>
		<table class="table-bordered">
			<thead class="table-primary">
				<tr>
					<td>Nom</td>
					<td>Prenom</td>
					<td>Date de naissance</td>
					<td>Actif</td>
					<td>Mail</td>
					<td>Adresse</td>
					<td>Role</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="couplePersonne" items="${listePersonnes}">

					<tr>
						<td><c:out value="${couplePersonne ['value'] ['nom'] } " /></td>
						<td><c:out value="${couplePersonne ['value'] ['prenom'] } " /></td>
						<td><c:out
								value="${couplePersonne ['value'] ['dateNaissance'] } " /></td>
						<td><c:out value="${couplePersonne ['value'] ['actif'] } " /></td>
						<td><c:out value="${couplePersonne ['value'] ['email'] } " /></td>
						<td><c:out value="${couplePersonne ['value'] ['adresse'] } " /></td>
						<td><c:out
								value="${couplePersonne ['value'] ['role'] ['role'] } " /></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	<br />
	<div>
		<form action="gestionuser" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</div>

</body>
</html>