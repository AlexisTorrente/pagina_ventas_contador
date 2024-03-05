package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Localidad;

public interface daoLocalidad {

	public Localidad obtenerLocalidadPorID(int idLocalidad);

	public ArrayList<Localidad> obtenerLocalidades();
}
