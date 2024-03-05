package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import frgp.utn.edu.ar.dao.daoVenta;
import frgp.utn.edu.ar.entidad.Articulo;
import frgp.utn.edu.ar.entidad.Venta;

public class daoVentaImp implements daoVenta{
    private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public void insertarVenta(Venta venta) {
		this.hibernateTemplate.save(venta);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Venta obtenerVentaPorID(int idVenta) {
		return this.hibernateTemplate.get(Venta.class, idVenta);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Venta> obtenerVentas() {
		return (ArrayList<Venta>) this.hibernateTemplate.find("FROM Venta v WHERE v.estado = 1");
	}

	@Override
	public ArrayList<Venta> obtenerVentasConFiltro(String filtro) {

		return (ArrayList<Venta>) this.hibernateTemplate.find("FROM Venta v WHERE v.estado = 1" + filtro);
	}
	
	@Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarVenta(int idVenta) {
		Venta venta = new Venta();
		venta = (Venta) this.hibernateTemplate.get(Venta.class, idVenta);
		venta.setEstado(false);
		this.hibernateTemplate.update(venta);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Venta> obtenerVentasFiltradoFechas(String fechaInicio, String fechaFin) {
		return (ArrayList<Venta>) this.hibernateTemplate.find("FROM Venta v WHERE v.estado = 1 AND v.fecha BETWEEN '"+ fechaInicio + "' AND '" + fechaFin + "'");
	}

	

}