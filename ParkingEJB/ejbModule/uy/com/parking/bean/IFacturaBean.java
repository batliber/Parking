package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Factura;

@Remote
public interface IFacturaBean {

	public Collection<Factura> list();
	
	public void save(Factura factura);
	
	public void remove(Factura factura);
	
	public void update(Factura factura);
}