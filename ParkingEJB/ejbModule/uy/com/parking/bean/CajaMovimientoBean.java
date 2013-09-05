package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.CajaMovimiento;

@Stateless
public class CajaMovimientoBean implements ICajaMovimientoBean {

	@Override
	public Collection<CajaMovimiento> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CajaMovimiento cajaMovimiento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(CajaMovimiento cajaMovimiento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CajaMovimiento cajaMovimiento) {
		// TODO Auto-generated method stub

	}
}