package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.usuario;

public interface InegocioUsuario {

	// Alta de persona
	void insertarUsuario(usuario usuario);

	// Obtiene una persona por dni
	usuario obtenerUsuarioPorNombre(String nombreUser);

	// Obtiene todas las persona
	ArrayList<usuario> obtenerUsuarios();

	// Elimina una persona a partir del dni
	void eliminarUsuario(String nombreUser);

	// Actualiza los datos de una persona
	void actualizarUsuario(usuario usuario);
}
