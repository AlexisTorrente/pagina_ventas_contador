<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listado de Ventas</title>
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
	      $('#tablaListadoVentasContador').DataTable({
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
			<li>
				<a href="redireccionar_usuario_alta_Contador.html"><b>Agregar Usuario</b></a>
			</li>
			<li>
				<div style="margin-right: 0px"><b style="color: white;">Bienvenido <%=nombreUsuario %></b></div>
			</li>
			<li>
				<a href="redireccionar_index.html"><b>Cerrar Sesión</b></a>
			</li>
		</ul>
	
		
			
			
		
		
		<h1>Listado de Ventas Contador</h1>
		<form action="ventasListadoContadorFiltro.html">
			Fecha inicio <input type="date" name ="filtroFechaMayorQue" value="${filtroFechaInicio}"> <br>
			Fecha fin <input type="date" name ="filtroFechaMenorQue" value="${filtroFechaFin}"> <br>
			<input type="submit" value="Filtrar" name="btnFiltros" onclick="return getConfirmacionFiltro();">
		</form>
		
		<br><br>
		<table id="tablaListadoVentasContador" border="1px">
					<thead>
						<tr>
							<th><b>N° venta</b></th> 
							<th><b>DNI cliente</b></th> 
							<th><b>Fecha de venta</b></th> 
							<th><b>Importe de venta</b></th>
							<th><b>Importe de compra</b></th>
						</tr>
					</thead>
					
					<tbody>
					<c:forEach items="${listaVentas}" var="item" varStatus="status">
						<tr>
							<th><b>${item.idVenta}</b></th> 
							<th><b>${item.cliente.dni}</b></th>
							<th><b>${item.fecha}</b></th>
							<th><b>${item.precioTotal}</b></th>
							<th><b>${importeCompras[status.index]}</b></th>
						</tr>
					</c:forEach>
					</tbody>
				</table>
		
	<h2>${Mensaje}</h2>	
		
<script type="text/javascript">
function getConfirmacionFiltro(){
	return confirm("¿Desea aplicar el filtrado?");
	}
	
</script>
	
	</body>
</html>