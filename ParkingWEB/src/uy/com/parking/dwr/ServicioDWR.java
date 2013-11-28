package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IServicioBean;
import uy.com.parking.bean.ServicioBean;
import uy.com.parking.entities.Servicio;
import uy.com.parking.transferObjects.ServicioTO;

@RemoteProxy
public class ServicioDWR {

	private IServicioBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = ServicioBean.class.getSimpleName();
		String remoteInterfaceName = IServicioBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IServicioBean) context.lookup(lookupName);
	}
	
	public Collection<ServicioTO> list() {
		Collection<ServicioTO> result = new LinkedList<ServicioTO>();
		
		try {
			IServicioBean iServicioBean = lookupBean();
			
			for (Servicio servicio : iServicioBean.list()) {
				result.add(transform(servicio, false));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(ServicioTO servicioTO) {
		this.update(servicioTO);
	}

	public void remove(ServicioTO servicioTO) {
		try {
			IServicioBean iServicioBean = lookupBean();
			
			Servicio servicio = new Servicio();
			servicio.setId(servicioTO.getId());
			
			iServicioBean.remove(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(ServicioTO servicioTO) {
		try {
			IServicioBean iServicioBean = lookupBean();
			
			Servicio servicio = new Servicio();
			
			servicio.setDescripcion(servicioTO.getDescripcion());
			
			servicio.setId(servicioTO.getId());
			servicio.setFact(servicioTO.getFact());
			servicio.setTerm(servicioTO.getTerm());
			servicio.setUact(servicioTO.getUact());
			
			iServicioBean.update(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Servicio transform(ServicioTO servicioTO, boolean transformCollections) {
		Servicio servicio = new Servicio();
		
		servicio.setDescripcion(servicioTO.getDescripcion());
		
		servicio.setServicioTipo(ServicioTipoDWR.transform(servicioTO.getServicioTipo()));
		
		servicio.setFact(servicioTO.getFact());
		servicio.setId(servicioTO.getId());
		servicio.setTerm(servicioTO.getTerm());
		servicio.setUact(servicioTO.getUact());
		
		return servicio;
	}

	public static ServicioTO transform(Servicio servicio, boolean transformCollections) {
		ServicioTO servicioTO = new ServicioTO();
		
		servicioTO.setDescripcion(servicio.getDescripcion());
		
		servicioTO.setServicioTipo(ServicioTipoDWR.transform(servicio.getServicioTipo()));
		
		servicioTO.setFact(servicio.getFact());
		servicioTO.setId(servicio.getId());
		servicioTO.setTerm(servicio.getTerm());
		servicioTO.setUact(servicio.getUact());
		
		return servicioTO;
	}
}