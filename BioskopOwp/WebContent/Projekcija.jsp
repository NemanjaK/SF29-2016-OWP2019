<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projekcija</title>
</head>
<h2>Projekcija</h2>
<body>
	<table border=2>
		<tr>
			<th>Film</th>
			<th>Tip projekcije</th>
			<th>Sala</th>
			<th>Vreme prikazivanja</th>
			<th>Cena karte(din)</th>
		</tr>

		<tr>
			<td><a href="FilmServlet?id=${projekcija.prikazaniFilm.id}">${projekcija.prikazaniFilm.naziv}</a></td>		
			<td>${projekcija.tipProjekcije.naziv}</td>
			<td>${projekcija.sala.naziv}</td>
			<td>${projekcija.datumVreme}</td>
			<td>${projekcija.cenaKarte}</td>
		</tr>
		
	</table>
	
	<c:choose>	
		<c:when test="${ulogovanKorisnikRole.role == 'ADMIN'}">
		<h3>Prodate karte za projekciju</h3>				
		<table border=2>
		<tr>
			<th>Zauzeto sediste</th>
			<th>Vreme prodaje karte</th>
			<th>Korisnik koji je kupio kartu</th>
		</tr>
		<c:forEach var="karta" items="${karte}">
		<tr>
			<td>${karta.sediste.redniBroj}</td>
			<td><a href="KartaServlet?id=${karta.id}">${karta.datumProdaje}</a></td>
			<td><a href="KorisnikServlet?korisnickoIme=${karta.korisnikKupioKartu.korisnickoIme}">${karta.korisnikKupioKartu.korisnickoIme}</a></td>		
					
		</tr>
		</c:forEach>
		</table>
		</c:when>		
		
		<c:when test="${ulogovanKorisnikRole.role == 'KORISNIK'}">
		<td><br><a href="KarteServlet?id=${projekcija.id}&idSala=${projekcija.sala.id}"><button>Kupi kartu</button><br></br>
		</c:when>			
	</c:choose>
	
		<a href="ProjekcijeServlet">Nazad</a>
	
		<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK' || ulogovanKorisnikRole.role == 'ADMIN' }">
			<a href="LogoutServlet">Odjava</a><br/> <br />
		</c:if>
</body>
</html>