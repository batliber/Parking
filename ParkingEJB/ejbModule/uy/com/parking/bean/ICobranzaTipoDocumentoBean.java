package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.CobranzaTipoDocumento;

@Remote
public interface ICobranzaTipoDocumentoBean {

	public Collection<CobranzaTipoDocumento> list();
	
	public CobranzaTipoDocumento getById(Long id);
}