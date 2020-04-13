<%@page import="java.util.List"%>
<%@page import="es.avalon.dominio.Libro"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Libro> lista =(List<Libro>) request.getAttribute("listaLibros");

%>
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
		<% for (Libro l:lista ) { %>
		<tr>
			<td><%=l.getIsbn()%></td>
			<td><%=l.getTitulo()%></td>
			<td><%=l.getCategoria().getNombre()%></td>
			<td><%=l.getAutor()%></td>
			<td><%=l.getPrecio()%></td>
			<td><a href="ServletLibros?isbn=<%=l.getIsbn()%>&accion=borrar">borrar</a></td>
			<td><a href="ServletLibros?isbn=<%=l.getIsbn()%>&accion=detalle">detalle</a></td>
			<td><a href="ServletLibros?isbn=<%=l.getIsbn()%>&accion=editar">editar</a></td>
		</tr>	
		<% } %>
	</table>
	<a href="ServletLibros?accion=formularioInsertar">insertar</a>
	
</body>
</html>