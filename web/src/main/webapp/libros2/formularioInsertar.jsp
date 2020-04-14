<%@page import="es.avalon.dominio.Categoria"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ServletLibros">

		<p>
			isbn:<input type="text" name="isbn" />
		</p>
		<p>
			titulo:<input type="text" name="titulo" />
		</p>
		<p>
			autor:<input type="text" name="autor" />
		</p>
		<p>
			precio:<input type="text" name="precio" />
		</p>

		categoria:<select name="categoria">

			<c:forEach items="${listaCategorias}" var="c">

				<option>${c.nombre}</option>
			</c:forEach>
		</select>

		<p>
			<input type="hidden" name="accion" value="insertar" /> <input
				type="submit" value="aceptar" />
		</p>
	</form>
</body>
</html>