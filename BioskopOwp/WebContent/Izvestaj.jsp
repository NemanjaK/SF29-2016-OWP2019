<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:if test="${sessionScope.ulogovanKorisnickoIme == null || sessionScope.ulogovanRole == 'KORISNIK'}">
	<c:redirect url="Login.html"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UFT-8">
<title>Insert title here</title>
</head>
<body>
<h2>Izvestaj</h2>
	<table border=2>
		<tr>
			<th>Nazi filmova</th>
			<th>Broj projekcija</th>
			<th>Broj prodatih karata</th>
			<th>Ukupna cena karata(din)</th>
		</tr>
		<c:forEach var="karta" items="${karte}">
				<tr>
					<td>${karta.projekcija.prikazaniFilm.naziv}</td>
					<td>${karta.projekcija.id}</td>	
					<td>${karta.id}</td>
					<td>${karta.projekcija.cenaKarte}</td>											
				</tr>
		</c:forEach>
		</table>
		<br></br><br></br>
		<table border=1>
			<tr>
				<th>Ukupan broj projekcija</th>
				<th>Ukupan broj prodatih karata</th>
				<th>Ukupna cena karata za sve projekcije(din)</th>												
			</tr>
			<tr>
				<td>${izvestaj.projekcija.id}</td>	
				<td>${izvestaj.id}</td>
				<td>${izvestaj.projekcija.cenaKarte}</td>											
			</tr>
		</table>
		<a href="LogoutServlet">Odjava</a><br/> <br />
		
		<a href="ProjekcijeServlet">Nazad</a><br/> <br />		
</body>
</html>