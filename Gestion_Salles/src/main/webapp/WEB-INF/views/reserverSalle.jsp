<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<title>Reservervation</title>
</head>
<body>
	<br />
	<div class="container">
		<h2>Réservations de la salle</h2>
		<br />
		<div class="text-danger">
			<c:if test="${ invalide != null }">
					La salle n'a pas pu être réservée !<br />
					Elle n'est pas disponibles aux dates indiquees.
					<br/><br/>
				</c:if>
		</div>
		
		<form action="Reserver" method="post">
			<input hidden="" name="id" value="${ id }" />
			<div>
				<h5>Liste des réservations déjà effectuées :</h5>
				<c:forEach items="${ reservations }" var="reserv">
					Du <c:out value="${ reserv.dateDebut }" /> au <c:out value="${ reserv.dateFin }" />
					 : <c:out value="${ reserv.intitule }" />
					<br />
				</c:forEach>
			</div>
			<hr/>
			<br />
			<div class="col-md-7">
				Date de début : <input name="debut" type="date" />
			</div>
			<br />
			<div class="col-md-7">
				Durée : <input name="duree" type="number" />
			</div>
			<br />
			<div class="col-md-7">
				Intitulé : <input name="intitule" type="text" />
			</div>
			<br />
			<div class="col-md-7">
				<input class="btn btn-primary" type="submit" value="Réserver" />
			</div>
		</form>

		<br /> <br />
		<form action="cs" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>


	</div>
</body>
</html>