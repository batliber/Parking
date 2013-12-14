package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.ClienteServicioPrecio;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;

@Remote
public interface IClienteServicioPrecioBean {

	public Collection<ClienteServicioPrecio> list();
	
	public Collection<ClienteServicioPrecio> listVigentes();
	
	public ClienteServicioPrecio getById(Long id);
	
	public ClienteServicioPrecio getPrecioVigenteByClienteServicioMoneda(Cliente cliente, Servicio servicio, Moneda moneda);
	
	public Collection<ClienteServicioPrecio> listVigentesByCliente(Cliente cliente);
	
	public void save(ClienteServicioPrecio clienteServicioPrecio);
	
	public void remove(ClienteServicioPrecio clienteServicioPrecio);
	
	public void update(ClienteServicioPrecio clienteServicioPrecio);
}