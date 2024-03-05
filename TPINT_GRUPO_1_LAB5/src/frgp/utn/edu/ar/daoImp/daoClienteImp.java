package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import frgp.utn.edu.ar.dao.daoCliente;
import frgp.utn.edu.ar.entidad.Cliente;

public class daoClienteImp implements daoCliente{
	private HibernateTemplate hibernateTemplate = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public void insertarCliente(Cliente cliente) {
		
		this.hibernateTemplate.save(cliente);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Cliente obtenerClientePorDNI(String dni) {
		
		return this.hibernateTemplate.get(Cliente.class, dni);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Cliente> obtenerClientes() {
		
		return (ArrayList<Cliente>) this.hibernateTemplate.find("FROM Cliente c WHERE c.estado = 1");
	}
	
	@Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public ArrayList<Cliente> obtenerClientesConFiltro(String filtro) {
		
		return (ArrayList<Cliente>) this.hibernateTemplate.find("FROM Cliente c WHERE c.estado = 1" + filtro);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void eliminarCliente(String dni) {
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) this.hibernateTemplate.get(Cliente.class, dni);
		cliente.setEstado(false);
		this.hibernateTemplate.update(cliente);
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public void actualizarCliente(Cliente cliente) {
		
		this.hibernateTemplate.update(cliente);
	}

}
