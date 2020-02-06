<%@page import="dao.tipProjekcijeDAO"%>
<%@page import="model.TipProjekcije"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:if test="${ulogovanKorisnickoIme == null || ulogovanKorisnikRole.role == 'KORISNIK'}">
	<c:redirect url="Login.html"/>
</c:if>	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dodavanje projekcije</title>
</head>
<body>
	<h3>Dodavanje projekcije:</h3>		
	<form action="AddProjekcijaServlet" method="post">
		<input type="hidden" name="action" value="add"/>
		<table>
				<tr><td align="right">Sala</td><td>
				<select name="sala">
						<c:forEach items="${sale}" var="sala">
						    <option value="${sala.id}">${sala.naziv}</option>
						</c:forEach>
				</select>	
				<tr><td align="right">Tip projekcije:</td><td>
				<select name="tipProjekcije">
						<c:forEach items="${tipovi}" var="tip">
						    <option value="${tip.id}">${tip.naziv}</option>
						</c:forEach>
				</select>			
				<tr><td align="right">Vreme:</td><td><input type="date" name="date"/><input type="time" name="time"/></td></tr>			
				<tr><td align="right">Cena karte:</td><td><input type="text" name="cenaKarte"/></td></tr>
				<tr><td align="right">Film</td><td>
				<select name="film">
						<c:forEach items="${filmovi}" var="film">
						    <option value="${film.id}">${film.naziv}</option>
						</c:forEach>
				</select>			<tr><td/><td><input type="submit" value="Dodaj" name="productSubmit"></td></tr>
		</table>
	</form>
		<p>
			<a href="FilmoviServlet">Povratak</a>
		</p>
		
		<a href="LogoutServlet">Odjava</a><br/> <br />
</body>
</html>