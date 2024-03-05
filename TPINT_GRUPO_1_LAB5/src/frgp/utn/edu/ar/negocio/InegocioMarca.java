package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Marca;

public interface InegocioMarca {

	Marca obtenerUnaMarca(Integer idMarca);
	
	ArrayList<Marca> obtenerMarcas();
	
}
