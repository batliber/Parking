package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.Vehiculo;

@Stateless
public class ClienteBean implements IClienteBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Cliente> list() {
		Collection<Cliente> result = new LinkedList<Cliente>();
		
		try {
			Query query = entityManager.createQuery("SELECT c FROM Cliente c");
			
			for (Object object : query.getResultList()) {
				result.add((Cliente) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Cliente getById(Long id) {
		Cliente result = null;
		
		try {
			result = entityManager.find(Cliente.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(Cliente cliente) {
		try {
			Collection<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
			for (Vehiculo vehiculo : cliente.getVehiculos()) {
				vehiculos.add(entityManager.find(Vehiculo.class, vehiculo.getId()));
			}
			
			cliente.setVehiculos(vehiculos);
			
			entityManager.persist(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Cliente cliente) {
		try {
			Cliente managedCliente = entityManager.find(Cliente.class, cliente.getId());
			
			entityManager.remove(managedCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Cliente cliente) {
		try {
			Collection<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
			for (Vehiculo vehiculo : cliente.getVehiculos()) {
				vehiculos.add(entityManager.find(Vehiculo.class, vehiculo.getId()));
			}
			
			cliente.setVehiculos(vehiculos);
			
			entityManager.merge(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}