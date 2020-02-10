<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<title>Mail</title>
</head>
<body>

<c:choose>
		<c:when test="${ empty sessionScope.personneCourante }">
			<c:redirect url="index.jsp" />
		</c:when>
		<%--<c:when test="${ not empty sessionScope.personneCourante }">
			<c:set scope="session" var="personneCourante" value=${ personne } />
		</c:when>--%>
	</c:choose>

<div class="container">
		<h2>
			<strong>Message :</strong>
		</h2>
		<br />
		<br />
	</div>

	<div class="container">
	<table class="table table-striped" >
  <thead>
    <tr>
      <th>Expediteur</th>
      <th>Objet</th>
      <th>Message</th>
      <th>Heure</th>
    </tr>
  </thead>
  <tbody>	  
  <tr>	 
	 <td><c:out value="${ expediteur }"></c:out></td>
	 <td><c:out value="${ objet }"></c:out></td>
	 <td><c:out value="${ contenu }"></c:out></td>
	 <td><c:out value="${ date }"></c:out></td>	     
  </tr>   
  </tbody>
</table>
		
	
	</div>
		<a href="BR" class="btn btn-danger">Retour</a>
</body>
</html>