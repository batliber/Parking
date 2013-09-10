package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Moneda;

@Remote
public interface IMonedaBean {

	public Collection<Moneda> list();
	
	public Moneda getById(Long id);
	
	public void save(Moneda moneda);
	
	public void remove(Moneda moneda);
	
	public void update(Moneda moneda);
}