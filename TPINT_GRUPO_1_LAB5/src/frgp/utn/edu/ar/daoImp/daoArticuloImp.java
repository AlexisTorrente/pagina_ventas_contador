package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import frgp.utn.edu.ar.dao.daoArticulo;
import frgp.utn.edu.ar.entidad.Articulo;

public class daoArticuloImp implements daoArticulo{
    private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public void insertarArticulo(Articulo articulo) {
		
		this.hibernateTemplate.save(articulo);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Articulo obtenerArticuloPorID(int idArticulo) {
		
		return this.hibernateTemplate.get(Articulo.class, idArticulo);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Articulo> obtenerArticulos() {
		
		return (ArrayList<Articulo>) this.hibernateTemplate.find("FROM Articulo a WHERE a.estado = 1");
		
		//return (ArrayList<Articulo>) this.hibernateTemplate.loadAll(Articulo.class);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Articulo> obtenerArticulosConFiltro(String filtro) {
		
		return (ArrayList<Articulo>) this.hibernateTemplate.find("FROM Articulo a WHERE a.estado = 1" + filtro);
	}
	
	@Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarArticulo(int idArticulo) {

		Articulo art = new Articulo();
		art = (Articulo) this.hibernateTemplate.get(Articulo.class, idArticulo);
		art.setEstado(false);
		this.hibernateTemplate.update(art);
		
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public void actualizarArticulo(Articulo articulo) {
		
		this.hibernateTemplate.update(articulo);
	}

}