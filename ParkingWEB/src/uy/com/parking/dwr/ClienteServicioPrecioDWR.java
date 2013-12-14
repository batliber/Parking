package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.ClienteServicioPrecioBean;
import uy.com.parking.bean.IClienteServicioPrecioBean;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.ClienteServicioPrecio;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.transferObjects.ClienteServicioPrecioTO;
import uy.com.parking.transferObjects.ClienteTO;
import uy.com.parking.transferObjects.MonedaTO;
import uy.com.parking.transferObjects.ServicioTO;

@RemoteProxy
public class ClienteServicioPrecioDWR {

	private IClienteServicioPrecioBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = ClienteServicioPrecioBean.class.getSimpleName();
		String remoteInterfaceName = IClienteServicioPrecioBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IClienteServicioPrecioBean) context.lookup(lookupName);
	}
	
	public Collection<ClienteServicioPrecioTO> list() {
		Collection<ClienteServicioPrecioTO> result = new LinkedList<ClienteServicioPrecioTO>();
		
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			for (ClienteServicioPrecio clienteServicioPrecio : iClienteServicioPrecioBean.list()) {
				result.add(transform(clienteServicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Collection<ClienteServicioPrecioTO> listVigentes() {
		Collection<ClienteServicioPrecioTO> result = new LinkedList<ClienteServicioPrecioTO>();
		
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			for (ClienteServicioPrecio clienteServicioPrecio : iClienteServicioPrecioBean.listVigentes()) {
				result.add(transform(clienteServicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;	
	}
	
	public ClienteServicioPrecioTO getById(Long id) {
		ClienteServicioPrecioTO result = null;
		
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			ClienteServicioPrecio clienteServicioPrecio = iClienteServicioPrecioBean.getById(id);
			
			result = transform(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ClienteServicioPrecioTO getPrecioVigenteByClienteServicioMoneda(
		ClienteTO clienteTO, ServicioTO servicioTO, MonedaTO monedaTO) {
		ClienteServicioPrecioTO result = null;
		
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			Cliente cliente = new Cliente();
			cliente.setId(clienteTO.getId());
			
			Servicio servicio = new Servicio();
			servicio.setId(servicioTO.getId());
			
			Moneda moneda = new Moneda();
			moneda.setId(monedaTO.getId());
			
			ClienteServicioPrecio clienteServicioPrecio = iClienteServicioPrecioBean.getPrecioVigenteByClienteServicioMoneda(cliente, servicio, moneda);
			
			result = transform(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<ClienteServicioPrecioTO> listVigentesByCliente(ClienteTO clienteTO) {
		Collection<ClienteServicioPrecioTO> result = new LinkedList<ClienteServicioPrecioTO>();
		
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			for (ClienteServicioPrecio clienteServicioPrecio : 
				iClienteServicioPrecioBean.listVigentesByCliente(ClienteDWR.transform(clienteTO, false))) {
				result.add(transform(clienteServicioPrecio));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
 	public void add(ClienteServicioPrecioTO clienteServicioPrecioTO) {
		this.update(clienteServicioPrecioTO);
	}
	
	public void remove(ClienteServicioPrecioTO clienteServicioPrecioTO) {
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			ClienteServicioPrecio clienteServicioPrecio = new ClienteServicioPrecio();
			clienteServicioPrecio.setId(clienteServicioPrecioTO.getId());
			
			iClienteServicioPrecioBean.remove(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ClienteServicioPrecioTO clienteServicioPrecioTO) {
		try {
			IClienteServicioPrecioBean iClienteServicioPrecioBean = lookupBean();
			
			iClienteServicioPrecioBean.update(transform(clienteServicioPrecioTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ClienteServicioPrecioTO transform(ClienteServicioPrecio clienteServicioPrecio) {
		ClienteServicioPrecioTO clienteServicioPrecioTO = new ClienteServicioPrecioTO();
		
		clienteServicioPrecioTO.setCliente(ClienteDWR.transform(clienteServicioPrecio.getCliente(), false));
		
		clienteServicioPrecioTO.setMoneda(MonedaDWR.transform(clienteServicioPrecio.getMoneda(), false));
		
		clienteServicioPrecioTO.setPrecio(clienteServicioPrecio.getPrecio());
		
		clienteServicioPrecioTO.setServicio(ServicioDWR.transform(clienteServicioPrecio.getServicio(), false));
		
		clienteServicioPrecioTO.setValidoHasta(clienteServicioPrecio.getValidoHasta());
		
		clienteServicioPrecioTO.setFact(clienteServicioPrecio.getFact());
		clienteServicioPrecioTO.setId(clienteServicioPrecio.getId());
		clienteServicioPrecioTO.setTerm(clienteServicioPrecio.getTerm());
		clienteServicioPrecioTO.setUact(clienteServicioPrecio.getUact());
		
		return clienteServicioPrecioTO;
	}
	
	public static ClienteServicioPrecio transform(ClienteServicioPrecioTO clienteServicioPrecioTO) {
		ClienteServicioPrecio clienteServicioPrecio = new ClienteServicioPrecio();
		
		if (clienteServicioPrecioTO.getCliente() != null) {
			Cliente cliente = new Cliente();
			cliente.setId(clienteServicioPrecioTO.getId());
			
			clienteServicioPrecio.setCliente(cliente);
		}
		
		Moneda moneda = new Moneda();
		moneda.setId(clienteServicioPrecioTO.getMoneda().getId());
		
		clienteServicioPrecio.setMoneda(moneda);
		
		clienteServicioPrecio.setPrecio(clienteServicioPrecioTO.getPrecio());
		
		Servicio servicio = new Servicio();
		servicio.setId(clienteServicioPrecioTO.getServicio().getId());
		
		clienteServicioPrecio.setServicio(servicio);
		
		clienteServicioPrecio.setValidoHasta(clienteServicioPrecioTO.getValidoHasta());
		
		clienteServicioPrecio.setFact(clienteServicioPrecioTO.getFact());
		clienteServicioPrecio.setId(clienteServicioPrecioTO.getId());
		clienteServicioPrecio.setTerm(clienteServicioPrecioTO.getTerm());
		clienteServicioPrecio.setUact(clienteServicioPrecioTO.getUact());
		
		return clienteServicioPrecio;
	}
}