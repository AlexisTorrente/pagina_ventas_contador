package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoVenta;
import frgp.utn.edu.ar.entidad.Venta;
import frgp.utn.edu.ar.negocio.InegocioVenta;

public class NegocioVenta implements InegocioVenta {

	private daoVenta dataAccess = null;

	public void setDataAccess(daoVenta dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public void insertarVenta(Venta venta) {
		dataAccess.insertarVenta(venta);

	}

	@Override
	public Venta obtenerVentaPorID(int idVenta) {
		return dataAccess.obtenerVentaPorID(idVenta);
	}

	@Override
	public ArrayList<Venta> obtenerVentas() {
		return dataAccess.obtenerVentas();
	}

	@Override
	public void eliminarVenta(int idVenta) {
		dataAccess.eliminarVenta(idVenta);

	}

	@Override
	public ArrayList<Venta> obtenerVentasFiltradoFechas(String fechaInicio, String fechaFin) {
		return dataAccess.obtenerVentasFiltradoFechas(fechaInicio, fechaFin);
	}

	@Override
	public ArrayList<Venta> obtenerVentasConFintro(String filtroFechaMayorQue, String filtroFechaMenorQue, String filtroPrecioMayorQue, String filtroPrecioMenorQue) {
		
		String filtro = "";
		
		if(filtroPrecioMayorQue != "" || filtroPrecioMenorQue != "") {
			
			if(filtroPrecioMayorQue != "") {
				filtro += " AND v.precioTotal >= " + filtroPrecioMayorQue;
			}
			if(filtroPrecioMenorQue != "") {
				filtro += " AND v.precioTotal <= " + filtroPrecioMenorQue;
			}
		}
		
		if(filtroFechaMayorQue != "" || filtroFechaMenorQue != "") {
			
			if(filtroFechaMayorQue != "") {
				filtro += " AND v.fecha >= '" + filtroFechaMayorQue + "'";
			}
			if(filtroFechaMenorQue != "") {
				filtro += " AND v.fecha <= '" + filtroFechaMenorQue + "'";
			}
		}
		
		if(filtro != "") {
			return dataAccess.obtenerVentasConFiltro(filtro);
		}
		else{
			return dataAccess.obtenerVentas();	
		}
	}

}
