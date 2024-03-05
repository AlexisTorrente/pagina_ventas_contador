package frgp.utn.edu.ar.negocioImp;


import java.util.ArrayList;

import frgp.utn.edu.ar.dao.daoArticulo;
import frgp.utn.edu.ar.entidad.Articulo;
import frgp.utn.edu.ar.negocio.InegocioArticulo;

public class NegocioArticulo implements InegocioArticulo{

	private daoArticulo dataAccess = null;

	public void setDataAccess(daoArticulo dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public ArrayList<Articulo> obtenerArticulos() {
		return dataAccess.obtenerArticulos();
	}

	@Override
	public ArrayList<Articulo> obtenerArticulosConFiltro(int filtroMarca, int filtroTipo) {
		
		String filtro = "";
		
		if(filtroMarca != 0 || filtroTipo != 0) {
			
			if(filtroMarca != 0) {
				filtro += " AND a.marca.id = " + filtroMarca;
			}
			if(filtroTipo != 0) {
				filtro += " AND a.tipo.id = " + filtroTipo;
			}
			
			return dataAccess.obtenerArticulosConFiltro(filtro);
		}
		else{
			return dataAccess.obtenerArticulos();			
		}
	}
	
	@Override
	public Articulo obtenerUnArticulo(Integer articulo) {
		return dataAccess.obtenerArticuloPorID(articulo);
	}

	@Override
	public void insertarArticulo(Articulo articulo) {
		 dataAccess.insertarArticulo(articulo);
		
	}

	@Override
	public void eliminarArticulo(Integer idArticulo) {
		dataAccess.eliminarArticulo(idArticulo);
		
	}

	@Override
	public void actualizarArticulo(Articulo articulo) {
		dataAccess.actualizarArticulo(articulo);
		
	}


}
