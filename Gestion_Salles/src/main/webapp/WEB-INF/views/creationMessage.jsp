<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouveau Message</title>

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
		<br/>
		<h2>
			<strong>NOUVEAU MESSAGE</strong>
		</h2>
		<br/>
		<br/>
		<form action="EM" method="post">
			<div>
				<c:if test="${ invalide != null }">
					Le message n'a pas pu être envoyé !
				</c:if>
			</div>
			
			<div class="col-md-7">
				A <input id="destinataire" name="destinataire" type="text" value="${ destinataire }" required/>
			</div>
			<div class="col-md-7">
				Objet <input id="objet" name="objet" type="text" value="${ objet }" required/>
			</div>
			<div class="col-md-7">
				<textarea id="contenu" name="contenu" rows="10" cols="50">
					<c:out value="${ contenu }"/>
				</textarea>
			</div>
			<br />
			<input class="btn btn-primary" type="submit" value="Envoyer" />
		</form>
		<br/>
		
		<form action="menu">
			<input class="btn btn-danger" type="submit" value="Retour"/>
		</form>
	</div>

	<!-- <script src="${pageContext.request.contextPath}/resources/js/verificationEnvoieMessage.js" type="text/javascript"></script> -->
</body>
</html>