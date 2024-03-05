package frgp.utn.edu.ar.dao;

import frgp.utn.edu.ar.entidad.usuario;
import java.util.ArrayList;

public interface daoUsuario {

    // Alta de persona
    public void insertarUsuario(usuario usuario);

    // Obtiene una persona por dni
    public usuario obtenerUsuarioPorNombre(String nombreUser);

    // Obtiene todas las persona
    public ArrayList<usuario> obtenerUsuarios();

    // Elimina una persona a partir del dni
    public void eliminarUsuario(String nombreUser);

    // Actualiza los datos de una persona
    public void actualizarUsuario(usuario usuario);

}
