package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
				result.add(this.transform(clienteServicioPrecio));
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
				result.add(this.transform(clienteServicioPrecio));
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
			
			result = this.transform(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ClienteServicioPrecioTO getPrecioVigenteByClienteServicioMoneda(ClienteTO clienteTO, ServicioTO servicioTO, MonedaTO monedaTO) {
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
			
			result = this.transform(clienteServicioPrecio);
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
			
			ClienteServicioPrecio clienteServicioPrecio = new ClienteServicioPrecio();
			
			Cliente cliente = new Cliente();
			cliente.setDocumento(clienteServicioPrecioTO.getCliente().getDocumento());
			cliente.setDomicilio(clienteServicioPrecioTO.getCliente().getDomicilio());
			cliente.setFact(clienteServicioPrecioTO.getCliente().getFact());
			cliente.setFechaBaja(clienteServicioPrecioTO.getCliente().getFechaBaja());
			cliente.setId(clienteServicioPrecioTO.getCliente().getId());
			cliente.setNombre(clienteServicioPrecioTO.getCliente().getNombre());
			cliente.setTelefono(clienteServicioPrecioTO.getCliente().getTelefono());
			cliente.setTerm(clienteServicioPrecioTO.getCliente().getTerm());
			cliente.setUact(clienteServicioPrecioTO.getCliente().getUact());
			
			clienteServicioPrecio.setCliente(cliente);
			
			Moneda moneda = new Moneda();
			moneda.setAbreviacion(clienteServicioPrecioTO.getMoneda().getAbreviacion());
			moneda.setDescripcion(clienteServicioPrecioTO.getMoneda().getDescripcion());
			moneda.setFact(clienteServicioPrecioTO.getMoneda().getFact());
			moneda.setId(clienteServicioPrecioTO.getMoneda().getId());
			moneda.setTerm(clienteServicioPrecioTO.getMoneda().getTerm());
			moneda.setUact(clienteServicioPrecioTO.getMoneda().getUact());
			
			clienteServicioPrecio.setMoneda(moneda);
			
			clienteServicioPrecio.setPrecio(clienteServicioPrecioTO.getPrecio());
			
			Servicio servicio = new Servicio();
			servicio.setDescripcion(clienteServicioPrecioTO.getServicio().getDescripcion());
			servicio.setFact(clienteServicioPrecioTO.getServicio().getFact());
			servicio.setId(clienteServicioPrecioTO.getServicio().getId());
			servicio.setTerm(clienteServicioPrecioTO.getServicio().getTerm());
			servicio.setUact(clienteServicioPrecioTO.getServicio().getUact());
			
			clienteServicioPrecio.setServicio(servicio);
			
			clienteServicioPrecio.setValidoHasta(clienteServicioPrecioTO.getValidoHasta());
			
			clienteServicioPrecio.setFact(clienteServicioPrecioTO.getFact());
			clienteServicioPrecio.setId(clienteServicioPrecioTO.getId());
			clienteServicioPrecio.setTerm(clienteServicioPrecioTO.getTerm());
			clienteServicioPrecio.setUact(clienteServicioPrecioTO.getUact());
			
			iClienteServicioPrecioBean.update(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClienteServicioPrecioTO transform(ClienteServicioPrecio clienteServicioPrecio) {
		ClienteServicioPrecioTO clienteServicioPrecioTO = new ClienteServicioPrecioTO();
		
		ClienteTO clienteTO = new ClienteTO();
		clienteTO.setDocumento(clienteServicioPrecio.getCliente().getDocumento());
		clienteTO.setDomicilio(clienteServicioPrecio.getCliente().getDomicilio());
		clienteTO.setFact(clienteServicioPrecio.getCliente().getFact());
		clienteTO.setFechaBaja(clienteServicioPrecio.getCliente().getFechaBaja());
		clienteTO.setId(clienteServicioPrecio.getCliente().getId());
		clienteTO.setNombre(clienteServicioPrecio.getCliente().getNombre());
		clienteTO.setTelefono(clienteServicioPrecio.getCliente().getTelefono());
		clienteTO.setTerm(clienteServicioPrecio.getCliente().getTerm());
		clienteTO.setUact(clienteServicioPrecio.getCliente().getUact());
		
		clienteServicioPrecioTO.setCliente(clienteTO);
		
		MonedaTO monedaTO = new MonedaTO();
		monedaTO.setAbreviacion(clienteServicioPrecio.getMoneda().getAbreviacion());
		monedaTO.setDescripcion(clienteServicioPrecio.getMoneda().getDescripcion());
		monedaTO.setFact(clienteServicioPrecio.getMoneda().getFact());
		monedaTO.setId(clienteServicioPrecio.getMoneda().getId());
		monedaTO.setTerm(clienteServicioPrecio.getMoneda().getTerm());
		monedaTO.setUact(clienteServicioPrecio.getMoneda().getUact());
		
		clienteServicioPrecioTO.setMoneda(monedaTO);
		
		clienteServicioPrecioTO.setPrecio(clienteServicioPrecio.getPrecio());
		
		ServicioTO servicioTO = new ServicioTO();
		servicioTO.setDescripcion(clienteServicioPrecio.getServicio().getDescripcion());
		servicioTO.setFact(clienteServicioPrecio.getServicio().getFact());
		servicioTO.setId(clienteServicioPrecio.getServicio().getId());
		servicioTO.setTerm(clienteServicioPrecio.getServicio().getTerm());
		servicioTO.setUact(clienteServicioPrecio.getServicio().getUact());
		
		clienteServicioPrecioTO.setServicio(servicioTO);
		
		clienteServicioPrecioTO.setValidoHasta(clienteServicioPrecio.getValidoHasta());
		
		clienteServicioPrecioTO.setFact(clienteServicioPrecio.getFact());
		clienteServicioPrecioTO.setId(clienteServicioPrecio.getId());
		clienteServicioPrecioTO.setTerm(clienteServicioPrecio.getTerm());
		clienteServicioPrecioTO.setUact(clienteServicioPrecio.getUact());
		
		return clienteServicioPrecioTO;
	}
}