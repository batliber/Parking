package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.CajaTipoDocumentoBean;
import uy.com.parking.bean.ICajaTipoDocumentoBean;
import uy.com.parking.entities.CajaTipoDocumento;
import uy.com.parking.transferObjects.CajaTipoDocumentoTO;

@RemoteProxy
public class CajaTipoDocumentoDWR {

	private ICajaTipoDocumentoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = CajaTipoDocumentoBean.class.getSimpleName();
		String remoteInterfaceName = ICajaTipoDocumentoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (ICajaTipoDocumentoBean) context.lookup(lookupName);
	}
	
	public Collection<CajaTipoDocumentoTO> list() {
		Collection<CajaTipoDocumentoTO> result = new LinkedList<CajaTipoDocumentoTO>();
		
		try {
			ICajaTipoDocumentoBean iCajaTipoDocumentoBean = lookupBean();
			
			for (CajaTipoDocumento cajaTipoDocumento : iCajaTipoDocumentoBean.list()) {
				CajaTipoDocumentoTO cajaTipoDocumentoTO = new CajaTipoDocumentoTO();
				
				cajaTipoDocumentoTO.setDescripcion(cajaTipoDocumento.getDescripcion());
				cajaTipoDocumentoTO.setSigno(cajaTipoDocumento.getSigno());
				
				cajaTipoDocumentoTO.setFact(cajaTipoDocumento.getFact());
				cajaTipoDocumentoTO.setId(cajaTipoDocumento.getId());
				cajaTipoDocumentoTO.setTerm(cajaTipoDocumento.getTerm());
				cajaTipoDocumentoTO.setUact(cajaTipoDocumento.getUact());
				
				result.add(cajaTipoDocumentoTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(CajaTipoDocumentoTO cajaTipoDocumentoTO) {
		this.update(cajaTipoDocumentoTO);
	}

	public void remove(CajaTipoDocumentoTO cajaTipoDocumentoTO) {
		try {
			ICajaTipoDocumentoBean iCajaTipoDocumentoBean = lookupBean();
			
			CajaTipoDocumento cajaTipoDocumento = new CajaTipoDocumento();
			cajaTipoDocumento.setId(cajaTipoDocumentoTO.getId());
			
			iCajaTipoDocumentoBean.remove(cajaTipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(CajaTipoDocumentoTO cajaTipoDocumentoTO) {
		try {
			ICajaTipoDocumentoBean iCajaTipoDocumentoBean = lookupBean();
			
			CajaTipoDocumento cajaTipoDocumento = new CajaTipoDocumento();
			
			cajaTipoDocumento.setDescripcion(cajaTipoDocumentoTO.getDescripcion());
			cajaTipoDocumento.setSigno(cajaTipoDocumentoTO.getSigno());
			
			cajaTipoDocumento.setId(cajaTipoDocumentoTO.getId());
			cajaTipoDocumento.setFact(cajaTipoDocumentoTO.getFact());
			cajaTipoDocumento.setTerm(cajaTipoDocumentoTO.getTerm());
			cajaTipoDocumento.setUact(cajaTipoDocumentoTO.getUact());
			
			iCajaTipoDocumentoBean.update(cajaTipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}