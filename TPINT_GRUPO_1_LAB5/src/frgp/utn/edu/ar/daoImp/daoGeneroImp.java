package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoGenero;
import frgp.utn.edu.ar.entidad.Genero;

public class daoGeneroImp implements daoGenero{
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Genero obtenerGeneroPorID(int idGenero) {
		
		return this.hibernateTemplate.get(Genero.class, idGenero);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Genero> obtenerGeneros() {
		
		return (ArrayList<Genero>) this.hibernateTemplate.find("FROM Genero");
	}
}
