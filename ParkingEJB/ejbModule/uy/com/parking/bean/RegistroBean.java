package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Registro;
import uy.com.parking.entities.RegistroTipo;

@Stateless
public class RegistroBean implements IRegistroBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Registro> list() {
		Collection<Registro> result = new LinkedList<Registro>();
		
		try {
			Query query = entityManager.createQuery("SELECT r FROM Registro r");
			
			for (Object object : query.getResultList()) {
				result.add((Registro) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(Registro registro) {
		try {
			registro.setRegistroTipo(entityManager.find(RegistroTipo.class, registro.getRegistroTipo().getId()));
			
			entityManager.persist(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Registro registro) {
		try {
			Registro managedRegistro = entityManager.find(Registro.class, registro.getId());
			
			entityManager.remove(managedRegistro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Registro registro) {
		try {
			registro.setRegistroTipo(entityManager.find(RegistroTipo.class, registro.getRegistroTipo().getId()));
			
			entityManager.merge(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}