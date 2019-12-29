<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Korisnik</h3>
	<table border = "1">
		<tr>
			<th>Korisnicko ime</th>
			<th>Datum registracije</th>
			<th>Uloga</th>
		</tr>	

			<tr>
				<td>${korisnik.korisnickoIme}</td>
				<td>${korisnik.datumRegistracije}</td>
				<td>${korisnik.role}</td>
			</tr>
					
	</table>
		<form action="KorisniciServlet" method="post">
			<input type="hidden" name="action" value="delete"/>
			<input type="hidden" name="korisnickoIme" value="${korisnik.korisnickoIme}"/>
			<tr><td/><td><input type="submit" value="Obrisi"></td></tr>
		</form>
</body>
</html>