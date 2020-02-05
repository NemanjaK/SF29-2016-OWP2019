<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ulogovanKorisnickoIme == null || ulogovanKorisnikRole.role == 'KORISNIK'}">
	<c:redirect url="Login.html" />
</c:if> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Korisnici</title>
</head>
<body>
<c:choose>
	<c:when test="${ulogovanKorisnikRole.role == 'ADMIN'}">
	<h3>Korisnici</h3>	
	<table border = "1">
		<tr>
			<th>Korisnicko ime</th>
			<th>Datum registracije</th>
			<th>Uloga</th>
		</tr>	
	
		<c:forEach var="korisnik" items="${korisnici}">
			<tr>
				<td><a href="KorisnikServlet?korisnickoIme=${korisnik.korisnickoIme}">${korisnik.korisnickoIme}</a></td>
				<td>${korisnik.datumRegistracije}</td>
				<td>${korisnik.role}</td>
			</tr>
			
		</c:forEach>		
	</table>
	
		<p>
			<a href="FilmoviServlet">Povratak</a>
		</p>
	</c:when>
	</c:choose>
		
		
</body>
</html>