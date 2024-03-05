package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoUsuario;
import frgp.utn.edu.ar.entidad.usuario;
import frgp.utn.edu.ar.negocio.InegocioUsuario;

public class NegocioUsuario implements InegocioUsuario {

	private daoUsuario dataAccess = null;

	public void setDataAccess(daoUsuario dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public void insertarUsuario(usuario usuario) {
		dataAccess.insertarUsuario(usuario);

	}

	@Override
	public usuario obtenerUsuarioPorNombre(String nombreUser) {
		return dataAccess.obtenerUsuarioPorNombre(nombreUser);
	}

	@Override
	public ArrayList<usuario> obtenerUsuarios() {
		return dataAccess.obtenerUsuarios();

	}

	@Override
	public void eliminarUsuario(String nombreUser) {
		dataAccess.eliminarUsuario(nombreUser);

	}

	@Override
	public void actualizarUsuario(usuario usuario) {
		dataAccess.actualizarUsuario(usuario);

	}

}
