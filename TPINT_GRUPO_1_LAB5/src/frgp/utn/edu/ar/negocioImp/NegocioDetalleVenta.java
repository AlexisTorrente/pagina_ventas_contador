package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoDetalleVenta;
import frgp.utn.edu.ar.entidad.DetalleVenta;
import frgp.utn.edu.ar.negocio.InegocioDetalleVenta;

public class NegocioDetalleVenta implements InegocioDetalleVenta{

	private daoDetalleVenta dataAccess = null;

	public void setDataAccess(daoDetalleVenta dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public void insertarDetalleVenta(DetalleVenta detVenta) {
		dataAccess.insertarDetalleVenta(detVenta);
		
	}

	@Override
	public ArrayList<DetalleVenta> obtenerDetalleVenta(int idVenta) {
		
		return dataAccess.obtenerDetalleVenta(idVenta);
	}
	
	
	
	
	
	
}
