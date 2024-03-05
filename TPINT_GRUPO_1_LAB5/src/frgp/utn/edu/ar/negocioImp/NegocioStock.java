package frgp.utn.edu.ar.negocioImp;

import frgp.utn.edu.ar.dao.daoStock;
import frgp.utn.edu.ar.entidad.Stock;
import frgp.utn.edu.ar.negocio.InegocioStock;

public class NegocioStock implements InegocioStock {
	
private daoStock dataAccess = null;
	
	public void setDataAccess(daoStock dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public void insertarStock(Stock stock) {
		dataAccess.insertarStock(stock);
	}
	
	@Override
	public Stock obtenerUnArticuloUltimoStock(Integer articulo) {
		return dataAccess.obtenerArticuloUltimoStockPorID(articulo);
	}
	
	@Override
	public Stock obtenerUnArticuloStock(Integer articulo) {
		return dataAccess.obtenerArticuloStockPorID(articulo);
	}
	
	@Override
	public double disminuirCantidadStock(int idArticulo, int cantidadParaRestar) {
		return dataAccess.disminuirCantidadStock(idArticulo, cantidadParaRestar);
	}

	@Override
	public boolean hayStock(int idArticulo, int cantidadParaVenta) {
		
		return dataAccess.hayStock(idArticulo, cantidadParaVenta);
	}

}
