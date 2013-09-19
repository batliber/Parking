package uy.com.parking.bean;

import javax.ejb.Remote;

import uy.com.parking.entities.SeguridadAuditoria;

@Remote
public interface ISeguridadBean {

	public SeguridadAuditoria login(String login, String contrsena);
	
	public void logout(Long usuarioId);
}