package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Genero;

public interface InegocioGenero{

	Genero obtenerUnGenero(Integer idGenero);
	
	ArrayList<Genero> obtenerGeneros();
}
