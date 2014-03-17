package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IServicioPrecioBean;
import uy.com.parking.bean.ServicioPrecioBean;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioPrecio;
import uy.com.parking.transferObjects.MonedaTO;
import uy.com.parking.transferObjects.ServicioPrecioTO;
import uy.com.parking.transferObjects.ServicioTO;
import uy.com.parking.util.Configuration;

@RemoteProxy
public class ServicioPrecioDWR {

	private IServicioPrecioBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = ServicioPrecioBean.class.getSimpleName();
		String remoteInterfaceName = IServicioPrecioBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IServicioPrecioBean) context.lookup(lookupName);
	}
	
	public Collection<ServicioPrecioTO> list() {
		Collection<ServicioPrecioTO> result = new LinkedList<ServicioPrecioTO>();
		
		try {
			IServicioPrecioBean iServicioPrecioBean = lookupBean();
			
			for (ServicioPrecio servicioPrecio : iServicioPrecioBean.list()) {
				result.add(transform(servicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Collection<ServicioPrecioTO> listVigentes() {
		Collection<ServicioPrecioTO> result = new LinkedList<ServicioPrecioTO>();
		
		try {
			IServicioPrecioBean iServicioPrecioBean = lookupBean();
			
			for (ServicioPrecio servicioPrecio : iServicioPrecioBean.listVigentes()) {
				result.add(transform(servicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	public ServicioPrecioTO getById(Long id) {
		ServicioPrecioTO result = null;
		
		try {
			IServicioPrecioBean iServicioPrecioBean = lookupBean();
			
			ServicioPrecio servicioPrecio = iServicioPrecioBean.getById(id);
			
			result = transform(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ServicioPrecioTO getPrecioVigenteByServicioMoneda(ServicioTO servicioTO, MonedaTO monedaTO) {
		ServicioPrecioTO result = null;
		
		try {
			IServicioPrecioBean iServicioPrecioBean = lookupBean();
			
			Servicio servicio = new Servicio();
			servicio.setId(servicioTO.getId());
			
			Moneda moneda = new Moneda();
			moneda.setId(monedaTO.getId());
			
			ServicioPrecio servicioPrecio = iServicioPrecioBean.getPrecioVigenteByServicioMoneda(servicio, moneda);
			
			result = transform(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ServicioPrecioTO getPrecioVigenteParkingMensual() {
		ServicioTO servicioTO = new ServicioTO();
		servicioTO.setId(new Long(Configuration.getInstance().getProperty("Servicio.ParkingMensual")));
		
		MonedaTO monedaTO = new MonedaTO();
		monedaTO.setId(new Long(Configuration.getInstance().getProperty("Moneda.PesosUruguayos")));
		
		return this.getPrecioVigenteByServicioMoneda(servicioTO, monedaTO);
	}
	
 	public void add(ServicioPrecioTO servicioPrecioTO) {
		this.update(servicioPrecioTO);
	}
	
	public void remove(ServicioPrecioTO servicioPrecioTO) {
		try {
			IServicioPrecioBean iServicioPrecioBean = lookupBean();
			
			ServicioPrecio servicioPrecio = new ServicioPrecio();
			servicioPrecio.setId(servicioPrecioTO.getId());
			
			iServicioPrecioBean.remove(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ServicioPrecioTO servicioPrecioTO) {
		try {
			IServicioPrecioBean iServicioPrecioBean = lookupBean();
			
			iServicioPrecioBean.update(transform(servicioPrecioTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServicioPrecioTO transform(ServicioPrecio servicioPrecio) {
		ServicioPrecioTO servicioPrecioTO = new ServicioPrecioTO();
		
		servicioPrecioTO.setMoneda(MonedaDWR.transform(servicioPrecio.getMoneda(), false));
		
		servicioPrecioTO.setPrecio(servicioPrecio.getPrecio());
		
		servicioPrecioTO.setServicio(ServicioDWR.transform(servicioPrecio.getServicio(), false));
		
		servicioPrecioTO.setValidoHasta(servicioPrecio.getValidoHasta());
		
		servicioPrecioTO.setFact(servicioPrecio.getFact());
		servicioPrecioTO.setId(servicioPrecio.getId());
		servicioPrecioTO.setTerm(servicioPrecio.getTerm());
		servicioPrecioTO.setUact(servicioPrecio.getUact());
		
		return servicioPrecioTO;
	}
	
	public static ServicioPrecio transform(ServicioPrecioTO servicioPrecioTO) {
		ServicioPrecio servicioPrecio = new ServicioPrecio();
		
		Moneda moneda = new Moneda();
		moneda.setId(servicioPrecioTO.getMoneda().getId());
		
		servicioPrecio.setMoneda(moneda);
		
		servicioPrecio.setPrecio(servicioPrecioTO.getPrecio());
		
		servicioPrecio.setServicio(ServicioDWR.transform(servicioPrecioTO.getServicio(), false));
		
		servicioPrecio.setValidoHasta(servicioPrecioTO.getValidoHasta());
		
		servicioPrecio.setId(servicioPrecioTO.getId());
		servicioPrecio.setFact(servicioPrecioTO.getFact());
		servicioPrecio.setTerm(servicioPrecioTO.getTerm());
		servicioPrecio.setUact(servicioPrecioTO.getUact());
		
		return servicioPrecio;
	}
}