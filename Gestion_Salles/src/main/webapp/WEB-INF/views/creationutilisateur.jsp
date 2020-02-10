<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Creer un utilisateur</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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
			<strong>CREATION D'UN UTILISATEUR</strong>
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
				<div class="col-md-4 mb-3">
					<label>Nom</label> <input type="text" class="form-control"
						name="nom" value='<c:out value="${ nom }" default=""></c:out>'>
				</div>
				<div class="col-md-4 mb-3">
					<label>Prenom</label> <input type="text" class="form-control"
						name="prenom"
						value='<c:out value="${ prenom }" default=""></c:out>'>
				</div>

				<div class="col-md-4 mb-3">
					<label>Mail</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">@</span>
						</div>
						<input type="text" class="form-control" name="mail"
							value='<c:out value="${ mail }" default=""></c:out>'>

					</div>
				</div>

			</div>

			<div class="form-row">
				<div class="col-md-6 mb-3">
					<label>Adresse</label> <input type="text" class="form-control"
						name="adresse"
						value='<c:out value="${ adresse }" default=""></c:out>'>

				</div>

				<div class="col-md-3 mb-3">
					<label>Role</label> <input type="text" class="form-control"
						name="role" value='<c:out value="${ role }" default=""></c:out>'>

				</div>
				<div class="col-md-3 mb-3">
					<label>Date de Naissance</label> <input type="date"
						class="form-control" name="datenaissance"
						value='<c:out value="${ datenaissance }" default=""></c:out>'>

				</div>
				<div class="col-md-4 mb-3">
					<label>Login</label> <input type="text" class="form-control"
						name="login" value='<c:out value="${ login }" default=""></c:out>'>
					<label> <c:if test="${ existe eq true }">
							<c:out value="le login est incorrect ou existe deja"></c:out>
						</c:if>
					</label>
				</div>
				<div class="col-md-4 mb-3">
					<label>MotDePasse</label> <input type="password"
						class="form-control" name="password" value="">

				</div>
				<div class="col-md-4 mb-3">
					<label>Valider Mot De Passe</label> <input type="password"
						class="form-control" name="password2" value="">

				</div>
			</div>
			<button class="btn btn-primary" type="submit" name="create"
				value="user">Creer un Utilisateur</button>
			<button class="btn btn-primary" type="submit" name="create"
				value="admin">Creer un Administrateur</button>
		</form>
		<br /> <br />
		<form action="Retour" method="get">
			<button class="btn btn-danger" type="submit">Retour</button>

		</form>
	</div>

</body>
</html>