package uy.com.parking.bean;

import javax.ejb.Remote;

import uy.com.parking.entities.CobranzaTipoDocumento;

@Remote
public interface ICobranzaTipoDocumentoBean {

	public CobranzaTipoDocumento getById(Long id);
}