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
<title>Modifier une salle</title>
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
		<br /> <br />
		<h2>
			<strong>MODIFICATION D'UNE SALLE</strong>
		</h2>
	</div>
	<br />
	<br />
	<br />
	<div class="container">
		<form class="needs-validation" action="" method="post">

			<input hidden="" name="id" value="${ id }">

			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label>Numero</label> <input type="text" class="form-control"
						name="numero" value='<c:out value="${ salle.numero }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Nom</label> <input type="text" class="form-control"
						name="nom" value='<c:out value="${ salle.nom }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Type de salle</label> <input type="text"
						class="form-control" name="type"
						value='<c:out value="${ salle.type.type }"/>'>
				</div>
			</div>

			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label>Nombre de chaise</label> <input type="number" class="form-control"
						name="chaise" value='<c:out value="${ salle.nbrChaise }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Nombre de table</label> <input type="number" class="form-control"
						name="table" value='<c:out value="${ salle.nbrTable }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Capacit�</label> <input type="number" class="form-control"
						name="capacite" value='<c:out value="${ salle.capacite }"/>'>
				</div>
				
			</div>
<br>
			<div>
				<button name="modif" class="btn btn-success" value="valider">valider</button>
				<button name="modif" class="btn btn-primary" value="desactiver">activer/d�sactiver</button>
				<button name="modif" class="btn btn-primary" value="supprimer">supprimer</button>
			</div>
		</form>
		<br /> <br />
		<form action="">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</body>
</html>