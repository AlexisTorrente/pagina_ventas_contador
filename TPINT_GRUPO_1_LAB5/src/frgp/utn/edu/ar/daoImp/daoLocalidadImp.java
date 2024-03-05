package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoLocalidad;
import frgp.utn.edu.ar.entidad.Localidad;

public class daoLocalidadImp implements daoLocalidad{

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Localidad obtenerLocalidadPorID(int idLocalidad) {
		
		return this.hibernateTemplate.get(Localidad.class, idLocalidad);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Localidad> obtenerLocalidades() {
		
		return (ArrayList<Localidad>) this.hibernateTemplate.find("FROM Localidad");
	}
}
