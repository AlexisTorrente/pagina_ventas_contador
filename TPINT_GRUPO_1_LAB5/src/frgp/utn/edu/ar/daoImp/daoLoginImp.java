package frgp.utn.edu.ar.daoImp;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoLogin;
import frgp.utn.edu.ar.entidad.TipoUsuario;
import frgp.utn.edu.ar.entidad.usuario;

public class daoLoginImp implements daoLogin{

	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public usuario inicioSesionUsuario(usuario usuario) {
		
		usuario usuarioAencontrar = new usuario();
		List<usuario> usuariosEncontrados = (List<usuario>) this.hibernateTemplate.find("from usuario where nombre = ?", usuario.getNombre());
		//System.out.println(usuariosEncontrados.size());
		
		if (usuariosEncontrados.size() == 0) {
			TipoUsuario aux = new TipoUsuario();
			aux.setId(-1);
		    usuarioAencontrar.setTipoUsuario(aux);
		    
		}
		else {
			usuarioAencontrar = this.hibernateTemplate.get(usuario.class,usuario.getNombre());
		}
		
		return usuarioAencontrar;

		
		}
		
	}
