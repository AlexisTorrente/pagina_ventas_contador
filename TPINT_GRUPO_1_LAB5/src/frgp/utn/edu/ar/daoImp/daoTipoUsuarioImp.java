package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoTipoUsuario;
import frgp.utn.edu.ar.entidad.TipoArticulo;
import frgp.utn.edu.ar.entidad.TipoUsuario;

public class daoTipoUsuarioImp implements daoTipoUsuario{

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public TipoUsuario obtenerTipoUsuarioPorID(int idTipo) {
		
		return this.hibernateTemplate.get(TipoUsuario.class, idTipo);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<TipoUsuario> obtenerTiposUsuario() {
		
		return (ArrayList<TipoUsuario>) this.hibernateTemplate.find("FROM TipoUsuario");
	}

	
	
}
