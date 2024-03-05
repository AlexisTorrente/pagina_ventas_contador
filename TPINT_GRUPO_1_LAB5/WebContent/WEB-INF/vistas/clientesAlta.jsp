<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		
		<style type="text/css">
	    	<jsp:include page="css/estilos.css"></jsp:include>
		</style>
		
		<%
	String nombreUsuario ="";
	int tipoUsuario = -1;
	
	try{
	nombreUsuario =(String) session.getAttribute("nombreUsuario");
	tipoUsuario = (int) session.getAttribute("tipoUsuario");
	}
	catch (Exception e){
		response.sendRedirect("redireccionar_index.html");
	}
	%>
		
		<script>
   		 $(document).ready(function() {
   	       if(tipoUsuario == -1){
   	    	response.sendRedirect("redireccionar_index.html");
   	       }

  	 	 });
  		</script>
		
	</head>
	<body>
		<ul class="nav"> 
			<li><a><b>Articulos</b></a>
				<ul>
			  		<li><a href="redireccionar_articulosListado.html"><b>Listado</b></a></li>
			  		<li><a href="redireccionar_articulosAlta.html"><b>Alta</b></a></li>
			  		<li><a href="redireccionar_articulosBaja.html"><b>Baja</b></a></li>
			  		<li><a href="redireccionar_articulosModificacion.html"><b>Modificacion</b></a></li>
		  		</ul>
			</li>
			<li><a><b>Clientes</b></a>
				<ul>
			  		<li><a href="redireccionar_clientesListado.html"><b>Listado</b></a></li>
			  		<li><a href="redireccionar_clientesAlta.html"><b>Alta</b></a></li>
			  		<li><a href="redireccionar_clientesBaja.html"><b>Baja</b></a></li>
			  		<li><a href="redireccionar_clientesModificacion.html"><b>Modificacion</b></a></li>
		  		</ul>
			</li>
			<li><a><b>Ventas</b></a>
				<ul>
			  		<li><a href="redireccionar_ventasListado.html"><b>Listado</b></a></li>
			  		<li><a href="redireccionar_ventasAlta.html"><b>Alta</b></a></li>
			  		<li><a href="redireccionar_ventasBaja.html"><b>Baja</b></a></li>
		  		</ul>
			</li>
			<li>
				<a href="redireccionar_registroStock.html"><b>Registrar Stock</b></a>
			</li>
			<li>
				<a href="redireccionar_usuario_alta.html"><b>Agregar Usuario</b></a>
			</li>
			<li>
				<div style="margin-right: 0px"><b style="color: white;">Bienvenido <%=nombreUsuario %></b></div>
			</li>
			<li>
				<a href="redireccionar_index.html"><b>Cerrar Sesión</b></a>
			</li>
		</ul>
		
		
		<h1>Alta cliente</h1>
		<form action="clientesAlta.html" method="post">
			Dni: <input type="text" pattern="^[1-9]\d*$" title="Se debe rellenar con números" minlength="7" maxlength="10" name="dniCliente" placeholder="Ingrese DNI" required>
			<br><br>
			
			Nombre: <input type="text" name="nombreCliente" placeholder="Ingrese nombre" maxlength="50" required>
			<br><br>
			
			Apellido: <input type="text" name="apellidoCliente" placeholder="Ingrese apellido" maxlength="50" required>
			<br><br>
			
			Genero: 
			<select name="generoCliente" required>
				<c:forEach items="${listaGeneros}" var="genero">
					<option value="${genero.id}">${genero.descripcion}</option>
				</c:forEach>
			</select>
			<br><br>
			
			
			Fecha de nacimiento: <input type="date" name="fechaNacimientoCliente" placeholder="Ingrese fecha" required>
			<br><br>
			
			Dirección: <input type="text" name="direccionCliente" placeholder="Ingrese dirección" maxlenght="50" required> <br><br>
			
			Localidad:
			<select name="localidadCliente" required>
				<c:forEach items="${listaLocalidades}" var="localidad">
					<option value="${localidad.id}">${localidad.nombre}</option>
				</c:forEach>
			</select>
			<br><br>
			
			Correo Electrónico: <input type="email" placeholder="Ingrese Correo Electronico" name="correoCliente" required ><br><br>
			
			Teléfono:<input type="tel" placeholder="Ingrese número telefonico" name="telefonoCliente" pattern="[\+]\d{12}" title="Debe ser formato: +54 11 12345678 (todo junto)" required><br><br>
			
			<input type="submit" value="Aceptar" name="btnAceptar" onclick="return getConfirmacionAlta();">
			
			<p>${ Mensaje }</p>
		
			
<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea dar de alta al cliente?");
	}
	
</script>
			
		</form>
	</body>
</html>