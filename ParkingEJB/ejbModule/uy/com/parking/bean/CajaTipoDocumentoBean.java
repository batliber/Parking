package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.CajaTipoDocumento;

@Stateless
public class CajaTipoDocumentoBean implements ICajaTipoDocumentoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<CajaTipoDocumento> list() {
		Collection<CajaTipoDocumento> result = new LinkedList<CajaTipoDocumento>();
		
		try {
			Query query = entityManager.createQuery("SELECT c FROM CajaTipoDocumento c");
			
			for (Object object : query.getResultList()) {
				result.add((CajaTipoDocumento) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(CajaTipoDocumento cajaTipoDocumento) {
		try {
			entityManager.persist(cajaTipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(CajaTipoDocumento cajaTipoDocumento) {
		try {
			CajaTipoDocumento managedCajaTipoDocumento = entityManager.find(CajaTipoDocumento.class, cajaTipoDocumento.getId());
			
			entityManager.remove(managedCajaTipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(CajaTipoDocumento cajaTipoDocumento) {
		try {
			entityManager.merge(cajaTipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}