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
		
		<h1>Modificación de articulos</h1>
		
		<form action="cargarDatosArticulo.html">
			Articulo <select name="ddlArticulos" required>
				<c:forEach items="${listaArticulos}" var="articulo">
					<option value="${articulo.idArticulo}" <c:if test="${articulo.idArticulo == articuloSeleccionado}">selected</c:if>>${articulo.nombre}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Cargar datos" name="btnCargarDatos">
		</form>
		<br>
		
		<div <c:if test="${articuloSeleccionado == null}">hidden</c:if>>
			<form action="articulosModificacion.html">
				<!--
				ID: <input type="text" name="idArticulo" placeholder="Ingrese ID del articulo" maxlength="50" required>
				-->
				<input type="hidden" name ="idArticulo" value="${articulo.idArticulo}">
				
				Nombre: <input type="text" name="nombreArticulo" placeholder="Ingrese nombre de articulo" maxlength="50" value="${articulo.nombre}" required>
				<br><br>
				
				Descripcion: <input type="text" name="descripcionArticulo" placeholder="Ingrese descripción" maxlength="50" value="${articulo.descripcion}" required>
				<br><br>
				
				Marca:
				<select name="marcaArticulo" required>
					<c:forEach items="${listaMarcas}" var="marca">
						<option value="${marca.id}" <c:if test="${marca.id == articulo.marca.id}">selected</c:if>>${marca.nombre}</option>
					</c:forEach>
				</select>
				<br><br>
				
				Tipo: 
				<select name="tipoArticulo" required>
					<c:forEach items="${listaTipos}" var="tipoArt">
						<option value="${tipoArt.id}" <c:if test="${tipoArt.id == articulo.tipo.id}">selected</c:if>>${tipoArt.nombre}</option>
					</c:forEach>
				</select>
				<br><br>
				
				Precio de venta: <input type="number" name="precioArticulo" placeholder="Ingrese precio" value="${articulo.precio}" required>
				<br><br>
				<input type="submit" value="Modificar" name="btnModificar"  onclick="return getConfirmacionModificar();">
				
			</form>
		</div>
		<p>${ Mensaje }</p>
	</body>



	<script type="text/javascript">
function getConfirmacionModificar(){
	return confirm("¿Desea aplicar las modificaciones?");
	}
	
</script>






</form>
</body>
</html>