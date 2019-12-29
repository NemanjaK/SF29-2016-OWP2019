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
			<th>Reziser</th>
			<th>Zemlja porekla</th>
			<th>Godina proizvodnje</th>
			<th>Glumci</th>
			<th>Opis</th>
			<th>Zanrovi</th>
		</tr>
		<form action="FilmoviServlet" method="get">
			<tr bgcolor="lightgrey">
				<td align="center"><input type="text" name="nazivFilter" value="${film.naziv}"><input type="submit" value="Pretrazi po nazivu"></td>
				</tr>
		</form>
		<c:forEach var="film" items="${filmovi}">
			<tr>
				<td><a href="FilmServlet?id=${film.id}">${film.naziv}</a></td>
				<td>${film.trajanje }</td>
				<td>${film.distributer }</td>
				<td>${film.reziser }</td>
				<td>${film.zemljaPorekla }</td>
				<td>${film.godinaProizvodnje }</td>
				<td>${film.glumci }</td>
				<td>${film.opis }</td>
				<td>${film.zanrovi }</td>
			</tr>
			
		</c:forEach>		
	</table>
		<p>
		<a href="DodajFilm.html">Dodaj film</a><br/>
		<br/>
		<a href="KorisniciServlet">Korisnici</a><br/>
		<br/>	
		<a href="Register.html">Registracija</a>
		</p>
</body>
</html>