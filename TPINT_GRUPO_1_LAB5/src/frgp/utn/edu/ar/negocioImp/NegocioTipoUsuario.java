package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoTipoArticulo;
import frgp.utn.edu.ar.dao.daoTipoUsuario;
import frgp.utn.edu.ar.entidad.TipoUsuario;
import frgp.utn.edu.ar.negocio.InegocioTipoUsuario;

public class NegocioTipoUsuario implements InegocioTipoUsuario{

	private daoTipoUsuario dataAccess = null;

	public void setDataAccess(daoTipoUsuario dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public TipoUsuario obtenerUnTipoUsuario(Integer idTipo) {
		
		return dataAccess.obtenerTipoUsuarioPorID(idTipo);
	}

	@Override
	public ArrayList<TipoUsuario> obtenerTiposUsuario() {
		
		return dataAccess.obtenerTiposUsuario();
	}

}
