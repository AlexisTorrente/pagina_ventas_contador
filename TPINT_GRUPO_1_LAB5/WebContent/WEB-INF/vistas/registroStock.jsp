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
				<a href="redireccionar_index.html"><b>Cerrar Sesi�n</b></a>
			</li>
		</ul>
		
		
		<h1>Registrar Stock</h1>
		
		<form action="registroStock.html">
		<!--
		ID Articulo: <input type="number" name="idArticuloStock" placeholder="Ingrese ID del articulo"> <br><br>
		-->
		Articulo <select name="idArticuloStock" required>
				<c:forEach items="${listaArticulos}" var="articulo">
					<option value="${articulo.idArticulo}">${articulo.nombre}</option>
				</c:forEach>
			</select> 
			<br><br>
		Fecha de ingreso: <input type="date" name="fechaIngresoArticulo" placeholder="Ingrese fecha" required> <br><br>
		Cantidad: <input type="number" name="cantidadArticulos" placeholder="Ingrese cantidad" required> <br><br>
		Precio de compra: <input type="number" name="precioCompra" placeholder="Ingrese monto" required> <br><br>
		
		<input type="submit" name="btnAceptarStock" value="Aceptar" onclick="return getConfirmacionAlta();">
		<br>
		
		<p>${ Mensaje }</p>
		
<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("�Desea registrar stock?");
	}
	
</script>		
		
		</form>
	</body>
</html>