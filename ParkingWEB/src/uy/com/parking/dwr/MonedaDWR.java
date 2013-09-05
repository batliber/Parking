package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IMonedaBean;
import uy.com.parking.bean.MonedaBean;
import uy.com.parking.entities.Moneda;
import uy.com.parking.transferObjects.MonedaTO;

@RemoteProxy
public class MonedaDWR {

	private IMonedaBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = MonedaBean.class.getSimpleName();
		String remoteInterfaceName = IMonedaBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IMonedaBean) context.lookup(lookupName);
	}
	
	public Collection<MonedaTO> list() {
		Collection<MonedaTO> result = new LinkedList<MonedaTO>();
		
		try {
			IMonedaBean iMonedaBean = lookupBean();
			
			for (Moneda moneda : iMonedaBean.list()) {
				MonedaTO monedaTO = new MonedaTO();
				
				monedaTO.setFact(moneda.getFact());
				monedaTO.setId(moneda.getId());
				monedaTO.setTerm(moneda.getTerm());
				monedaTO.setUact(moneda.getUact());
				
				result.add(monedaTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(MonedaTO monedaTO) {
		try {
			IMonedaBean iMonedaBean = lookupBean();
			
			Moneda moneda = new Moneda();
			
			moneda.setFact(monedaTO.getFact());
			moneda.setTerm(monedaTO.getTerm());
			moneda.setUact(monedaTO.getUact());
			
			iMonedaBean.save(moneda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(MonedaTO monedaTO) {
		try {
			IMonedaBean iMonedaBean = lookupBean();
			
			Moneda moneda = new Moneda();
			moneda.setId(monedaTO.getId());
			
			iMonedaBean.remove(moneda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(MonedaTO monedaTO) {
		try {
			IMonedaBean iMonedaBean = lookupBean();
			
			Moneda moneda = new Moneda();
			
			moneda.setId(monedaTO.getId());
			moneda.setFact(monedaTO.getFact());
			moneda.setTerm(monedaTO.getTerm());
			moneda.setUact(monedaTO.getUact());
			
			iMonedaBean.update(moneda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}