package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.Factura;

@Stateless
public class FacturaBean implements IFacturaBean {

	@Override
	public Collection<Factura> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Factura factura) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Factura factura) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Factura factura) {
		// TODO Auto-generated method stub

	}
}