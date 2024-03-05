package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Venta;

public interface InegocioVenta {

	// Alta de Venta
	void insertarVenta(Venta venta);

	// Obtiene una Venta por ID
	Venta obtenerVentaPorID(int idVenta);

	// Obtiene todas las Ventas
	ArrayList<Venta> obtenerVentas();
	
	ArrayList<Venta> obtenerVentasConFintro(String filtroFechaMayorQue, String filtroFechaMenorQue, String filtroPrecioMayorQue, String filtroPrecioMenorQue);

	// Elimina una Venta a partir del ID
	void eliminarVenta(int idVenta);
	
	// Obtiene todas las Ventas entre dos fechas
	ArrayList<Venta> obtenerVentasFiltradoFechas(String fechaInicio, String fechaFin);
}
