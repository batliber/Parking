package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.RegistroTipo;

@Stateless
public class RegistroTipoBean implements IRegistroTipoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<RegistroTipo> list() {
		Collection<RegistroTipo> result = new LinkedList<RegistroTipo>();
		
		try {
			Query query = entityManager.createQuery("SELECT rt FROM RegistroTipo rt");
			
			for (Object object : query.getResultList()) {
				result.add((RegistroTipo) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(RegistroTipo registroTipo) {
		try {
			entityManager.persist(registroTipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(RegistroTipo registroTipo) {
		try {
			RegistroTipo managedRegistroTipo = entityManager.find(RegistroTipo.class, registroTipo.getId());
			
			entityManager.remove(managedRegistroTipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(RegistroTipo registroTipo) {
		try {
			entityManager.merge(registroTipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}