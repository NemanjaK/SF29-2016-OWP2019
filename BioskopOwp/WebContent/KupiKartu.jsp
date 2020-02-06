<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:if test="${ulogovanKorisnickoIme == null}">
	<c:redirect url="Login.html"/>
</c:if>	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Kupovina karte</title>
</head>
<body>
	<h3>Kupovina karte:</h3>
	<form action="KarteServlet" method="post">
		<input type="hidden" name="action" value="add"/>
		<table>
			<tr><td align="right">Pocetak projekcije:</td><td><input type="hidden" name="id" value="${projekcija.id}">
													<input type="text" name="sala" value="${projekcija.datumVreme}" disabled>
			</td></tr>
			<tr><td align="right">Sala:</td><td><input type="hidden" name="sala" value="${projekcija.sala.id}"/>
												<input type="text" name="sala" value="${projekcija.sala.naziv}" disabled>
			</td></tr>				
			<tr><td align="right">Sediste:</td><td><select name="sediste">
														<c:forEach items="${sedista}" var="sediste">
															<option value="${sediste.redniBroj}">${sediste.redniBroj}</option>
														</c:forEach>
													</select></td></tr>
			<tr><td/><td><input type="submit" value="Dodaj" name="productSubmit"></td></tr>
		</table>
	</form>
</body>
</html>