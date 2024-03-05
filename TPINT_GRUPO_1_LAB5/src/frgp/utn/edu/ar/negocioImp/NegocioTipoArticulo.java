package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoTipoArticulo;
import frgp.utn.edu.ar.entidad.TipoArticulo;
import frgp.utn.edu.ar.negocio.InegocioTipoArticulo;

public class NegocioTipoArticulo implements InegocioTipoArticulo{

	private daoTipoArticulo dataAccess = null;

	public void setDataAccess(daoTipoArticulo dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public TipoArticulo obtenerUnTipoArticulo(Integer idTipo) {
		
		return dataAccess.obtenerTipoArtPorID(idTipo);
	}

	@Override
	public ArrayList<TipoArticulo> obtenerTiposArt() {
		
		return dataAccess.obtenerTiposArt();
	}

}
