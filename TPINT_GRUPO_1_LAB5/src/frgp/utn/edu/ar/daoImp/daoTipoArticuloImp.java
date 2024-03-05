package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoTipoArticulo;
import frgp.utn.edu.ar.entidad.TipoArticulo;

public class daoTipoArticuloImp implements daoTipoArticulo{

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public TipoArticulo obtenerTipoArtPorID(int idTipo) {
		
		return this.hibernateTemplate.get(TipoArticulo.class, idTipo);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<TipoArticulo> obtenerTiposArt() {
		return (ArrayList<TipoArticulo>) this.hibernateTemplate.find("FROM TipoArticulo t WHERE t.estado = 1");
	}

}
