package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.Vehiculo;

@Stateless
public class VehiculoBean implements IVehiculoBean {

	@Override
	public Collection<Vehiculo> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Vehiculo vehiculo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Vehiculo vehiculo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vehiculo vehiculo) {
		// TODO Auto-generated method stub

	}
}