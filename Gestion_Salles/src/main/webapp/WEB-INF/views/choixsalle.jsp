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

<title>Choix de la salle</title>
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
		<form class="needs-validation" action="sc" method="post">
			<div class="form-row">
				<div class="col-md-1 mb-3">
					<label>id salle :</label>
				</div>
				<div class="col-md-2 mb-3">
					<input class="form-control" type="number" name="id">
				</div>
				<div class="col-md-2 mb-3">
					<button class="btn btn-success" type="submit" name="res" value="Valider">Valider</button>
				</div>
				<div class="col-md-2 mb-3">
					<button class="btn btn-success" type="submit" name="res" value="Reserver">Reserver</button>
				</div>
			</div>
		</form>
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