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
		
		<script>
			function btnMas() {
			  // Lógica de tu función btnMas aquí
			  console.log("El botón más fue clicado.");
			}
		
		</script>
		
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
	
		<h1>Alta de ventas</h1>
		<form action="registroVenta.html" method="post">
			
			Fecha: <input type="date" name="fechaVentaStr" placeholder="Ingrese fecha" required>
			<br><br>
			
			Cliente:
			
			<select name="listaClientes" required>
				<c:forEach items="${listaClientes}" var="cliente">
					<option value="${cliente.dni}">${cliente.nombre} ${cliente.apellido}</option>
				</c:forEach>
			</select>
			<br><br>
		
			<h3>Articulos vendidos</h3>
			  
			<br>
			<!--  
				
			<select name="articulos" required>
				<c:forEach items="${listaArticulos}" var="articulo">
					<option value="${articulo.idArticulo}">${articulo.nombre}</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			Cantidad: <input type="number" name="cantidad" placeholder="Ingrtese cantidad" required>
			 -->
			
			
			
			
			<div id="articulosContainer">
			    <!--   
			    <select name="articulos_0" required>
			        <c:forEach items="${listaArticulos}" var="articulo">
			            <option value="${articulo.idArticulo}">${articulo.nombre}</option>
			        </c:forEach>
			    </select>
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    Cantidad: <input type="number" name="cantidad" placeholder="Ingrese cantidad" required>
			    <br>
			    -->
			</div>
			
			<br><br>
			<input type="submit" value="Guardar" name="btnGuardar" onclick="return getConfirmacionAlta();">
			<br>
			<p>${ Mensaje }</p>
			<br><br><br>
			
		</form>
		

		
		
		<br><br><br>
		
		<form action="agregarItemVenta.html" method="post">
    <button type="button" onclick="agregarCampo()">+</button>
</form>
<form action="eliminarItemVenta.html" method="post">
    <button type="button" onclick="eliminarCampo()">-</button>
</form>
		

	</body>
	<script>
	
	
	var listaArticulos = [
	    <c:forEach items="${listaArticulos}" var="articulo" varStatus="status">
	    
	        {
	            id: "${articulo.idArticulo}",
	            nombre: "${articulo.nombre}"
	        }<c:if test="${!status.last}">,</c:if>
	    </c:forEach>
	];
	
	var contadorCampos = 1;
	function agregarCampo() {
	    var container = document.getElementById("articulosContainer");

	    var select = document.createElement("select");
	    select.name = "articulos_" + contadorCampos;
	    select.required = true;

	    // con esto me copio las opciones al select
	    for (var i = 0; i < listaArticulos.length; i++) {
	        var option = document.createElement("option");
	        option.value = listaArticulos[i].id;
	        option.text = listaArticulos[i].nombre;
	        select.appendChild(option);

	        var br = document.createElement("br");
	        select.appendChild(br);
	    }

	    var cantidadInput = document.createElement("input");
	    cantidadInput.type = "number";
	    cantidadInput.name = "cantidad_" + contadorCampos;
	    cantidadInput.placeholder = "Ingrese cantidad";
	    cantidadInput.required = true;

	    container.appendChild(select);
	    container.appendChild(document.createTextNode("\u00A0\u00A0\u00A0\u00A0"));
	    container.appendChild(cantidadInput);

	    contadorCampos++;
	}

	function eliminarCampo() {
	    var container = document.getElementById("articulosContainer");
	    
	    var select = container.lastElementChild.previousElementSibling;
	    var cantidadInput = container.lastElementChild;
	    
	    container.removeChild(select);
	    container.removeChild(cantidadInput);
	}
	
	function getConfirmacionAlta(){
		return confirm("¿Desea dar de alta la venta?");
		}

	</script>
</html>













