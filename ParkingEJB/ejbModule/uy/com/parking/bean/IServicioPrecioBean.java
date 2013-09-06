package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioPrecio;

@Remote
public interface IServicioPrecioBean {

	public Collection<ServicioPrecio> list();
	
	public ServicioPrecio getPrecioVigenteByServicioMoneda(Servicio servicio, Moneda moneda);
	
	public void save(ServicioPrecio servicioPrecio);
	
	public void remove(ServicioPrecio servicioPrecio);
	
	public void update(ServicioPrecio servicioPrecio);
}