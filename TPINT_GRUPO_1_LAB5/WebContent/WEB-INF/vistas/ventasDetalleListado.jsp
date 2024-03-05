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
		
		<style type="text/css">
	    	<jsp:include page="css/estilos.css"></jsp:include>
		</style>
		
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
		
		
		<script>
	      $(document).ready( function () {
	      $('#tabla_detalleVentaListado_id').DataTable({
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
		
		<h1>Venta N° ${venta.idVenta}</h1>
		
		<h3>Cliente: ${venta.cliente.nombre} ${venta.cliente.apellido}</h3>
		<h3>Fecha: ${venta.fecha}</h3>
		<h3>Total: $ ${venta.precioTotal}</h3>
		<br>
		
		<table id="tabla_detalleVentaListado_id" border="1px">
			<thead>
				<tr>
					<th><b>Articulo</b></th>
					<th><b>Precio</b></th> 
					<th><b>Cantidad</b></th> 
					<th><b>Importe de la venta</b></th>
					<th><b>Importe de la compra</b></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaDetalleVenta}" var="item">
					<tr>
						<td><b>${item.articulo.nombre}</b></td> 
						<td><b>$ ${item.articulo.precio}</b></td> 
						<td><b>${item.cantidad}</b></td> 
						<td><b>$ ${item.totalVenta}</b></td>
						<td><b>$ ${item.totalCompra}</b></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
	</body>
</html>