<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filmovi</title>
</head>
<body>
	<table border=1>
	
		<tr bgcolor="lightgrey">
			<th>Naziv</th>
			<th>Trajanje</th>
			<th>Distributer</th>
			<th>Zemlja porekla</th>
			<th>Godina proizvodnje</th>
			<th>Zanrovi</th>
		</tr>
		<form action="FilmoviServlet" method="get">
			<tr bgcolor="lightgrey">			
				<td align="center"><input type="text" name="nazivFilter"><input type="submit" value="Pretrazi"></td>
				<td align="center">
					od:&nbsp;<input type="text" name="minTrajanjeFilter"><br/>
					do:&nbsp;<input type="text" name="maxTrajanjeFilter">
				</td>
				<td align="center"><input type="text" name="distributerFilter"><input type="submit" value="Pretrazi"></td>
				<td align="center"><input type="text" name="zemljaPoreklaFilter"><input type="submit" value="Pretrazi"></td>
				<td align="center">
					od:&nbsp;<input type="text" name="minGodinaProizvodnjeFilter"><br/>
					do:&nbsp;<input type="text" name="maxGodinaProizvodnjeFilter">
				</td>
				<td align="center"><input type="text" name="zanroviFilter"><input type="submit" value="Pretrazi"></td>								
			</tr>
			<tr>
			<td></td>
			<td>
				<select name="sort"/>
						<option value="ORDER BY trajanje ASC">RASTUCE</option>
						<option value="ORDER BY trajanje DESC">OPADAJUCE</option>
				</select><input type="submit" value="Sortiraj">	
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		
			</tr>			
			
		</form>
		<c:forEach var="film" items="${filmovi}">
			<tr>
				<td><a href="FilmServlet?id=${film.id}">${film.naziv}</a></td>
				<td>${film.trajanje }</td>
				<td>${film.distributer }</td>
				<td>${film.zemljaPorekla }</td>
				<td>${film.godinaProizvodnje }</td>
				<td>${film.zanrovi }</td>
			</tr>

		</c:forEach>
	</table>
		<p>
		
			<a href="ProjekcijeServlet">Povratak na projekcije</a><br /> <br />
			
			
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
				<a href="DodavanjeFilm.html">Dodaj film</a>
				<br />
			</c:if>
		</p>		
</body>
</html>