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
		<form class="needs-validation" action="rms" method="post">
			<input hidden="" name="id" value="${ id }">

			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label>Numero</label> <input type="text" class="form-control"
						name="numsalle" value='<c:out value="${ salle.numero }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Nom</label> <input type="text" class="form-control"
						name="nomsalle" value='<c:out value="${ salle.nom }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Type de salle</label> <input type="text"
						class="form-control" name="type"
						value='<c:out value="${ salle.typeSalle.type }"/>'>
				</div>
			</div>

			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label>Surface</label> <input type="number" class="form-control"
						name="surface" value='<c:out value="${ salle.surface }"/>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Capacité</label> <input type="number" class="form-control"
						name="capacite" value='<c:out value="${ salle.capacite }"/>'>
				</div>

			</div>
			<div class="form-row">
			<div class="col-md-5 mb-3">
				<table class="table">
					<thead>
						<tr class="table table-primary">
							<td>type</td>
							<td>quantité</td>
						</tr>
					</thead>
					<tbody>
						<c:out value="${ materiel }" escapeXml="false"></c:out>

					</tbody>
				</table>
			</div>
		</div>
			<br>
			<div>
				<button name="modif" class="btn btn-success" value="valider">valider</button>
				<button name="modif" class="btn btn-primary" value="supprimer">supprimer</button>
			</div>
		</form>
		<br /> <br />
		<form action="cs" method="get">
			<button class="btn btn-danger">Retour</button>
		</form>
	</div>
</body>
</html>