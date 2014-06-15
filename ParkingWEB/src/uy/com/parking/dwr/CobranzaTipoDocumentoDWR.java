package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.CobranzaTipoDocumentoBean;
import uy.com.parking.bean.ICobranzaTipoDocumentoBean;
import uy.com.parking.entities.CobranzaTipoDocumento;
import uy.com.parking.transferObjects.CobranzaTipoDocumentoTO;
import uy.com.parking.util.Configuration;

@RemoteProxy
public class CobranzaTipoDocumentoDWR {

	private ICobranzaTipoDocumentoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = CobranzaTipoDocumentoBean.class.getSimpleName();
		String remoteInterfaceName = ICobranzaTipoDocumentoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (ICobranzaTipoDocumentoBean) context.lookup(lookupName);
	}
	
	public Collection<CobranzaTipoDocumentoTO> list() {
		Collection<CobranzaTipoDocumentoTO> result = new LinkedList<CobranzaTipoDocumentoTO>();
		
		try {
			ICobranzaTipoDocumentoBean iCobranzaTipoDocumentoBean = lookupBean();
			
			for (CobranzaTipoDocumento cobranzaTipoDocumento : iCobranzaTipoDocumentoBean.list()) {
				result.add(transform(cobranzaTipoDocumento, false));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public CobranzaTipoDocumentoTO getTipoDocumentoCobranzaManual() {
		CobranzaTipoDocumentoTO result = null;
		
		try {
			ICobranzaTipoDocumentoBean iCobranzaTipoDocumentoBean = lookupBean();
			
			result = 
				transform(
					iCobranzaTipoDocumentoBean.getById(
						new Long(Configuration.getInstance().getProperty(
							"CobranzaTipoDocumento.cobranzaManual")
						)
					), false
				);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public CobranzaTipoDocumentoTO getTipoDocumentoAjusteCobranza() {
		CobranzaTipoDocumentoTO result = null;
		
		try {
			ICobranzaTipoDocumentoBean iCobranzaTipoDocumentoBean = lookupBean();
			
			result = 
				transform(
					iCobranzaTipoDocumentoBean.getById(
						new Long(Configuration.getInstance().getProperty(
							"CobranzaTipoDocumento.ajusteCobranza")
						)
					), false
				);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
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