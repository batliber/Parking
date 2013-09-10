package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Servicio;

@Remote
public interface IServicioBean {

	public Collection<Servicio> list();
	
	public Servicio getById(Long id);
	
	public void save(Servicio servicio);
	
	public void remove(Servicio servicio);
	
	public void update(Servicio servicio);
}