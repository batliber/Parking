package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.entities.VehiculoServicioPrecio;

@Remote
public interface IVehiculoServicioPrecioBean {

	public Collection<VehiculoServicioPrecio> list();
	
	public Collection<VehiculoServicioPrecio> listVigentes();
	
	public VehiculoServicioPrecio getById(Long id);
	
	public VehiculoServicioPrecio getPrecioVigenteByVehiculoServicioMoneda(Vehiculo vehiculo, Servicio servicio, Moneda moneda);
	
	public Collection<VehiculoServicioPrecio> listVigentesByVehiculo(Vehiculo vehiculo);
	
	public void save(VehiculoServicioPrecio vehiculoServicioPrecio);
	
	public void remove(VehiculoServicioPrecio vehiculoServicioPrecio);
	
	public void update(VehiculoServicioPrecio vehiculoServicioPrecio);
}