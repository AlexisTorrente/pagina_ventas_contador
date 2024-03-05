<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Registro de usuario</title>
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
		<% if(tipoUsuario == 1){%>
		
		
		
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
			<%}%>
			<% if(tipoUsuario == 2){%>
			<li>
				<a href="redireccionar_usuario_alta_Contador.html"><b>Agregar Usuario</b></a>
			</li>
			<%}%>
			<li>
				<div style="margin-right: 0px"><b style="color: white;">Bienvenido <%=nombreUsuario %></b></div>
			</li>
			<li>
				<a href="redireccionar_index.html"><b>Cerrar Sesión</b></a>
			</li>
		</ul>
		
		
		
		<h1>Registrar un nuevo usuario:</h1>
		<form action="usuario_alta.html" method="post">
		<table style="border-style: none" border="0">
		<tr><td>Nombre de Usuario</td> <td><input type="text" name="usuarioTxt" placeholder="Ingrese su usuario" required></td></tr>
		<tr><td>Contraseña</td> <td><input type="password" name="contraseniaTxt" placeholder="Ingrese su constraseña" required></td></tr>
		<tr><td>Repetir Contraseña</td> <td><input type="password" name="contraConf" placeholder="Repita la contraseña" required></td></tr>
		<tr>
		<td>Tipo de usuario</td> 
		<td>
		<select name="IDtipoUsuarioAregistrar" required>
				<c:forEach items="${listaTipos}" var="TipoUsuario">
					<option value="${TipoUsuario.id}">${TipoUsuario.descripcion}</option>
				</c:forEach>
			</select>
		</td>
		
		</tr>
		</table>
			<input type="hidden" name="tipoUsuario" value=<%=tipoUsuario%>  >
			<input type="submit" value="Aceptar" name="btnAceptar" onclick="return getConfirmacionAlta();">
		<br><br>
		<b>${Mensaje}</b>
			
<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea dar de alta al usuario?");
	}
	
</script>				
			
		</form>
		
	</body>
	
	
	
</html>