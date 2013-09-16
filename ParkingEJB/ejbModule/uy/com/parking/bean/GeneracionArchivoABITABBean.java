package uy.com.parking.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GeneracionArchivoABITABBean implements IGeneracionArchivoABITABBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public void generarArchivoABITAB() {
		System.out.println("generarArchivoABITAB");
	}
}