<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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
					<td>${karta.sediste.redniBroj}</td>
					<td><a href="ProjekcijaServlet?id=${karta.projekcija.id}">${karta.projekcija.datumVreme}</td>
					<td><a href="KorisnikServlet?korisnickoIme=${karta.korisnikKupioKartu.korisnickoIme}">${karta.korisnikKupioKartu.korisnickoIme}</a></td>	
					<td>${karta.projekcija.sala.naziv}</td>
					<td>${karta.projekcija.tipProjekcije.naziv}</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>