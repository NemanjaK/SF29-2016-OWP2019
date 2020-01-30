<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projekcije</title>
</head>
<body>
	<table border=1>
		<tr>
			<th>Tip projekcije</th>
			<th>Sala</th>
			<th>Vreme prikazivanja</th>
			<th>Cena karte</th>
			<th>Korisnicko ime</th>
			<th>Film</th>
		</tr>	
	<form action="ProjekcijeServlet" method="get">
			<tr bgcolor="lightgrey">
			<td></td>
			<td></td>
			<td></td>
			<td align="center">
					od:&nbsp;<input type="text" name="minCenaKarteFilter" value="${minCenaKarte}"><br/>
					do:&nbsp;<input type="text" name="maxCenaKarteFilter" value="${maxCenaKarte}"><input type="submit" value="Pretrazi">
			</td>
			<td></td>
			<td></td>
			</tr>
		</form> 
		<c:forEach var="projekcija" items="${projekcije}">
		<tr>
			<td>${projekcija.tipProjekcije.naziv}</td>
			<td>${projekcija.sala.naziv}</td>
			<td><a href="ProjekcijaServlet?id=${projekcija.id}">${projekcija.datumVreme}</a></td>
			<td>${projekcija.cenaKarte}</td>
			<td>${projekcija.adminDodaoProjekciju.korisnickoIme}</td>
			<td><a href="FilmServlet?id=${projekcija.prikazaniFilm.id}">${projekcija.prikazaniFilm.naziv}</a></td>
		</tr>
		</c:forEach>
		
	</table>
	
			<a href="FilmoviServlet">Filmovi</a><br /> <br />
	
			<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK'}">
			<a href="KorisnikServlet">Profil</a><br /> <br />
			</c:if>
		
			<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK'}">
			<a href="LogoutServlet">Odjava</a><br/> <br />
			</c:if>
					
			<c:if test="${ulogovanKorisnikRole.role == 'ADMIN'}">
			<a href="KorisniciServlet">Upravljanje korisnicima</a><br /> <br />
			</c:if>
	
</body>
</html>