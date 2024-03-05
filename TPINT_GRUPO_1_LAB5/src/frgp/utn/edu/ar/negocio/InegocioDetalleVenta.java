package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.DetalleVenta;

public interface InegocioDetalleVenta {

	public void insertarDetalleVenta(DetalleVenta detVenta);

	public ArrayList<DetalleVenta> obtenerDetalleVenta(int idVenta);
}
