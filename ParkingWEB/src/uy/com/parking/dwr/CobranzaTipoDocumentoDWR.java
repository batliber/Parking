package uy.com.parking.dwr;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.entities.CobranzaTipoDocumento;
import uy.com.parking.transferObjects.CobranzaTipoDocumentoTO;

@RemoteProxy
public class CobranzaTipoDocumentoDWR {

	public static CobranzaTipoDocumento transform(
		CobranzaTipoDocumentoTO cobranzaTipoDocumentoTO, boolean transformCollections) {
		
		CobranzaTipoDocumento cobranzaTipoDocumento = new CobranzaTipoDocumento();
		
		cobranzaTipoDocumento.setDescripcion(cobranzaTipoDocumentoTO.getDescripcion());
		cobranzaTipoDocumento.setSigno(cobranzaTipoDocumentoTO.getSigno());
		
		cobranzaTipoDocumento.setFact(cobranzaTipoDocumentoTO.getFact());
		cobranzaTipoDocumento.setId(cobranzaTipoDocumentoTO.getId());
		cobranzaTipoDocumento.setTerm(cobranzaTipoDocumentoTO.getTerm());
		cobranzaTipoDocumento.setUact(cobranzaTipoDocumentoTO.getUact());
		
		return cobranzaTipoDocumento;
	}

	public static CobranzaTipoDocumentoTO transform(
			CobranzaTipoDocumento cobranzaTipoDocumento, boolean transformCollections) {
		
		CobranzaTipoDocumentoTO cobranzaTipoDocumentoTO = new CobranzaTipoDocumentoTO();
		
		cobranzaTipoDocumentoTO.setDescripcion(cobranzaTipoDocumento.getDescripcion());
		cobranzaTipoDocumentoTO.setSigno(cobranzaTipoDocumento.getSigno());
		
		cobranzaTipoDocumentoTO.setFact(cobranzaTipoDocumento.getFact());
		cobranzaTipoDocumentoTO.setId(cobranzaTipoDocumento.getId());
		cobranzaTipoDocumentoTO.setTerm(cobranzaTipoDocumento.getTerm());
		cobranzaTipoDocumentoTO.setUact(cobranzaTipoDocumento.getUact());
		
		return cobranzaTipoDocumentoTO;
	}
}