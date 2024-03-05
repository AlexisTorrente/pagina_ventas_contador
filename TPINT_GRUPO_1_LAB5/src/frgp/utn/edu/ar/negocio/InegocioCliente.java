package frgp.utn.edu.ar.negocio;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Cliente;

public interface InegocioCliente {

	void insertarCliente(Cliente cliente);
	
	Cliente obtenerUnCliente(String dni);
	
	ArrayList<Cliente> obtenerClientes();
	
	ArrayList<Cliente> obtenerClientesConFiltro(int filtroGenero, int filtroLocalidad, String filtroFechaInicio, String filtroFechaFin);

    void eliminarCliente(String dni);

	void actualizarCliente(Cliente cliente);
}
