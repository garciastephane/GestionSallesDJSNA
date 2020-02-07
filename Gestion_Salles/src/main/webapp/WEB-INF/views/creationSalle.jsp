<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creation Salle</title>
</head>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">

<body>
	<div>
		<div class="alert alert-info" role="alert">
			<br>
			<h2 class="text-uppercase">creation salle</h2>
			<br>
		</div>
		<form action="asbdd" method="post">
			<p class="text-secondary">
			<div class="container">
				<br> <label>Nom de batiment : </label> <select name="batiment"
					id="selectNomBatiment">
					<c:forEach var="batiment" items="${listebatiment}">
						<option value="${batiment.id}">${batiment.nom}</option>
					</c:forEach>
				</select> <br> <label>Type salle : </label> <select name="type"
					id="selectNomBatiment">
					<c:forEach var="type" items="${listeTypeSalle }">
						<option value="${type.id}">${ type.type}</option>
					</c:forEach>
				</select> <br> <label>Nom de la salle : </label> <input type="text"
					name="nomsalle"> <br> <label>Numero de salle :
				</label> <input type="text" name="numsalle" id="num"> <br> <label>Surface
					(m²) : </label> <input type="number" name="surface" id="num"> <br>
				<label>Capacite : </label> <input type="number" name="capacite"
					id="num"> <br> <br>
				<div class="col text-center">
					<button type="submit">Envoyer</button>
					<br> <br>
				</div>
		</form>
	</div>
</body>
</html>