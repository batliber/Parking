package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Servicio;

@Stateless
public class ServicioBean implements IServicioBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Servicio> list() {
		Collection<Servicio> result = new LinkedList<Servicio>();
		
		try {
			Query query = entityManager.createQuery("SELECT s FROM Servicio s");
			
			for (Object object : query.getResultList()) {
				result.add((Servicio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Servicio getById(Long id) {
		Servicio result = null;
		
		try {
			result = entityManager.find(Servicio.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(Servicio servicio) {
		try {
			entityManager.persist(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(Servicio servicio) {
		try {
			Servicio managedServicio = entityManager.find(Servicio.class, servicio.getId());
			
			entityManager.remove(managedServicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Servicio servicio) {
		try {
			entityManager.merge(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}