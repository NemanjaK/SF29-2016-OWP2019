<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:if test="${ulogovanKorisnickoIme == null}">
	<c:redirect url="Login.html" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${ulogovanKorisnikRole == 'KORISNIK'}">
			<h3>Profil</h3>
			<table border="1">
				<tr>
					<th>Korisnicko ime</th>
					<th>Datum registracije</th>
					<th>Uloga</th>
				</tr>

				<tr>
					<td>${ulogovanKorisnickoIme}</td>
					<td>${ulogovanKorisnikRole}</td>
					<td>${ulogovanDatumRegistracije}</td>
				</tr>
			</table>
		</c:when>

		<c:when test="${ulogovanKorisnikRole == 'ADMIN'}">
			<h3>Korisnik</h3>
			<table border="1">
			<form action="KorisniciServlet" method="post">
					<input type="hidden" name="action" value="update"/>
					<input type="hidden" name="korisnickoIme" value="${korisnik.korisnickoIme}"/>
					<tr><td align="right">Korisnicko ime:</td><td><input type="text" name="newKorisnickoIme" value="${korisnik.korisnickoIme}"/></td></tr>
					<tr><td align="right">Datum registracije:</td><td>${korisnik.datumRegistracije}"</td></tr>
					<tr><td align="right">Uloga:</td><td>
						<select name="role"/>
							<option value="ADMIN">ADMIN</option>
							<option value="KORISNIK">KORISNIK</option>
						</select>
					</td></tr>
					<tr><td/><td><input type="submit" value="Izmeni"></td></tr>
				</form>
				<form action="KorisniciServlet" method="post">
					<input type="hidden" name="action" value="delete"/> 
					<input	type="hidden" name="korisnickoIme" value="${korisnik.korisnickoIme}" />
					<tr>
						<td/>
						<td><input type="submit" value="Obrisi"></td>
					</tr>
				</form>
			</table>
		</c:when>
	</c:choose>

</body>
</html>