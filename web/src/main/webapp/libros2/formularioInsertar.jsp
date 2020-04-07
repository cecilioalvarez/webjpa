<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="ServletLibros" method="POST">

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

<p>
categoria:<input type="text" name="categoria" />
</p>
<p>
<input type="hidden" name="accion" value="insertar"/>
<input type="submit" value="aceptar"/>
</p>
</form>
</body>
</html>