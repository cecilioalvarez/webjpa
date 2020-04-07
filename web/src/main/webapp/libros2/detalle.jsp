<%@page import="es.avalon.dominio.Libro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Libro libro= (Libro) request.getAttribute("libro");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
isbn:<%=libro.getIsbn()%>
</p>
<p>
titulo:<%=libro.getTitulo()%>
</p>
<p>
autor:<%=libro.getAutor()%>
</p>
<p>
precio:<%=libro.getPrecio()%>
</p>
<p>
categoria:<%=libro.getCategoria()%>
</p>

</body>
</html>