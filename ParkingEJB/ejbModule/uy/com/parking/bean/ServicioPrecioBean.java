package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioPrecio;

@Stateless
public class ServicioPrecioBean implements IServicioPrecioBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<ServicioPrecio> list() {
		Collection<ServicioPrecio> result = new LinkedList<ServicioPrecio>();
		
		try {
			Query query = entityManager.createQuery("SELECT sp FROM ServicioPrecio sp");
			
			for (Object object : query.getResultList()) {
				result.add((ServicioPrecio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(ServicioPrecio servicioPrecio) {
		try {
			servicioPrecio.setServicio(entityManager.find(Servicio.class, servicioPrecio.getId()));
			servicioPrecio.setMoneda(entityManager.find(Moneda.class, servicioPrecio.getMoneda().getId()));
			
			entityManager.persist(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(ServicioPrecio servicioPrecio) {
		try {
			ServicioPrecio managedServicioPrecio = entityManager.find(ServicioPrecio.class, servicioPrecio.getId());
			
			entityManager.remove(managedServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ServicioPrecio servicioPrecio) {
		try {
			servicioPrecio.setServicio(entityManager.find(Servicio.class, servicioPrecio.getServicio().getId()));
			servicioPrecio.setMoneda(entityManager.find(Moneda.class, servicioPrecio.getMoneda().getId()));
			
			entityManager.merge(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}