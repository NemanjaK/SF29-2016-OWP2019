<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Film</title>
</head>
	<body>
		<table>
			<form action="FilmoviServlet" method="post">
				<input type="hidden" name="action" value="update"/>
				<input type="hidden" name="id" value="${film.id}"/>				
				<tr><td align="right">Naziv:</td><td><input type="text" name="naziv" value="${film.naziv}"/></td></tr>
				<tr><td align="right">Trajanje:</td><td><input type="text" name="trajanje" value="${film.trajanje}"/></td></tr>
				<tr><td align="right">Distributer:</td><td><input type="text" name="distributer" value="${film.distributer}"/></td></tr>
				<tr><td align="right">Reziser:</td><td><input type="text" name="reziser" value="${film.reziser}"/></td></tr>
				<tr><td align="right">Zemlja porekla:</td><td><input type="text" name="zemljaPorekla" value="${film.zemljaPorekla}"/></td></tr>
				<tr><td align="right">Godina proizvodnje:</td><td><input type="text" name="godinaProizvodnje" value="${film.godinaProizvodnje}"/></td></tr>
				<tr><td align="right">Glumci:</td><td><input type="text" name="glumci" value="${film.glumci}"/></td></tr>
				<tr><td align="right">Opis:</td><td><input type="text" name="opis" value="${film.opis}"/></td></tr>
				<tr><td align="right">Zanrovi:</td><td><input type="text" name="zanrovi" value="${film.zanrovi}"/></td></tr>
				<tr><td/><td><input type="submit" value="Izmeni"></td></tr>				
			</form>
				<form action="FilmoviServlet" method="post">
					<input type="hidden" name="action" value="delete"/>
					<input type="hidden" name="id" value="${film.id}"/>
					<tr><td/><td><input type="submit" value="Obrisi"></td>
				</form>		
		</table>
	</body>
</html>