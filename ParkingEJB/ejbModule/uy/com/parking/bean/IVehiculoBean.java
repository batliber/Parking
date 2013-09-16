package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Vehiculo;

@Remote
public interface IVehiculoBean {

	public Collection<Vehiculo> list();
	
	public Vehiculo getById(Long id);
	
	public Vehiculo getByMatricula(String matricula);
	
	public void save(Vehiculo vehiculo);
	
	public void remove(Vehiculo vehiculo);
	
	public void update(Vehiculo vehiculo);
}