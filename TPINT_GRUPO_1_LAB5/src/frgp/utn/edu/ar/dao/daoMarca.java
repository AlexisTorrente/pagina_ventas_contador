package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Marca;

public interface daoMarca {
	
	public Marca obtenerMarcaPorID(int idMarca);

	public ArrayList<Marca> obtenerMarcas();
}
