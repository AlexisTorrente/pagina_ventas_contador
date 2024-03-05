package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoStock;
import frgp.utn.edu.ar.entidad.Stock;

public class daoStockImp implements daoStock {
	 private HibernateTemplate hibernateTemplate = null;
	 
	 public void setSessionFactory(SessionFactory sessionFactory) {
			
	        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	    }

	 	@Override
	    @Transactional(propagation=Propagation.REQUIRED)
		public void insertarStock(Stock stock) {
			
			this.hibernateTemplate.save(stock);
		}
	 	
	 	@Override
		@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
		public Stock obtenerArticuloUltimoStockPorID(int idArticulo) {
			 String hql = "FROM Stock s WHERE s.articulo.idArticulo = :idArticulo ORDER BY s.fechaIngreso DESC";
			    Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
			    query.setParameter("idArticulo", idArticulo);
			    query.setMaxResults(1);
			    return (Stock) query.uniqueResult();
		}

		@Override
		@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
		public Stock obtenerArticuloStockPorID(int idArticulo) {
			 String hql = "FROM Stock s WHERE s.articulo.idArticulo = :idArticulo AND s.estado = 1";
			    Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
			    query.setParameter("idArticulo", idArticulo);
			    query.setMaxResults(1);
			    return (Stock) query.uniqueResult();
		}
		
		@Override
		@Transactional(propagation = Propagation.REQUIRED)
		public double disminuirCantidadStock(int idArticulo, int cantidadParaRestar) {
			
			String hql = "FROM Stock s WHERE s.articulo.idArticulo = :idArticulo AND s.estado = 1 ORDER BY s.fechaIngreso ASC";
			
			Query queryArt = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
			queryArt.setParameter("idArticulo", idArticulo);
			List<Stock> listaStock = queryArt.list();
			double precioCompra = 0;
			
			for (Stock stock : listaStock) {
			    // System.out.println("stock: " + stock.toString());
				
				System.out.println("cantidad para restar: " + cantidadParaRestar);
				if(cantidadParaRestar == 0) { return precioCompra;}
				else if(cantidadParaRestar > 0) 
				{
					if(stock.getCantidad() > cantidadParaRestar) 
					{
						precioCompra += stock.getPrecioCompra() * cantidadParaRestar;
						hql = "UPDATE Stock s SET s.cantidad = s.cantidad - :cantidadParaRestar WHERE s.articulo.idArticulo = :idArticulo AND s.idStock = :idStock";
					    Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
					    query.setParameter("cantidadParaRestar", cantidadParaRestar);
					    query.setParameter("idArticulo", idArticulo);
					    query.setParameter("idStock", stock.getIdStock());
					    query.executeUpdate();
					    cantidadParaRestar = 0;
					}
					if(stock.getCantidad() == cantidadParaRestar) 
					{
						precioCompra += stock.getPrecioCompra() * cantidadParaRestar;
						hql = "UPDATE Stock s SET s.cantidad = s.cantidad - :cantidadParaRestar, estado = 0 WHERE s.articulo.idArticulo = :idArticulo AND s.idStock = :idStock";
					    Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
					    query.setParameter("cantidadParaRestar", stock.getCantidad());
					    query.setParameter("idArticulo", idArticulo);
					    query.setParameter("idStock", stock.getIdStock());
					    query.executeUpdate();
					    cantidadParaRestar = 0;
					}
					if(stock.getCantidad() < cantidadParaRestar) 
					{
						precioCompra += (stock.getPrecioCompra() * stock.getCantidad());
						hql = "UPDATE Stock s SET s.cantidad = s.cantidad - :cantidadParaRestar, estado = 0 WHERE s.articulo.idArticulo = :idArticulo AND s.idStock = :idStock";
					    Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
					    query.setParameter("cantidadParaRestar", stock.getCantidad());
					    query.setParameter("idArticulo", idArticulo);
					    query.setParameter("idStock", stock.getIdStock());
					    query.executeUpdate();

					    System.out.println("cant rest despues: " + cantidadParaRestar);
					    System.out.println("precio compra: " + precioCompra);
					    cantidadParaRestar -= stock.getCantidad();
					    System.out.println("cant rest despues: " + cantidadParaRestar);
					}
				}
				
			}
			return precioCompra;
			
			
		    
		}

		@Override
		@Transactional(propagation = Propagation.REQUIRED)
		public boolean hayStock(int idArticulo, int cantidadParaVenta) {
			String hql = "FROM Stock s WHERE s.articulo.idArticulo = :idArticulo AND s.estado = 1 ORDER BY s.fechaIngreso ASC";
			Query queryArt = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
			queryArt.setParameter("idArticulo", idArticulo);
			List<Stock> listaStock = queryArt.list();
			int stockTotal=0;
			for (Stock stock : listaStock) {
				
				stockTotal+= stock.getCantidad();
			}
			
			if(cantidadParaVenta > stockTotal) {
				return false;
			}
			
			return true;
		}
}
