package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.FacturaLinea;

@Stateless
public class FacturaLineaBean implements IFacturaLineaBean {

	@Override
	public Collection<FacturaLinea> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(FacturaLinea facturaLinea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(FacturaLinea facturaLinea) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(FacturaLinea facturaLinea) {
		// TODO Auto-generated method stub

	}
}