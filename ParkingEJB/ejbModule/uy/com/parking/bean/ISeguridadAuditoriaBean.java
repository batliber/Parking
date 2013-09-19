package uy.com.parking.bean;

import javax.ejb.Remote;

import uy.com.parking.entities.SeguridadAuditoria;

@Remote
public interface ISeguridadAuditoriaBean {

	public void save(SeguridadAuditoria seguridadAuditoria);
}