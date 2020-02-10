<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${ulogovanKorisnickoIme == null}">
	<c:redirect url="Login.html" />
</c:if> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${ulogovanKorisnikRole.role == 'ADMIN'}">		
	<table border=1>
				<tr>
					<th>Naziv filma</th>					
					<th>Sediste</th>
					<th>Vreme prikazivanja</th>					
					<th>Korisnik koji je kupio kartu</th>
					<th>Sala</th>
					<th>Tip projekcije</th>
				</tr>
			<c:forEach var="karta" items="${karte}">
					<tr>
					<td>${karta.projekcija.prikazaniFilm.naziv}</td>					
					<td><a href="KartaServlet?id=${karta.id}">${karta.sediste.redniBroj}</a></td>
					<td><a href="ProjekcijaServlet?id=${karta.projekcija.id}"><fmt:formatDate value="${karta.projekcija.datumVreme}" pattern="dd/MM/yyyy HH:mm"/></td>
					<td><a href="KorisnikServlet?korisnickoIme=${karta.korisnikKupioKartu.korisnickoIme}">${karta.korisnikKupioKartu.korisnickoIme}</a></td>	
					<td>${karta.projekcija.sala.naziv}</td>
					<td>${karta.projekcija.tipProjekcije.naziv}</td>
			</tr>
		</c:forEach>
	</table>
		</c:when>
		
			<c:when test="${ulogovanKorisnikRole.role == 'KORISNIK'}">		
	<table border=1>
				<tr>
					<th>Naziv filma</th>					
					<th>Sediste</th>
					<th>Vreme prikazivanja</th>					
					<th>Korisnik koji je kupio kartu</th>
					<th>Sala</th>
					<th>Tip projekcije</th>
				</tr>
			<c:forEach var="karta" items="${karteKorisnika}">
					<tr>
					<td>${karta.projekcija.prikazaniFilm.naziv}</td>					
					<td><a href="KartaServlet?id=${karta.id}">${karta.sediste.redniBroj}</a></td>
					<td><a href="ProjekcijaServlet?id=${karta.projekcija.id}"><fmt:formatDate value="${karta.projekcija.datumVreme}" pattern="dd/MM/yyyy HH:mm"/></td>
					<td><a href="KorisnikServlet?korisnickoIme=${karta.korisnikKupioKartu.korisnickoIme}">${karta.korisnikKupioKartu.korisnickoIme}</a></td>	
					<td>${karta.projekcija.sala.naziv}</td>
					<td>${karta.projekcija.tipProjekcije.naziv}</td>
			</tr>
		</c:forEach>
	</table>
		</c:when>
	</c:choose>
			<form action="SveKarteServlet" method="get">
				<select name="sort"/>
						<option value="ORDER BY karta.korisnickoIme ASC">RASTUCE</option>
						<option value="ORDER BY karta.korisnickoIme DESC">OPADAJUCE</option>
				</select><input type="submit" value="Sortiraj po imenu">	
			</form>
			<a href="LogoutServlet">Odjava</a><br/> <br />
</body>
</html>