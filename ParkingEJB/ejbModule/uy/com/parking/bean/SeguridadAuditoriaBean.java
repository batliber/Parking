package uy.com.parking.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.com.parking.entities.SeguridadAuditoria;

@Stateless
public class SeguridadAuditoriaBean implements ISeguridadAuditoriaBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public void save(SeguridadAuditoria seguridadAuditoria) {
		try {
			entityManager.persist(seguridadAuditoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}