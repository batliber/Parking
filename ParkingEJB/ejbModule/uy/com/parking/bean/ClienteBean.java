package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.Cliente;

@Stateless
public class ClienteBean implements IClienteBean {

	@Override
	public Collection<Cliente> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Cliente cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Cliente cliente) {
		// TODO Auto-generated method stub

	}
}