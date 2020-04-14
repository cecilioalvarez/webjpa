<%@page import="java.util.List"%>
<%@page import="es.avalon.dominio.Libro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>ISBN</th>
			<th>Titulo</th>
			<th>Categoria</th>
			<th>Autor</th>
			<th>Precio</th>
		</tr>
		<c:forEach items="${listaLibros}" var="libro">
			<tr>
				<td>${libro.isbn}</td>
				<td>${libro.titulo}</td>
				<td>${libro.categoria.nombre}</td>
				<td>${libro.autor}</td>
				<td>${libro.precio}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>