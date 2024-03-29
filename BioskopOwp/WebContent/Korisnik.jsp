<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
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
		<c:when test="${ulogovanKorisnikRole.role == 'KORISNIK'}">
			<h3>Profil</h3>
			<table border="1">
				<tr>
					<th>Korisnicko ime</th>
					<th>Datum registracije</th>
					<th>Uloga</th>
				</tr>

				<tr>
					<td>${ulogovanKorisnickoIme}</td>
					<td><fmt:formatDate value="${ulogovanDatumRegistracije}" pattern="dd/MM/yyyy"/></td>
					<td>${ulogovanKorisnikRole.role}</td>
				</tr>
			</table>
			<h4>Izmeni loznku:</h4>
			<table>	
					<form action="KorisnikServlet" method="post">
					<input type="hidden" name="action" value="update"/>
					<input type="hidden" name="korisnickoIme" value="${ulogovanKorisnikRole.korisnickoIme}"/>		
					<tr><td align="right">Lozinka:</td><td><input type="password" name="lozinka"/></td></tr>
					<tr><td align="right">Ponovljena lozinka:</td><td><input type="password" name="ponovljenaLozinka"/></td></tr>					
					<tr><td/><td><input type="submit" value="Izmeni"></td></tr>
				</form>
			</table>
		</c:when>

		<c:when test="${ulogovanKorisnikRole.role == 'ADMIN'}">
			<h3>Korisnik</h3>
			<table border="1">
			<form action="KorisniciServlet" method="post">
					<input type="hidden" name="action" value="update"/>
					<input type="hidden" name="korisnickoIme" value="${korisnik.korisnickoIme}"/>
					<tr><td align="right">Korisnicko ime:</td><td><input type="text" name="newKorisnickoIme" value="${korisnik.korisnickoIme}"/></td></tr>
					<tr><td align="right">Datum registracije:</td><td><fmt:formatDate value="${korisnik.datumRegistracije}" pattern="dd/MM/yyyy"/></td></tr>
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
			<a href="LogoutServlet">Odjava</a><br/> <br />
	
</body>
</html>