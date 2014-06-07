package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.ServicioTipo;

@Remote
public interface IServicioTipoBean {

	public Collection<ServicioTipo> list();
	
	public ServicioTipo getById(Long id);
}