package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Localidad;

public interface InegocioLocalidad {

	Localidad obtenerUnaLocalidad(Integer idLocalidad);
	
	ArrayList<Localidad> obtenerLocalidades();
}
