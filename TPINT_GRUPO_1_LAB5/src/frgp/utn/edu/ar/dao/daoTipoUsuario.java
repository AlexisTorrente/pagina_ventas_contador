package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.TipoUsuario;

public interface daoTipoUsuario {

	public TipoUsuario obtenerTipoUsuarioPorID(int idTipo);

	public ArrayList<TipoUsuario> obtenerTiposUsuario();
}
