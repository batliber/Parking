package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Registro;

@Remote
public interface IRegistroBean {

	public Collection<Registro> list();
	
	public Collection<Registro> listSinSalida();
	
	public Registro getLastByMatricula(String matricula);
	
	public void save(Registro registro);
	
	public void remove(Registro registro);
	
	public void update(Registro registro);
}