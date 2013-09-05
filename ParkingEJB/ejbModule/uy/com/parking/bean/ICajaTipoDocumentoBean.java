package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.CajaTipoDocumento;

@Remote
public interface ICajaTipoDocumentoBean {

	public Collection<CajaTipoDocumento> list();
	
	public void save(CajaTipoDocumento cajaTipoDocumento);
	
	public void remove(CajaTipoDocumento cajaTipoDocumento);
	
	public void update(CajaTipoDocumento cajaTipoDocumento);
}