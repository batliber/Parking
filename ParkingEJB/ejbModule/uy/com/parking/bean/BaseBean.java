package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uy.com.parking.entities.BaseEntity;

@Stateless
public class BaseBean implements IBaseBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<BaseBean> list() {
		return null;
	}
	
	public void save(BaseEntity baseEntity) {
		
	}

	public void remove(BaseEntity baseEntity) {
		
	}

	public void update(BaseEntity baseEntity) {
		
	}
}