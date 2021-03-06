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
	
	public Collection<ServicioPrecio> listVigentes() {
		Collection<ServicioPrecio> result = new LinkedList<ServicioPrecio>();
		
		try {
			Query query = entityManager.createQuery(
				"SELECT sp"
				+ " FROM ServicioPrecio sp"
				+ " WHERE sp.validoHasta = null"
				+ " ORDER BY sp.servicio.servicioTipo.id, sp.servicio.descripcion"
			);
			
			for (Object object : query.getResultList()) {
				result.add((ServicioPrecio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ServicioPrecio getById(Long id) {
		ServicioPrecio result = null;
		
		try {
			result = entityManager.find(ServicioPrecio.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ServicioPrecio getPrecioVigenteByServicioMoneda(Servicio servicio, Moneda moneda) {
		ServicioPrecio result = null;
		
		try {
			Query query = 
				entityManager.createQuery(
					"SELECT sp FROM ServicioPrecio sp"
					+ " WHERE sp.servicio.id = :servicioId"
					+ " AND sp.moneda.id = :monedaId"
					+ " AND sp.validoHasta = null");
			query.setParameter("servicioId", servicio.getId());
			query.setParameter("monedaId", moneda.getId());
			
			result = (ServicioPrecio) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(ServicioPrecio servicioPrecio) {
		try {
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
			entityManager.merge(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}