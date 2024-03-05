<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Alta de Articulos</title>
		<style type="text/css">
	    	<jsp:include page="css/estilos.css"></jsp:include>
		</style>
		<script>
		  function agregarArticulo() {
		    var numArticulos = document.getElementById("numArticulos");
		    var currentNumArticulos = parseInt(numArticulos.value);
		    currentNumArticulos++;
		    numArticulos.value = currentNumArticulos;
		
		    // Agregar la porción de código dinámicamente
		    var selectElement = document.createElement("select");
		    selectElement.name = "articulos" + currentNumArticulos;
		    selectElement.required = true;
		    <c:forEach items="${listaArticulos}" var="articulo">
		      var optionElement = document.createElement("option");
		      optionElement.value = "${articulo.id}";
		      optionElement.text = "${articulo.nombre}";
		      selectElement.appendChild(optionElement);
		    </c:forEach>
		
		    var inputElement = document.createElement("input");
		    inputElement.type = "number";
		    inputElement.name = "cantidad" + currentNumArticulos;
		    inputElement.placeholder = "Ingrese cantidad";
		    inputElement.required = true;
		
		    var brElement = document.createElement("br");
		
		    var formElement = document.getElementsByTagName("form")[0];
		    formElement.appendChild(selectElement);
		    formElement.appendChild(document.createTextNode("    "));
		    formElement.appendChild(inputElement);
		    formElement.appendChild(brElement);
		  }
		
		  function eliminarArticulo() {
		    var numArticulos = document.getElementById("numArticulos");
		    var currentNumArticulos = parseInt(numArticulos.value);
		
		    if (currentNumArticulos > 0) {
		      // Eliminar la última porción de código agregada
		      var formElement = document.getElementsByTagName("form")[0];
		      formElement.removeChild(formElement.lastElementChild); // <br> element
		      formElement.removeChild(formElement.lastElementChild); // <input> element
		      formElement.removeChild(formElement.lastElementChild); // <select> element
		
		      currentNumArticulos--;
		      numArticulos.value = currentNumArticulos;
		    }
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
		
		<h1>Alta de articulos</h1>
		<form action="articulosAlta.html" method="post">
			Nombre: <input type="text" name="nombreArticulo" placeholder="Ingrese nombre de articulo" maxlength="50" required>
			<br><br>
			
			Descripcion: <input type="text" name="descripcionArticulo" placeholder="Ingrese descripción" maxlength="50" required>
			<br><br>
			
			Marca:
			<select name="marcaArticulo" required>
				<c:forEach items="${listaMarcas}" var="marca">
					<option value="${marca.id}">${marca.nombre}</option>
				</c:forEach>
			</select>
			<br><br>
			
			Tipo: 
			<select name="tipoArticulo" required>
				<c:forEach items="${listaTipos}" var="tipoArt">
					<option value="${tipoArt.id}">${tipoArt.nombre}</option>
				</c:forEach>
			</select>
			<br><br>
			
			Precio de venta: <input type="number" name="precioArticulo" placeholder="Ingrese precio" required>
			<br><br>
			<input type="submit" value="Guardar" name="btnGuardar" onclick="return getConfirmacionAlta();">
			
			<p>${ Mensaje }</p>
		</form>
	</body>



<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea dar de alta el articulo?");
	}
	
</script>	






</form>
</body>
</html>