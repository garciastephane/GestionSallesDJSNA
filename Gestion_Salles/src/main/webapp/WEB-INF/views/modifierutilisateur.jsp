<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<title>Modifier un utilisateur</title>
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
			<strong>MODIFICATION D'UN UTILISATEUR</strong>
		</h2>
	</div>
	<br />
	<br />
	<br />
	<div class="container">
		<form class="needs-validation" action="SMU" method="post">

			<input hidden="" name="id" value="${ id }">

			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label>Nom</label> <input type="text" class="form-control"
						name="nom" value='<c:out value="${ personne.nom }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Prenom</label> <input type="text" class="form-control"
						name="prenom" value='<c:out value="${ personne.prenom }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Mail</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">@</span>
						</div>
						<input type="text" class="form-control" name="mail"
							value='<c:out value="${ personne.email }"/>'>
					</div>
				</div>
			</div>

			<div class="form-row">
				<div class="col-md-6 mb-3">
					<label>Adresse</label> <input type="text" class="form-control"
						name="adresse" value='<c:out value="${ personne.adresse }"/>'>
				</div>
				<div class="col-md-3 mb-3">
					<label>Role</label> <input type="text" class="form-control"
						name="role" value='<c:out value="${ personne.role.role }"/>'>
				</div>
				<div class="col-md-3 mb-3">
					<label>Date de naissance</label> <input type="date"
						class="form-control" name="datenaissance"
						value='<c:out value="${ datenaissance }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Mot de Passe</label> <input type="password"
						class="form-control" name="password">
				</div>
				<div class="col-md-4 mb-3">
					<label>Confirmer le Mot de Passe</label> <input type="password"
						class="form-control" name="password2">
				</div>
			</div>

			<div>
				<button name="modif" class="btn btn-success" value="valider">valider</button>
				<button name="modif" class="btn btn-primary" value="desactiver">activer/désactiver</button>
				<button name="modif" class="btn btn-primary" value="supprimer">supprimer</button>
			</div>
		</form>
		<br /> <br />
		<form action="SChU">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</body>
</html>