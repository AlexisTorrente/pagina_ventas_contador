package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Articulo;

public interface InegocioArticulo {

	void insertarArticulo(Articulo articulo);
	
	Articulo obtenerUnArticulo(Integer articulo);
	
	ArrayList<Articulo> obtenerArticulos();
	
	ArrayList<Articulo> obtenerArticulosConFiltro(int filtroMarca, int filtroTipo);

    void eliminarArticulo(Integer idUser) ;

	void actualizarArticulo(Articulo articulo);
}
