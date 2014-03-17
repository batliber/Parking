package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	public Departamento getByMatricula(String matricula) {
		Departamento result = null;
		
		try {
			TypedQuery<Departamento> query = 
				entityManager.createQuery(
					"SELECT d FROM Departamento d WHERE d.prefijo = :prefijo", 
					Departamento.class
				);
			query.setParameter("prefijo", matricula.toUpperCase().substring(0, 1));
			
			List<Departamento> resultList = query.getResultList();
			
			if (resultList.size() > 0) {
				result = resultList.get(0);
			}
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