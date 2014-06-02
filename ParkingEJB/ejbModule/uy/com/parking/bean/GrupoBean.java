package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Grupo;

@Stateless
public class GrupoBean implements IGrupoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Grupo> list() {
		Collection<Grupo> result = new LinkedList<Grupo>();
		
		try {
			Query query = entityManager.createQuery("SELECT g FROM Grupo g");
			
			for (Object object : query.getResultList()) {
				result.add((Grupo) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}