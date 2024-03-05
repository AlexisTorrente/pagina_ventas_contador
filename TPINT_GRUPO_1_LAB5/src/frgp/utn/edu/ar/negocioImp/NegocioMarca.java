package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoMarca;
import frgp.utn.edu.ar.entidad.Marca;
import frgp.utn.edu.ar.negocio.InegocioMarca;

public class NegocioMarca implements InegocioMarca{

	private daoMarca dataAccess = null;

	public void setDataAccess(daoMarca dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public Marca obtenerUnaMarca(Integer idMarca) {
		
		return dataAccess.obtenerMarcaPorID(idMarca);
	}

	@Override
	public ArrayList<Marca> obtenerMarcas() {
		
		return dataAccess.obtenerMarcas();
	}

	
}
