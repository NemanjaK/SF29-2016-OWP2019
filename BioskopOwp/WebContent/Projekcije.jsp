<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projekcije</title>
</head>
<h1 align="left">Projekcije u ponudi</h1>
<body>
	<table border=1>
		<tr>
			<th>Film</th>
			<th>Tip projekcije</th>
			<th>Sala</th>
			<th>Vreme prikazivanja</th>
			<th>Cena karte(din)</th>
		</tr>	
	<form action="ProjekcijeServlet" method="get">
			<tr bgcolor="lightgrey">
			</td><td align="center"><input type="text" name="nazivFilmaFilter"><input type="submit" value="Pretrazi"></td>
			<td align="center"><select name="tipProjkecijeFilter">
								 <option value="">Select</option>		
								  <option value="2D">2D</option>
								  <option value="3D">3D</option>
								  <option value="4D">4D</option>
								</select><input type="submit" value="Pretrazi"></td>
			<td align="center"><select name="salaFilter">
								 <option value="">Select</option>		
								  <option value="SALA1">SALA1</option>
								  <option value="SALA2">SALA2</option>
								  <option value="SALA3">SALA3</option>
								</select><input type="submit" value="Pretrazi"></td>
			<td align="center">
					od:&nbsp;<input type="text" name="vremeOd"><br/>
						<input type="submit" value="Pretrazi">
			<td align="center">
					od:&nbsp;<input type="text" name="minCenaKarteFilter" value="${minCenaKarte}"><br/>
					do:&nbsp;<input type="text" name="maxCenaKarteFilter" value="${maxCenaKarte}"><input type="submit" value="Pretrazi">
			</td>
			</tr>
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<select name="sort"/>
						<option value="">Select</option>
						<option value="ORDER BY projekcija.cenaKarte ASC">RASTUCE</option>
						<option value="ORDER BY projekcija.cenaKarte DESC">OPADAJUCE</option>
				</select><input type="submit" value="Sortiraj">		
			</td>
			</tr>			
		</form> 

		<c:forEach var="projekcija" items="${projekcije}">
		<tr>
			<td><a href="FilmServlet?id=${projekcija.prikazaniFilm.id}">${projekcija.prikazaniFilm.naziv}</a></td>		
			<td>${projekcija.tipProjekcije.naziv}</td>
			<td>${projekcija.sala.naziv}</td>
			<td><a href="ProjekcijaServlet?id=${projekcija.id}">${projekcija.datumVreme}</a></td>
			<td>${projekcija.cenaKarte}</td>
		</tr>
		</c:forEach>
		
	</table>
	
			<a href="FilmoviServlet">Filmovi</a><br /> <br />
			
			<c:if test="${ulogovanKorisnikRole.role == 'ADMIN'}">	
			<a href="IzvestajServlet">Izvestaj</a><br /> <br />
			</c:if>	
	
			<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK'}">
			<a href="KorisnikServlet">Profil</a><br /> <br />
			</c:if>
		
			<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK' || ulogovanKorisnikRole.role == 'ADMIN' }">
			<a href="LogoutServlet">Odjava</a><br/> <br />
			</c:if>
					
			<c:if test="${ulogovanKorisnikRole.role == 'ADMIN'}">
			<a href="KorisniciServlet">Upravljanje korisnicima</a><br /> <br />
			</c:if>
			
			<c:if test="${ulogovanKorisnikRole.role == 'ADMIN'}">
				<a href="AddProjekcijaServlet">Dodaj projekciju</a>
				<br />
			</c:if>
	
</body>
</html>