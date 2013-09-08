package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IRegistroTipoBean;
import uy.com.parking.bean.RegistroTipoBean;
import uy.com.parking.entities.RegistroTipo;
import uy.com.parking.transferObjects.RegistroTipoTO;

@RemoteProxy
public class RegistroTipoDWR {

	private IRegistroTipoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = RegistroTipoBean.class.getSimpleName();
		String remoteInterfaceName = IRegistroTipoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IRegistroTipoBean) context.lookup(lookupName);
	}
	
	public Collection<RegistroTipoTO> list() {
		Collection<RegistroTipoTO> result = new LinkedList<RegistroTipoTO>();
		
		try {
			IRegistroTipoBean iRegistroTipoBean = lookupBean();
			
			for (RegistroTipo registroTipo : iRegistroTipoBean.list()) {
				RegistroTipoTO registroTipoTO = new RegistroTipoTO();
				
				registroTipoTO.setDescripcion(registroTipo.getDescripcion());

				registroTipoTO.setFact(registroTipo.getFact());
				registroTipoTO.setId(registroTipo.getId());
				registroTipoTO.setTerm(registroTipo.getTerm());
				registroTipoTO.setUact(registroTipo.getUact());
				
				result.add(registroTipoTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(RegistroTipoTO registroTipoTO) {
		this.update(registroTipoTO);
	}

	public void remove(RegistroTipoTO registroTipoTO) {
		try {
			IRegistroTipoBean iRegistroTipoBean = lookupBean();
			
			RegistroTipo registroTipo = new RegistroTipo();
			registroTipo.setId(registroTipoTO.getId());
			
			iRegistroTipoBean.remove(registroTipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(RegistroTipoTO registroTipoTO) {
		try {
			IRegistroTipoBean iRegistroTipoBean = lookupBean();
			
			RegistroTipo registroTipo = new RegistroTipo();
			
			registroTipo.setDescripcion(registroTipoTO.getDescripcion());			
			
			registroTipo.setId(registroTipoTO.getId());
			registroTipo.setFact(registroTipoTO.getFact());
			registroTipo.setTerm(registroTipoTO.getTerm());
			registroTipo.setUact(registroTipoTO.getUact());
			
			iRegistroTipoBean.update(registroTipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}