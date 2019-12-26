<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Korisnici</title>
</head>
<body>
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
</body>
</html>