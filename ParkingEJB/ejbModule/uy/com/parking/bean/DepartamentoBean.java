package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Departamento;

@Stateless
public class DepartamentoBean implements IDepartamentoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Departamento> list() {
		Collection<Departamento> result = new LinkedList<Departamento>();
		
		try {
			Query query = entityManager.createQuery("SELECT d FROM Departamento d");
			
			for (Object object : query.getResultList()) {
				result.add((Departamento) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Departamento getById(Long id) {
		Departamento result = null;
		
		try {
			result = entityManager.find(Departamento.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(Departamento departamento) {
		try {
			entityManager.persist(departamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Departamento departamento) {
		try {
			Departamento managedDepartamento = entityManager.find(Departamento.class, departamento.getId());
			
			entityManager.remove(managedDepartamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Departamento departamento) {
		try {
			entityManager.merge(departamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}