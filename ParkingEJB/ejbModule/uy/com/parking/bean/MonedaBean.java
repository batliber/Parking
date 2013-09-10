package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Moneda;

@Stateless
public class MonedaBean implements IMonedaBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Moneda> list() {
		Collection<Moneda> result = new LinkedList<Moneda>();
		
		try {
			Query query = entityManager.createQuery("SELECT m FROM Moneda m");
			
			for (Object object : query.getResultList()) {
				result.add((Moneda) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Moneda getById(Long id) {
		Moneda result = null;
		
		try {
			result = entityManager.find(Moneda.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(Moneda moneda) {
		try {
			entityManager.persist(moneda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Moneda moneda) {
		try {
			Moneda managedMoneda = entityManager.find(Moneda.class, moneda.getId());
			
			entityManager.remove(managedMoneda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Moneda moneda) {
		try {
			entityManager.merge(moneda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}