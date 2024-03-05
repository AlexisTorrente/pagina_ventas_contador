package frgp.utn.edu.ar.dao;

import frgp.utn.edu.ar.entidad.Venta;
import java.util.ArrayList;

public interface daoVenta {

	// Alta de Venta
	public void insertarVenta(Venta venta);

	// Obtiene una Venta por ID
	public Venta obtenerVentaPorID(int idVenta);

	// Obtiene todas las Ventas
	public ArrayList<Venta> obtenerVentas();

	public ArrayList<Venta> obtenerVentasConFiltro(String filtro);
	
	// Elimina una Venta a partir del ID
	public void eliminarVenta(int idVenta);
	
	// Obtiene todas las Ventas entre dos fechas
	public ArrayList<Venta> obtenerVentasFiltradoFechas(String fechaInicio, String fechaFin);

}
