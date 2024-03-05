package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.TipoArticulo;

public interface InegocioTipoArticulo {

	TipoArticulo obtenerUnTipoArticulo(Integer idTipo);
	
	ArrayList<TipoArticulo> obtenerTiposArt();
}
