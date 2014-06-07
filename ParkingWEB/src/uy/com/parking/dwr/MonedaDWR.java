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
				result.add(transform(moneda, false));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(MonedaTO monedaTO) {
		this.update(monedaTO);
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
			
			iMonedaBean.update(transform(monedaTO, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Moneda transform(MonedaTO monedaTO, boolean transformCollections) {
		Moneda moneda = new Moneda();
		
		moneda.setAbreviacion(monedaTO.getAbreviacion());
		moneda.setDescripcion(monedaTO.getDescripcion());
		
		moneda.setFact(monedaTO.getFact());
		moneda.setId(monedaTO.getId());
		moneda.setTerm(monedaTO.getTerm());
		moneda.setUact(monedaTO.getUact());
		
		return moneda;
	}

	public static MonedaTO transform(Moneda moneda, boolean transformCollections) {
		MonedaTO monedaTO = new MonedaTO();
		
		monedaTO.setAbreviacion(moneda.getAbreviacion());
		monedaTO.setDescripcion(moneda.getDescripcion());
		
		monedaTO.setFact(moneda.getFact());
		monedaTO.setId(moneda.getId());
		monedaTO.setTerm(moneda.getTerm());
		monedaTO.setUact(moneda.getUact());
		
		return monedaTO;
	}
}