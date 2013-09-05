package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.Moneda;

@Stateless
public class MonedaBean implements IMonedaBean {

	@Override
	public Collection<Moneda> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Moneda moneda) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Moneda moneda) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Moneda moneda) {
		// TODO Auto-generated method stub

	}
}