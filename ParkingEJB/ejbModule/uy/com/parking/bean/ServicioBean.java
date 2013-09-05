package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import uy.com.parking.entities.Servicio;

@Stateless
public class ServicioBean implements IServicioBean {

	@Override
	public Collection<Servicio> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Servicio servicio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Servicio servicio) {
		// TODO Auto-generated method stub

	}
}