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
public class VehiculoBean implements IVehiculoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Vehiculo> list() {
		Collection<Vehiculo> result = new LinkedList<Vehiculo>();
		
		try {
			Query query = entityManager.createQuery("SELECT v FROM Vehiculo v");
			
			for (Object object : query.getResultList()) {
				result.add((Vehiculo) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Vehiculo getByMatricula(String matricula) {
		Vehiculo result = null;
		
		try {
			Query query = 
				entityManager.createQuery(
					"SELECT v FROM Vehiculo v"
					+ " WHERE v.matricula = :matricula"
				);
			query.setParameter("matricula", matricula);
			
			return (Vehiculo) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(Vehiculo vehiculo) {
		try {
			entityManager.persist(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Vehiculo vehiculo) {
		try {
			Vehiculo managedVehiculo = entityManager.find(Vehiculo.class, vehiculo.getId());
			
			entityManager.remove(managedVehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Vehiculo vehiculo) {
		try {
			Collection<Cliente> clientes = new LinkedList<Cliente>();
			for (Cliente cliente : vehiculo.getClientes()) {
				clientes.add(entityManager.find(Cliente.class, cliente.getId()));
			}
			
			vehiculo.setClientes(clientes);
			
			entityManager.merge(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}