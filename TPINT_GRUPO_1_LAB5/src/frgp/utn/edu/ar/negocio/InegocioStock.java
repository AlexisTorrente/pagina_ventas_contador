package frgp.utn.edu.ar.negocio;

import frgp.utn.edu.ar.entidad.Articulo;
import frgp.utn.edu.ar.entidad.Stock;

public interface InegocioStock {
	
	void insertarStock(Stock stock);

	Stock obtenerUnArticuloUltimoStock(Integer articulo);
	
	Stock obtenerUnArticuloStock(Integer articulo);
	
	double disminuirCantidadStock(int idArticulo, int cantidadParaRestar);
	
	boolean hayStock(int idArticulo, int cantidadParaVenta);


}
