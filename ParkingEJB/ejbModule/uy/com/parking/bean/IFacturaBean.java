package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Factura;

@Remote
public interface IFacturaBean {

	public Collection<Factura> list();
	
	public Factura getById(Long id);
	
	public Factura getByNumero(Long numero);
	
	public void save(Factura factura);
	
	public Factura saveAndCloseRegistro(Factura factura, String matricula);
	
	public Factura generateFacturaByMatricula(String matricula);
	
	public void remove(Factura factura);
	
	public void update(Factura factura);
}