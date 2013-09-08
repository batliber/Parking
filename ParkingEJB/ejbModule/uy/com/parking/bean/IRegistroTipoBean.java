package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.RegistroTipo;

@Remote
public interface IRegistroTipoBean {

	public Collection<RegistroTipo> list();
	
	public void save(RegistroTipo registroTipo);
	
	public void remove(RegistroTipo registroTipo);
	
	public void update(RegistroTipo registroTipo);
}