<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${film.naziv}</title>
</head>
	<body>
	<c:choose>
		<c:when test="${ulogovanKorisnikRole.role == null || ulogovanKorisnikRole.role == 'KORISNIK'}">
			<table>
				<tr><td align="right"><b>Naziv:</b></td><td>${film.naziv}</td></tr>			
				<tr><td align="right"><b>Trajanje:</b></td><td>${film.trajanje}</td></tr>
				<tr><td align="right"><b>Distributer:</b></td><td>${film.distributer}</td></tr>
				<tr><td align="right"><b>Reziser:</b></td><td>${film.reziser}</td></tr>
				<tr><td align="right"><b>Zemlja porekla:</b></td><td>${film.zemljaPorekla}</td></tr>
				<tr><td align="right"><b>Godina proizvodnje:</b></td><td>${film.godinaProizvodnje}</td></tr>
				<tr><td align="right"><b>Glumci:</b></td><td>${film.glumci}</td></tr>
				<tr><td align="right"><b>Opis:</b></td><td>${film.opis}</td></tr>
				<tr><td align="right"><b>Zanrovi:</b></td><td>${film.zanrovi}</td></tr>
			</table>
			
			<p>
			
			<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK'}">
			<a href="KorisnikServlet">Profil</a><br /> <br />
			</c:if>
			
			<c:if test="${ulogovanKorisnikRole.role == 'KORISNIK' || ulogovanKorisnikRole.role == 'ADMIN'}">
			<a href="LogoutServlet">Odjava</a><br/> <br />
			</c:if>
			</p>
		</c:when>
	<c:when test="${ulogovanKorisnikRole.role == 'ADMIN'}">	
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
			<p>	
			<c:if test="${ulogovanKorisnikRole.role == 'ADMIN'}">
			<a href="KorisniciServlet">Upravljanje korisnicima</a><br /> <br />
			</c:if>	
			</p>
		</table>
		</c:when>
		</c:choose>
		<p>
			<a href="FilmoviServlet">Povratak</a>
		</p>
	</body>
</html>