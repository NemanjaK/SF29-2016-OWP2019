<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dodavanje proizvoda</title>
</head>
<body>
	<h3>Dodavanje filma:</h3>
	<form action="KarteServlet" method="post">
		<input type="hidden" name="action" value="add"/>
		<table>
				<tr><td align="right">Projekcija:</td><td><input type="text" name="id" value="${projekcija.id}"/></td></tr>
				<tr><td align="right">Sediste:</td><td><input type="text" name="sediste"/></td></tr>			
			<tr><td/><td><input type="submit" value="Dodaj" name="productSubmit"></td></tr>
		</table>
	</form>

</body>
</html>