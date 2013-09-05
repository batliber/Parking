package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.FacturaLinea;

@Remote
public interface IFacturaLineaBean {

	public Collection<FacturaLinea> list();
	
	public void save(FacturaLinea facturaLinea);
	
	public void remove(FacturaLinea facturaLinea);
	
	public void update(FacturaLinea facturaLinea);
}