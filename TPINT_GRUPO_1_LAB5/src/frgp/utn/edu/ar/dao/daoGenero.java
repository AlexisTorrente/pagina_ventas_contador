package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Genero;
public interface daoGenero {

	public Genero obtenerGeneroPorID(int idGenero);

	public ArrayList<Genero> obtenerGeneros();
}
