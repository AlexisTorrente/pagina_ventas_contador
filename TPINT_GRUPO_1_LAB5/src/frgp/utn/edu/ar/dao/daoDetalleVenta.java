package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.DetalleVenta;

public interface daoDetalleVenta {

		
		public void insertarDetalleVenta(DetalleVenta detVenta);

		public ArrayList<DetalleVenta> obtenerDetalleVenta(int idVenta);

		//public void eliminarVenta(int idVenta);
}
