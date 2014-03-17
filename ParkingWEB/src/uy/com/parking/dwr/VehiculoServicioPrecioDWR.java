package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IVehiculoServicioPrecioBean;
import uy.com.parking.bean.VehiculoServicioPrecioBean;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.entities.VehiculoServicioPrecio;
import uy.com.parking.transferObjects.MonedaTO;
import uy.com.parking.transferObjects.ServicioTO;
import uy.com.parking.transferObjects.VehiculoServicioPrecioTO;
import uy.com.parking.transferObjects.VehiculoTO;
import uy.com.parking.util.Configuration;

@RemoteProxy
public class VehiculoServicioPrecioDWR {

	private IVehiculoServicioPrecioBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = VehiculoServicioPrecioBean.class.getSimpleName();
		String remoteInterfaceName = IVehiculoServicioPrecioBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IVehiculoServicioPrecioBean) context.lookup(lookupName);
	}
	
	public Collection<VehiculoServicioPrecioTO> list() {
		Collection<VehiculoServicioPrecioTO> result = new LinkedList<VehiculoServicioPrecioTO>();
		
		try {
			IVehiculoServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			for (VehiculoServicioPrecio clienteServicioPrecio : iClienteServicioPrecioBean.list()) {
				result.add(transform(clienteServicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Collection<VehiculoServicioPrecioTO> listVigentes() {
		Collection<VehiculoServicioPrecioTO> result = new LinkedList<VehiculoServicioPrecioTO>();
		
		try {
			IVehiculoServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			for (VehiculoServicioPrecio clienteServicioPrecio : iClienteServicioPrecioBean.listVigentes()) {
				result.add(transform(clienteServicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	public VehiculoServicioPrecioTO getById(Long id) {
		VehiculoServicioPrecioTO result = null;
		
		try {
			IVehiculoServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			VehiculoServicioPrecio clienteServicioPrecio = iClienteServicioPrecioBean.getById(id);
			
			result = transform(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public VehiculoServicioPrecioTO getPrecioVigenteByVehiculoServicioMoneda(
		VehiculoTO vehiculoTO, ServicioTO servicioTO, MonedaTO monedaTO) {
		VehiculoServicioPrecioTO result = null;
		
		try {
			IVehiculoServicioPrecioBean iVehiculoServicioPrecioBean = lookupBean();
			
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setId(vehiculoTO.getId());
			
			Servicio servicio = new Servicio();
			servicio.setId(servicioTO.getId());
			
			Moneda moneda = new Moneda();
			moneda.setId(monedaTO.getId());
			
			VehiculoServicioPrecio vehiculoServicioPrecio = 
				iVehiculoServicioPrecioBean.getPrecioVigenteByVehiculoServicioMoneda(vehiculo, servicio, moneda);
			
			if (vehiculoServicioPrecio != null) {
				result = transform(vehiculoServicioPrecio);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<VehiculoServicioPrecioTO> listVigentesByVehiculo(VehiculoTO vehiculoTO) {
		Collection<VehiculoServicioPrecioTO> result = new LinkedList<VehiculoServicioPrecioTO>();
		
		try {
			IVehiculoServicioPrecioBean iVehiculoServicioPrecioBean = lookupBean();
			
			for (VehiculoServicioPrecio vehiculoServicioPrecio : 
				iVehiculoServicioPrecioBean.listVigentesByVehiculo(VehiculoDWR.transform(vehiculoTO, false))) {
				result.add(transform(vehiculoServicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public VehiculoServicioPrecioTO getPrecioVigenteParkingMensualByVehiculo(VehiculoTO vehiculoTO) {
		ServicioTO servicioTO = new ServicioTO();
		servicioTO.setId(new Long(Configuration.getInstance().getProperty("Servicio.ParkingMensual")));
		
		MonedaTO monedaTO = new MonedaTO();
		monedaTO.setId(new Long(Configuration.getInstance().getProperty("Moneda.PesosUruguayos")));
		
		return this.getPrecioVigenteByVehiculoServicioMoneda(vehiculoTO, servicioTO, monedaTO);
	}
	
 	public void add(VehiculoServicioPrecioTO clienteServicioPrecioTO) {
		this.update(clienteServicioPrecioTO);
	}
	
	public void remove(VehiculoServicioPrecioTO clienteServicioPrecioTO) {
		try {
			IVehiculoServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			VehiculoServicioPrecio clienteServicioPrecio = new VehiculoServicioPrecio();
			clienteServicioPrecio.setId(clienteServicioPrecioTO.getId());
			
			iClienteServicioPrecioBean.remove(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(VehiculoServicioPrecioTO clienteServicioPrecioTO) {
		try {
			IVehiculoServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			iClienteServicioPrecioBean.update(transform(clienteServicioPrecioTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static VehiculoServicioPrecioTO transform(VehiculoServicioPrecio vehiculoServicioPrecio) {
		VehiculoServicioPrecioTO vehiculoServicioPrecioTO = new VehiculoServicioPrecioTO();
		
		vehiculoServicioPrecioTO.setVehiculo(VehiculoDWR.transform(vehiculoServicioPrecio.getVehiculo(), false));
		
		vehiculoServicioPrecioTO.setMoneda(MonedaDWR.transform(vehiculoServicioPrecio.getMoneda(), false));
		
		vehiculoServicioPrecioTO.setPrecio(vehiculoServicioPrecio.getPrecio());
		
		vehiculoServicioPrecioTO.setServicio(ServicioDWR.transform(vehiculoServicioPrecio.getServicio(), false));
		
		vehiculoServicioPrecioTO.setValidoHasta(vehiculoServicioPrecio.getValidoHasta());
		
		vehiculoServicioPrecioTO.setFact(vehiculoServicioPrecio.getFact());
		vehiculoServicioPrecioTO.setId(vehiculoServicioPrecio.getId());
		vehiculoServicioPrecioTO.setTerm(vehiculoServicioPrecio.getTerm());
		vehiculoServicioPrecioTO.setUact(vehiculoServicioPrecio.getUact());
		
		return vehiculoServicioPrecioTO;
	}
	
	public static VehiculoServicioPrecio transform(VehiculoServicioPrecioTO vehiculoServicioPrecioTO) {
		VehiculoServicioPrecio vehiculoServicioPrecio = new VehiculoServicioPrecio();
		
		if (vehiculoServicioPrecioTO.getVehiculo() != null) {
			vehiculoServicioPrecio.setVehiculo(VehiculoDWR.transform(vehiculoServicioPrecioTO.getVehiculo(), false));
		}
		
		Moneda moneda = new Moneda();
		moneda.setId(vehiculoServicioPrecioTO.getMoneda().getId());
		
		vehiculoServicioPrecio.setMoneda(moneda);
		
		vehiculoServicioPrecio.setPrecio(vehiculoServicioPrecioTO.getPrecio());
		
		Servicio servicio = new Servicio();
		servicio.setId(vehiculoServicioPrecioTO.getServicio().getId());
		
		vehiculoServicioPrecio.setServicio(servicio);
		
		vehiculoServicioPrecio.setValidoHasta(vehiculoServicioPrecioTO.getValidoHasta());
		
		vehiculoServicioPrecio.setFact(vehiculoServicioPrecioTO.getFact());
		vehiculoServicioPrecio.setId(vehiculoServicioPrecioTO.getId());
		vehiculoServicioPrecio.setTerm(vehiculoServicioPrecioTO.getTerm());
		vehiculoServicioPrecio.setUact(vehiculoServicioPrecioTO.getUact());
		
		return vehiculoServicioPrecio;
	}
}