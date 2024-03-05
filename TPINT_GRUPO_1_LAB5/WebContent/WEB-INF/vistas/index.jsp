<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Inicio Sesion</title>
		
		<%
			session.invalidate();
		%>
	</head>
	<body>
		<h1>Iniciar sesion</h1>
		<form action="inicio_sesion.html" method="post">
			Usuario: <input type="text" name="usuarioTxt" placeholder="Ingrese su usuario" > <br>
			Contraseña: <input type="password" name="contraseniaTxt" placeholder="Ingrese su constraseña" > <br>
			<input type="submit" value="Aceptar" name="btnAceptar">
		</form>
		
	</body>
	
	
	
</html>