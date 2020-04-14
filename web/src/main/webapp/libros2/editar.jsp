<!DOCTYPE html>
<%@page import="es.avalon.dominio.Libro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Libro libro= (Libro)request.getAttribute("libro");

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="ServletLibros">

<p>
isbn:<input type="text" name="isbn" value="<%=libro.getIsbn()%>" />
</p>
<p>
titulo:<input type="text" name="titulo" value="<%=libro.getTitulo()%>"  />
</p>
<p>
autor:<input type="text" name="autor" value="<%=libro.getAutor()%>"  />
</p>
<p>
precio:<input type="text" name="precio" value="<%=libro.getPrecio()%>" />
</p>

<p>
categoria:<input type="text" name="categoria"  value="<%=libro.getCategoria().getNombre()%>" />
</p>
<p>
<input type="submit" value="aceptar"/>
</p>
<input type="hidden" name="accion" value="salvar"/>
</form>
</body>
</html>