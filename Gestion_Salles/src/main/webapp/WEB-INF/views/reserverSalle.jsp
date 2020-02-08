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
	<div>
		
		<h2>Réservations de la salle</h2>

		<div>
			
		</div>
		<form action="Reserver" method="post">
			<input hidden="" name="id" value="${ id }"/>
			<div>
				<c:if test="${ invalide != null }">
					La salle n'a pas pu être réservée !<br/>
					Elle n'est pas disponibles aux dates indiquees.
				</c:if>
			</div>
			<div class="col-md-7">
				Date de début : <input name="debut" type="date" />
			</div>
			<div class="col-md-7">
				Durée : <input name="duree" type="number" />
			</div>
			<div class="col-md-7">
				Intitulé : <input name="intitule" type="text" />
			</div>
			<div class="col-md-7">
				<input class="btn btn-primary" type="submit" value="Reserver"/>
			</div>
		</form>
		
		<br/> <br/>
		<form action="cs" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>
		
	</div>

</body>
</html>