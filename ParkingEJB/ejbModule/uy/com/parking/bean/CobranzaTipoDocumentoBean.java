package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import uy.com.parking.entities.CobranzaTipoDocumento;

@Stateless
public class CobranzaTipoDocumentoBean implements ICobranzaTipoDocumentoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<CobranzaTipoDocumento> list() {
		Collection<CobranzaTipoDocumento> result = new LinkedList<CobranzaTipoDocumento>();
		
		try {
			TypedQuery<CobranzaTipoDocumento> query = entityManager.createQuery(
				"SELECT ctd FROM CobranzaTipoDocumento ctd", 
				CobranzaTipoDocumento.class
			);
			
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public CobranzaTipoDocumento getById(Long id) {
		CobranzaTipoDocumento result = null;
		
		try {
			result = entityManager.find(CobranzaTipoDocumento.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}