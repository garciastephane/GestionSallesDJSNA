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
<form action="creationSalle" method="post">
		
		<label>Nom de batiment : </label> <input type="text" name="nom de batiment">
		<br> 
		<label>Numero de salle : </label> <input type="text" name="numero de salle">
		<br> 
		<label>Nom de la salle : </label> <input type="text" name="nom de salle">
		<br>
		<label>Surface : </label> <input type="number" name="surface">
		<br>
		<label>Capacite : </label><input type="number" name="capacite">
		<br>
		
		<button type="submit">Envoyer</button>
	</form>

</body>
</html>