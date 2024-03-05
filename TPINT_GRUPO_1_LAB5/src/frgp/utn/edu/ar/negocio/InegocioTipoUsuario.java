package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.TipoUsuario;

public interface InegocioTipoUsuario {

	TipoUsuario obtenerUnTipoUsuario(Integer idTipo);
	
	ArrayList<TipoUsuario> obtenerTiposUsuario();
}
