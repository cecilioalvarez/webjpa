<%@page import="es.avalon.dominio.Libro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
isbn:%{libro.isbn}
</p>
<p>
titulo:%{libro.titulo}
</p>
<p>
autor:%{libro.autor}
</p>
<p>
precio:%{libro.precio}
</p>
<p>
categoria:%{libro.categoria.nombre}
</p>

</body>
</html>