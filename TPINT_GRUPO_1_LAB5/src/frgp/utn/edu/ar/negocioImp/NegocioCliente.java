package frgp.utn.edu.ar.negocioImp;

import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoCliente;
import frgp.utn.edu.ar.entidad.Cliente;
import frgp.utn.edu.ar.negocio.InegocioCliente;

public class NegocioCliente implements InegocioCliente{

	private daoCliente dataAccess = null;

	public void setDataAccess(daoCliente dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public void insertarCliente(Cliente cliente) {
		
		dataAccess.insertarCliente(cliente);
	}

	@Override
	public Cliente obtenerUnCliente(String dni) {
		
		return dataAccess.obtenerClientePorDNI(dni);
	}

	@Override
	public ArrayList<Cliente> obtenerClientes() {
		
		return dataAccess.obtenerClientes();
	}
	
	@Override
	public ArrayList<Cliente> obtenerClientesConFiltro(int filtroGenero, int filtroLocalidad, String filtroFechaInicio, String filtroFechaFin) {
		
		String filtro = "";
		
		if(filtroGenero != 0 || filtroLocalidad != 0) {
			
			if(filtroGenero != 0) {
				filtro += " AND c.genero.id = " + filtroGenero;
			}
			if(filtroLocalidad != 0) {
				filtro += " AND c.localidad.id = " + filtroLocalidad;
			}
		}
		
		if(filtroFechaInicio != "" || filtroFechaFin != "") {
			
			if(filtroFechaInicio != "") {
				filtro += " AND c.fechaNac >= '" + filtroFechaInicio + "'";
			}
			if(filtroFechaFin != "") {
				filtro += " AND c.fechaNac <= '" + filtroFechaFin + "'";
			}
		}
		
		if(filtro != "") {
			return dataAccess.obtenerClientesConFiltro(filtro);
		}
		else{
			return dataAccess.obtenerClientes();	
		}
	}

	@Override
	public void eliminarCliente(String dni) {
		
		dataAccess.eliminarCliente(dni);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		
		dataAccess.actualizarCliente(cliente);
	}
	

}
