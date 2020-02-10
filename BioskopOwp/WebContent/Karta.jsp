<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.ulogovanKorisnickoIme == null}">
	<c:redirect url="Login.html"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<table border="1">
				<tr>
					<th>Naziv filma</th>					
					<th>Sediste</th>
					<th>Vreme prikazivanja</th>					
					<th>Korisnik koji je kupio kartu</th>
					<th>Sala</th>
					<th>Tip projekcije</th>
				</tr>
				<tr>
					<td>${karta.projekcija.prikazaniFilm.naziv}</td>					
					<td>${karta.sediste.redniBroj}</td>
					<td><fmt:formatDate value="${karta.projekcija.datumVreme}" pattern="dd/MM/yyyy HH:mm"/></td>
					<td><a href="KorisnikServlet?korisnickoIme=${karta.korisnikKupioKartu.korisnickoIme}">${karta.korisnikKupioKartu.korisnickoIme}</a></td>	
					<td>${karta.projekcija.sala.naziv}</td>
					<td>${karta.projekcija.tipProjekcije.naziv}</td>
				</tr>
			</table>
			<br></br>
			<c:if test="${ulogovanKorisnikRole.role == 'ADMIN'}">	
			<form action="KartaServlet" method="post">
					<input type="hidden" name="action" value="delete"/>
					<input type="hidden" name="id" value="${karta.id}"/>
					<tr><td/><td><input type="submit" value="Obrisi"></td>
			</form>	
			</c:if>	
			<a href="LogoutServlet">Odjava</a><br/> <br />			
	</body>
</html>