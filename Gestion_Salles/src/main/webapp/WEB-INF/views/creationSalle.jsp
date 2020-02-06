<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creation Salle</title>
</head>
<body>
<h2>CREATION SALLE</h2>
<form action="asbdd" method="post">
		
		<label>Nom de batiment : </label> <select name="batiment" id="selectNomBatiment">
		<c:forEach var="batiment" items="${listebatiment}">
			<option value="${batiment.id}">${batiment.nom}</option>
		</c:forEach>
		</select>
		<br> 
		<label>Numero de salle : </label> <input type="text" name="numsalle">
		<br> 
		<label>Nom de la salle : </label> <input type="text" name="nomsalle">
		<br>
		<label>Surface : </label> <input type="number" name="surface">
		<br>
		<label>Capacite : </label><input type="number" name="capacite">
		<br>	
		<label>Type salle : </label><input type="text" name="type">
		<br>
		
		
		<button type="submit">Envoyer</button>
	</form>

</body>
</html>