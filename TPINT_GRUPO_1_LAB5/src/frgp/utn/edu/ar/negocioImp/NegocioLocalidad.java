package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoLocalidad;
import frgp.utn.edu.ar.entidad.Localidad;
import frgp.utn.edu.ar.negocio.InegocioLocalidad;

public class NegocioLocalidad implements InegocioLocalidad{

	private daoLocalidad dataAccess = null;

	public void setDataAccess(daoLocalidad dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public Localidad obtenerUnaLocalidad(Integer idLocalidad) {
		
		return dataAccess.obtenerLocalidadPorID(idLocalidad);
	}

	@Override
	public ArrayList<Localidad> obtenerLocalidades() {
		
		return dataAccess.obtenerLocalidades();
	}
}
