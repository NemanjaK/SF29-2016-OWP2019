<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Film</title>
</head>
	<body>
		<table border=1>
			<form action="FilmoviServlet" method="post">
				<input type="hidden" name="action" value="update"/>
				<input type="hidden" name="id" value="${film.id}"/>				
				<tr><td>Naziv:</td><td><input type="text" name="naziv" value="${film.naziv}"/></td></tr>
				<tr><td>Trajanje:</td><td><input type="text" name="trajanje" value="${film.trajanje}"/></td></tr>
				<tr><td>Distributer:</td><td><input type="text" name="distributer" value="${film.distributer}"/></td></tr>
				<tr><td>Reziser:</td><td><input type="text" name="reziser" value="${film.reziser}"/></td></tr>
				<tr><td>Zemlja porekla:</td><td><input type="text" name="zemljaPorekla" value="${film.zemljaPorekla}"/></td></tr>
				<tr><td>Godina proizvodnje:</td><td><input type="text" name="godinaProizvodnje" value="${film.godinaProizvodnje}"/></td></tr>
				<tr><td>Glumci:</td><td><input type="text" name="glumci" value="${film.glumci}"/></td></tr>
				<tr><td>Opis:</td><td><input type="text" name="opis" value="${film.opis}"/></td></tr>
				<tr><td>Zanrovi:</td><td><input type="text" name="zanrovi" value="${film.zanrovi}"/></td></tr>
				<tr><td/><td><input type="submit" value="Izmeni"></td></tr>				
			</form>
				<form action="FilmoviServlet" method="post">
					<input type="hidden" name="action" value="delete"/>
					<input type="hidden" name="id" value="${film.id}"/>
					<tr><td/><td><input type="submit" value="Obrisi"></td></tr>
				</form>		
		</table>
	</body>
</html>