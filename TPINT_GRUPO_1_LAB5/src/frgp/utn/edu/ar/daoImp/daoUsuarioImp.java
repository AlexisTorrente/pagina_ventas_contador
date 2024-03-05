package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoUsuario;
import frgp.utn.edu.ar.entidad.usuario;

public class daoUsuarioImp implements daoUsuario {
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarUsuario(usuario usuario) {
		this.hibernateTemplate.save(usuario);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public usuario obtenerUsuarioPorNombre(String nombreUser) {
		return this.hibernateTemplate.get(usuario.class, nombreUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<usuario> obtenerUsuarios() {
		return (ArrayList<usuario>) this.hibernateTemplate.loadAll(usuario.class);
	}


    	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarUsuario(String nombreUser) {
		usuario user = new usuario();
		user.setNombre(nombreUser);
		this.hibernateTemplate.delete(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void actualizarUsuario(usuario persona) {
		this.hibernateTemplate.update(persona);
	}
}
