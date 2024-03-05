package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoMarca;
import frgp.utn.edu.ar.entidad.Marca;

public class daoMarcaImp implements daoMarca{
	
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Marca obtenerMarcaPorID(int idMarca) {
		
		return this.hibernateTemplate.get(Marca.class, idMarca);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Marca> obtenerMarcas() {
		
		return (ArrayList<Marca>) this.hibernateTemplate.find("FROM Marca m WHERE m.estado = 1");
	}

}
