<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projekcija</title>
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

		<tr>
			<td>${projekcija.tipProjekcije.naziv}</td>
			<td>${projekcija.sala.naziv}</td>
			<td>${projekcija.datumVreme}</td>
			<td>${projekcija.cenaKarte}</td>
			<td>${projekcija.adminDodaoProjekciju.korisnickoIme}</td>
			<td><a href="FilmServlet?id=${film.id}">${projekcija.prikazaniFilm.naziv}</a></td>
		</tr>
	</table>

</body>
</html>