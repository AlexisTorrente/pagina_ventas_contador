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
		
		<style type="text/css">
			table {
			  margin: 0 auto;
			}
			table {
			  color: #333;
			  background: white;
			  border: 1px solid grey;
			  font-size: 12pt;
			  border-collapse: collapse;
			}
			table thead th,
			table tfoot th {
			  color: #777;
			  background: rgba(0,0,0,.1);
			}
			table caption {
			  padding:.5em;
			}
			table th,
			table td {
			  padding: .5em;
			  border: 1px solid lightgrey;
			}
		</style>
		
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
		
		
		<script>
	      $(document).ready( function () {
	      $('#tabla_clientesListado_id').DataTable({
	    	lengthMenu: [5, 10, 15, 30, 60],
	        language: {
	          processing: "Tratamiento en curso...",
	          search: "Buscar&nbsp;:",
	          lengthMenu: "Mostrar _MENU_ registros",
	          info: "Mostrando del registro _START_ al _END_ de un total de _TOTAL_ registros",
	          infoEmpty: "No existen datos.",
	          loadingRecords: "Cargando...",
	          zeroRecords: "No se encontraron datos con tu busqueda",
	          emptyTable: "No hay datos disponibles en la tabla.",
	          paginate: {
	            first: "Primero",
	            previous: "Anterior",
	            next: "Siguiente",
	            last: "Último"
	          },
	        }
	      });
	      } );
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
		
		
		<h1>Clientes</h1>
		<!-- 
		Buscar: <input type="text" name="Busqueda" maxlength="100" required>
		<br><br>
		 -->
		 
		 <form action="clientesListado.html" method="post">
			Genero:
			<select name="generoCliente" required>
				<option value= "0">Todos</option>
				<c:forEach items="${listaGeneros}" var="genero">
					<option value="${genero.id}" <c:if test="${genero.id == filtroGenero}">selected</c:if>>${genero.descripcion}</option>
				</c:forEach>
			</select>
			<br><br>
			
			Localidad:
			<select name="localidadCliente" required>
				<option value= "0">Todos</option>
				<c:forEach items="${listaLocalidades}" var="localidad">
					<option value="${localidad.id}" <c:if test="${localidad.id == filtroLocalidad}">selected</c:if>>${localidad.nombre}</option>
				</c:forEach>
			</select>
			<br><br>
			
			Fecha de nacimiento: 
			<br>
			Fecha inicio <input type="date" name ="fechaMayorQue" value="${filtroFechaInicio}"> Fecha fin <input type="date" name ="fechaMenorQue" value="${filtroFechaFin}">
			<br><br>
			
			<input type="submit" value="Aplicar" name="btnAplicar">
			<br><br>
		</form>
		<form action="redireccionar_clientesListado.html" method="post">
			<input type="submit" value="Quitar filtros" name="btnQuitarFiltros">
		</form>
		
		<br><br><br>
		<table id="tabla_clientesListado_id">
			<thead>
				<tr>
					<th><b>DNI</b></th> 
					<th><b>Nombre</b></th>
					<th><b>Apellido</b></th> 
					<th><b>Sexo</b></th>
					<th><b>Fecha de nacimiento</b></th>
					<th><b>Direccion</b></th> 
					<th><b>Localidad</b></th> 
					<th><b>Correo</b></th>
					<th><b>Telefono</b></th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${listaClientes}" var="item">
				<tr>
					<th><b>${item.dni}</b></th>
					<th><b>${item.nombre}</b></th>
					<th><b>${item.apellido}</b></th>
					<th><b>${item.genero.descripcion}</b></th>
					<th><b>${item.fechaNac}</b></th>
					<th><b>${item.direccion}</b></th> 
					<th><b>${item.localidad.nombre}</b></th> 
					<th><b>${item.correo}</b></th> 
					<th><b>${item.telefono}</b></th>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		

		
	</body>
</html>