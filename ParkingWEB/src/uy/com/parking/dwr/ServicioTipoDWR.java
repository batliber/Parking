package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IServicioTipoBean;
import uy.com.parking.bean.ServicioTipoBean;
import uy.com.parking.entities.ServicioTipo;
import uy.com.parking.transferObjects.ServicioTipoTO;

@RemoteProxy
public class ServicioTipoDWR {

	private IServicioTipoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = ServicioTipoBean.class.getSimpleName();
		String remoteInterfaceName = IServicioTipoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IServicioTipoBean) context.lookup(lookupName);
	}
	
	public Collection<ServicioTipoTO> list() {
		Collection<ServicioTipoTO> result = new LinkedList<ServicioTipoTO>();
		
		try {
			IServicioTipoBean iServicioTipoBean = lookupBean();
			
			for (ServicioTipo servicioTipo : iServicioTipoBean.list()) {
				result.add(transform(servicioTipo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ServicioTipo transform(ServicioTipoTO servicioTipoTO) {
		ServicioTipo servicioTipo = new ServicioTipo();
		
		servicioTipo.setDescripcion(servicioTipoTO.getDescripcion());
		
		servicioTipo.setFact(servicioTipoTO.getFact());
		servicioTipo.setId(servicioTipoTO.getId());
		servicioTipo.setTerm(servicioTipoTO.getTerm());
		servicioTipo.setUact(servicioTipoTO.getUact());
		
		return servicioTipo;
	}
	
	public static ServicioTipoTO transform(ServicioTipo servicioTipo) {
		ServicioTipoTO servicioTipoTO = new ServicioTipoTO();
		
		servicioTipoTO.setDescripcion(servicioTipo.getDescripcion());
		
		servicioTipoTO.setFact(servicioTipo.getFact());
		servicioTipoTO.setId(servicioTipo.getId());
		servicioTipoTO.setTerm(servicioTipo.getTerm());
		servicioTipoTO.setUact(servicioTipo.getUact());
		
		return servicioTipoTO;
	}
}