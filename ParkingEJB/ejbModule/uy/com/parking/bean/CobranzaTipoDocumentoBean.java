package uy.com.parking.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.com.parking.entities.CobranzaTipoDocumento;

@Stateless
public class CobranzaTipoDocumentoBean implements ICobranzaTipoDocumentoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
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