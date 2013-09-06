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
				ServicioPrecioTO servicioPrecioTO = new ServicioPrecioTO();
				
				MonedaTO monedaTO = new MonedaTO();
				monedaTO.setAbreviacion(servicioPrecio.getMoneda().getAbreviacion());
				monedaTO.setDescripcion(servicioPrecio.getMoneda().getDescripcion());
				monedaTO.setFact(servicioPrecio.getMoneda().getFact());
				monedaTO.setId(servicioPrecio.getMoneda().getId());
				monedaTO.setTerm(servicioPrecio.getMoneda().getTerm());
				monedaTO.setUact(servicioPrecio.getMoneda().getUact());
				
				servicioPrecioTO.setMoneda(monedaTO);
				
				servicioPrecioTO.setPrecio(servicioPrecio.getPrecio());
				
				ServicioTO servicioTO = new ServicioTO();
				servicioTO.setDescripcion(servicioPrecio.getServicio().getDescripcion());
				servicioTO.setFact(servicioPrecio.getServicio().getFact());
				servicioTO.setId(servicioPrecio.getServicio().getId());
				servicioTO.setTerm(servicioPrecio.getServicio().getTerm());
				servicioTO.setUact(servicioPrecio.getServicio().getUact());
				
				servicioPrecioTO.setServicio(servicioTO);
				
				servicioPrecioTO.setValidoHasta(servicioPrecio.getValidoHasta());
				
				servicioPrecioTO.setFact(servicioPrecio.getFact());
				servicioPrecioTO.setId(servicioPrecio.getId());
				servicioPrecioTO.setTerm(servicioPrecio.getTerm());
				servicioPrecioTO.setUact(servicioPrecio.getUact());
				
				result.add(servicioPrecioTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
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
			
			ServicioPrecio servicioPrecio = new ServicioPrecio();
			
			Moneda moneda = new Moneda();
			moneda.setAbreviacion(servicioPrecioTO.getMoneda().getAbreviacion());
			moneda.setDescripcion(servicioPrecioTO.getMoneda().getDescripcion());
			moneda.setFact(servicioPrecioTO.getMoneda().getFact());
			moneda.setId(servicioPrecioTO.getMoneda().getId());
			moneda.setTerm(servicioPrecioTO.getMoneda().getTerm());
			moneda.setUact(servicioPrecioTO.getMoneda().getUact());
			
			servicioPrecio.setMoneda(moneda);
			
			servicioPrecio.setPrecio(servicioPrecioTO.getPrecio());
			
			Servicio servicio = new Servicio();
			servicio.setDescripcion(servicioPrecioTO.getServicio().getDescripcion());
			servicio.setFact(servicioPrecioTO.getServicio().getFact());
			servicio.setId(servicioPrecioTO.getServicio().getId());
			servicio.setTerm(servicioPrecioTO.getServicio().getTerm());
			servicio.setUact(servicioPrecioTO.getServicio().getUact());
			
			servicioPrecio.setServicio(servicio);
			
			servicioPrecio.setValidoHasta(servicioPrecioTO.getValidoHasta());
			
			servicioPrecio.setFact(servicioPrecioTO.getFact());
			servicioPrecio.setId(servicioPrecioTO.getId());
			servicioPrecio.setTerm(servicioPrecioTO.getTerm());
			servicioPrecio.setUact(servicioPrecioTO.getUact());
			
			iServicioPrecioBean.update(servicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}