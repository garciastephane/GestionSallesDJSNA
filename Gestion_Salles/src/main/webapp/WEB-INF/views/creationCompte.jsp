<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Création du compte</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

</head>
<body>
	
	<div class="container">
		<br /> <br />
		<h2>
			<strong>CREATION D'UN COMPTE</strong>
		</h2>
	</div>
	<br />
	<br />
	<br />
	<div class="container">
		<label> <c:if test="${ champIncorrect eq true }">
				<c:out value="L'un des champs est incorrect !"></c:out>
			</c:if>
		</label>
		<form class="needs-validation" novalidate action="SCU" method="post">
			<div class="form-row">
				<div class="col-md-7 mb-3">
					Nom<input type="text" class="form-control" id="nom"
						name="nom" value='<c:out value="${ nom }" default=""></c:out>' required>
				</div>
				<div class="col-md-7 mb-3">
					Prenom<input type="text" class="form-control" id="prenom"
						name="prenom"
						value='<c:out value="${ prenom }" default=""></c:out>' required>
				</div>

				<div class="col-md-7 mb-3">
					Mail
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">@</span>
						</div>
						<input type="email" class="form-control" id="mail" name="mail"
							value='<c:out value="${ mail }" default=""></c:out>' required>

					</div>
				</div>

			</div>

			<div class="form-row">
				<div class="col-md-7 mb-3">
					Adresse<input type="text" class="form-control"
						id="adresse" name="adresse"
						value='<c:out value="${ adresse }" default=""></c:out>' required>

				</div>

				<div class="col-md-7 mb-3">
					Role<input type="text" class="form-control"
						name="role" value='<c:out value="${ role }" default=""></c:out>'>

				</div>
				<div class="col-md-7 mb-3">
					Date de Naissance<input type="date"
						class="form-control" id="datenaissance" name="datenaissance"
						value='<c:out value="${ datenaissance }" default=""></c:out>' required>

				</div>
				<div class="col-md-7 mb-3">
					Login<input type="text" class="form-control" id="login"
						name="login" value='<c:out value="${ login }" default=""></c:out>' required>
					<label> <c:if test="${ existe eq true }">
							<c:out value="le login est incorrect ou existe deja"></c:out>
						</c:if>
					</label>
				</div>
				<div class="col-md-7 mb-3">
					MotDePasse<input type="password"
						class="form-control" id="password" name="password" value="" required>

				</div>
				<div class="col-md-7 mb-3">
					Valider Mot De Passe<input type="password"
						class="form-control" id="password" name="password2" value="" required>

				</div>
			</div>
			<button class="btn btn-primary" type="submit" id="create" name="create"
				value="pageUser" >Creer</button>
		</form>
		<br /> <br />
		<form action="SD" method="post">
			<button class="btn btn-danger" type="submit">Retour</button>
		</form>
	</div>
	
	<!-- <script src="${pageContext.request.contextPath}/resources/js/verificationCreationCompte.js" type="text/javascript" ></script> -->
</body>
</html>