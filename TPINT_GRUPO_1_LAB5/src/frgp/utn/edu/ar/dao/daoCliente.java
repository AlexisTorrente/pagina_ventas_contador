package frgp.utn.edu.ar.dao;

import java.util.ArrayList;

import frgp.utn.edu.ar.entidad.Cliente;

public interface daoCliente {
	
		public void insertarCliente(Cliente cliente);
		
		public Cliente obtenerClientePorDNI(String dni);

		public ArrayList<Cliente> obtenerClientes();
		
		public ArrayList<Cliente> obtenerClientesConFiltro(String filtro);

		public void eliminarCliente(String dni);

		public void actualizarCliente(Cliente cliente);
}
