package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoDetalleVenta;
import frgp.utn.edu.ar.entidad.DetalleVenta;

public class daoDetalleVentaImp implements daoDetalleVenta{
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarDetalleVenta(DetalleVenta detVenta) {
		
		this.hibernateTemplate.save(detVenta);
	}

	@Override
	public ArrayList<DetalleVenta> obtenerDetalleVenta(int idVenta) {
		
		return (ArrayList<DetalleVenta>) this.hibernateTemplate.find("FROM DetalleVenta dv WHERE dv.venta.idVenta = " + idVenta);
	}
}
