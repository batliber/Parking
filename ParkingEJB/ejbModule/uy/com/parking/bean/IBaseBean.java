package uy.com.parking.bean;

import javax.ejb.Remote;

import uy.com.parking.entities.BaseEntity;

@Remote
public interface IBaseBean {

	public void save(BaseEntity baseEntity);
	
	public void remove(BaseEntity baseEntity);
	
	public void update(BaseEntity baseEntity);
}