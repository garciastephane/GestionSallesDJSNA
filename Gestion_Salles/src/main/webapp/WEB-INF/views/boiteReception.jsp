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
<title>Menu</title>
</head>
<body>
	
	<div class="container">
		<h2>
			<strong>Liste des Messages Reçus</strong>
		</h2>
		<br /> <br />
	</div>

	<div class="container">
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Expediteur</th>
					<th>Objet</th>
					<th>Message</th>
					<th>Heure</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${ not empty listeMessages }">
					<c:forEach items="${ listeMessages }" var="message">
						<c:if test="${ message.archivage eq false }">
							<tr>
								<td><c:out value="${ message.expediteur }"></c:out></td>
								<td><c:out value="${ message.objet }"></c:out></td>
								<td><c:out value="${ message.contenu}"></c:out></td>
								<td><c:out value="${ message.date }"></c:out></td>
								<td>
									<div class="btn-group" role="group">
										<c:url value="voirR" var="voirMessage">
											<c:param name="expediteur" value="${ message.expediteur }" />
											<c:param name="objet" value="${ message.objet }" />
											<c:param name="contenu" value="${ message.contenu }" />
											<c:param name="date" value="${ message.date }" />
										</c:url>
										<a href="${ voirMessage }" class="btn btn-primary">Voir</a>
										<%-- <form class="form" action="DeleteServlet" method="post">
											<input type="hidden" name="id" value="<c:out value="" />">
											<input type="submit" class="btn btn-danger" name="archiver"
												value="Archiver">
										</form>--%>
										
										<form action="ARC" method="post">
											<input hidden="" name="id" value="${ message.id }"/>
											<input hidden="" name="page" value="boiteReception"/>
											<input class="btn btn-danger" type="submit" value="Archiver"/>
										</form>
										
										
									</div>

								</td>
								
							</tr>
						</c:if>
					</c:forEach>
				</c:if>

			</tbody>
		</table>


	</div>
	<a href="menu" class="btn btn-danger">Retour</a>
</body>
</html>