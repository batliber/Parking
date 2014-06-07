package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.ServicioTipo;

@Stateless
public class ServicioTipoBean implements IServicioTipoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<ServicioTipo> list() {
		Collection<ServicioTipo> result = new LinkedList<ServicioTipo>();
		
		try {
			Query query = entityManager.createQuery("SELECT st FROM ServicioTipo st");
			
			for (Object object : query.getResultList()) {
				result.add((ServicioTipo) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ServicioTipo getById(Long id) {
		ServicioTipo result = null;
		
		try {
			result = entityManager.find(ServicioTipo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}