<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <c:if test="${ulogovanKorisnickoIme == null}">
	<c:redirect url="Login.html" />
</c:if> 
 --%>
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
				<td align="center"><input type="text" name="nazivFilter" value="${film.naziv}"><input type="submit" value="Pretrazi"></td>
				<td align="center">
					od:&nbsp;<input type="text" name="minTrajanjeFilter" value="${minTrajanje}"><br/>
					do:&nbsp;<input type="text" name="maxTrajanjeFilter" value="${maxTrajanje}">
				</td>
				<td align="center"><input type="text" name="distributerFilter" value="${film.distributer}"><input type="submit" value="Pretrazi"></td>
				<td align="center"><input type="text" name="zemljaPoreklaFilter" value="${film.zemljaPorekla}"><input type="submit" value="Pretrazi"></td>
				<td align="center">
					od:&nbsp;<input type="text" name="minGodinaProizvodnjeFilter" value="${minGodinaProizvodnje}"><br/>
					do:&nbsp;<input type="text" name="maxGodinaProizvodnjeFilter" value="${maxGodinaProizvodnje}">
				</td>
				<td align="center"><input type="text" name="zanroviFilter" value="${film.zanrovi}"><input type="submit" value="Pretrazi"></td>								
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
		
			
			<c:if test="${ulogovanKorisnikRole == 'KORISNIK'}">
			<a href="KorisnikServlet">Profil</a><br /> <br />
			</c:if>
		
			<c:if test="${ulogovanKorisnikRole == 'KORISNIK'}">
			<a href="LogoutServlet">Odjava</a><br/> <br />
			</c:if>
					
			<c:if test="${ulogovanKorisnikRole == 'ADMIN'}">
			<a href="KorisniciServlet">Upravljanje korisnicima</a><br /> <br />
			</c:if>
			
			<c:if test="${ulogovanKorisnikRole == 'ADMIN'}">
				<a href="DodavanjeFilm.html">Dodaj film</a>
				<br />
			</c:if>
		</p>
</body>
</html>