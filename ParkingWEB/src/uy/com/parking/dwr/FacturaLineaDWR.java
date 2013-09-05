package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.FacturaLineaBean;
import uy.com.parking.bean.IFacturaLineaBean;
import uy.com.parking.entities.FacturaLinea;
import uy.com.parking.transferObjects.FacturaLineaTO;

@RemoteProxy
public class FacturaLineaDWR {

	private IFacturaLineaBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = FacturaLineaBean.class.getSimpleName();
		String remoteInterfaceName = IFacturaLineaBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IFacturaLineaBean) context.lookup(lookupName);
	}
	
	public Collection<FacturaLineaTO> list() {
		Collection<FacturaLineaTO> result = new LinkedList<FacturaLineaTO>();
		
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			for (FacturaLinea facturaLinea : iFacturaLineaBean.list()) {
				FacturaLineaTO facturaLineaTO = new FacturaLineaTO();
				
				facturaLineaTO.setFact(facturaLinea.getFact());
				facturaLineaTO.setId(facturaLinea.getId());
				facturaLineaTO.setTerm(facturaLinea.getTerm());
				facturaLineaTO.setUact(facturaLinea.getUact());
				
				result.add(facturaLineaTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(FacturaLineaTO facturaLineaTO) {
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			FacturaLinea facturaLinea = new FacturaLinea();
			
			facturaLinea.setFact(facturaLineaTO.getFact());
			facturaLinea.setTerm(facturaLineaTO.getTerm());
			facturaLinea.setUact(facturaLineaTO.getUact());
			
			iFacturaLineaBean.save(facturaLinea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(FacturaLineaTO facturaLineaTO) {
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			FacturaLinea facturaLinea = new FacturaLinea();
			facturaLinea.setId(facturaLineaTO.getId());
			
			iFacturaLineaBean.remove(facturaLinea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(FacturaLineaTO facturaLineaTO) {
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			FacturaLinea facturaLinea = new FacturaLinea();
			
			facturaLinea.setId(facturaLineaTO.getId());
			facturaLinea.setFact(facturaLineaTO.getFact());
			facturaLinea.setTerm(facturaLineaTO.getTerm());
			facturaLinea.setUact(facturaLineaTO.getUact());
			
			iFacturaLineaBean.update(facturaLinea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}