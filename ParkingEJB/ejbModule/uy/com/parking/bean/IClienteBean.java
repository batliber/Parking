package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.VehiculoServicioPrecio;

@Remote
public interface IClienteBean {

	public Collection<Cliente> list();
	
	public Cliente getById(Long id);
	
	public Cliente getByDocumento(String documento);
	
	public void save(Cliente cliente);
	
	public void remove(Cliente cliente);
	
	public void update(Cliente cliente);
	
	public void updateConVehiculoServicioPrecios(
		Cliente cliente, Collection<VehiculoServicioPrecio> vehiculoServicioPrecios
	);
}