package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoGenero;
import frgp.utn.edu.ar.entidad.Genero;
import frgp.utn.edu.ar.negocio.InegocioGenero;

public class NegocioGenero implements InegocioGenero{

	private daoGenero dataAccess = null;

	public void setDataAccess(daoGenero dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public Genero obtenerUnGenero(Integer idGenero) {
		
		return dataAccess.obtenerGeneroPorID(idGenero);
	}

	@Override
	public ArrayList<Genero> obtenerGeneros() {
		
		return dataAccess.obtenerGeneros();
	}

}
