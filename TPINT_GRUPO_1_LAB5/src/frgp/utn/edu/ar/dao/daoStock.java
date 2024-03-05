package frgp.utn.edu.ar.dao;


import frgp.utn.edu.ar.entidad.Stock;

public interface daoStock {
	
	// ALTA DE STOCK
	public void insertarStock(Stock stock);

	public Stock obtenerArticuloUltimoStockPorID(int idArticulo);
	
	// Obtiene el stock de un articulo
	public Stock obtenerArticuloStockPorID(int idArticulo);
	
	// disminuye la cantidad de stock
	public double disminuirCantidadStock(int idArticulo, int cantidadParaRestar);
	
	// Verifica cantidad de stock
	public boolean hayStock(int idArticulo, int cantidadParaVenta);

}
