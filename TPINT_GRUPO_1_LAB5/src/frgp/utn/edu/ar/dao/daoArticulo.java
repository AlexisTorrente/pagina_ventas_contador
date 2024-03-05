package frgp.utn.edu.ar.dao;

import frgp.utn.edu.ar.entidad.Articulo;
import java.util.ArrayList;

public interface daoArticulo {

	// Alta de Articulo
	public void insertarArticulo(Articulo articulo);

	// Obtiene un Art por ID
	public Articulo obtenerArticuloPorID(int idArticulo);

	// Obtiene todas los articulos
	public ArrayList<Articulo> obtenerArticulos();
	
	public ArrayList<Articulo> obtenerArticulosConFiltro(String filtro);

	// Elimina un articulo a partir del ID
	public void eliminarArticulo(int idArticulo);

	// Actualiza los datos de un Art
	public void actualizarArticulo(Articulo articulo);

}
