<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salle <c:out value="${ id.numero }" /></title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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
		<h1>
			Salle
			<c:out value="${ id.numero }" />
		</h1>
		<br>
	</div>
	<div class="container">
		<h2>
			Batiment
			<c:out value="${ id.batiment.nom }" />
		</h2>
		<div class="row">
			<div class="col">
				<h2>Nom : <c:out value="${ id.nom }" /></h2>
			</div>
			<div class="col">
				<h2>Type : <c:out value="${ id.typeSalle.type }" />
			</div>
		</div>
		<div class="row">
			<div class="col">
				<h2>Surface : <c:out value="${ id.surface }" /> m²</h2>
			</div>
			<div class="col">
				<h2>Capacité : <c:out value="${ id.capacite }" /> personnes</h2>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col">
				<table>
					<thead>
						<tr>
							<th>quantite</th>
							<th>materiel</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ id.listeMateriels }" var="materiel">
							<tr>
								<td><c:out value="${ materiel.quantite }" /></td>
								<td><c:out value="${ materiel.type.type }" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<div class="col">
				<table>
					<thead>
						<tr>
							<th>intitulé</th>
							<th>date de debut</th>
							<th>date de fin</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ id.listeReservations }" var="reserve">
							<tr>
								<td><c:out value="${ reserve.intitule }" /></td>
								<td><c:out value="${ reserve.dateDebut }" /></td>
								<td><c:out value="${ reserve.dateFin }" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>

		<br>

		<form action="vs" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</body>
</html>