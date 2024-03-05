package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.TipoArticulo;

public interface daoTipoArticulo {

		//public void insertarMarca(Marca marca);
		
		public TipoArticulo obtenerTipoArtPorID(int idTipo);

		public ArrayList<TipoArticulo> obtenerTiposArt();

		//public void eliminarArticulo(int idArticulo);

		//public void actualizarArticulo(Articulo articulo);
}
