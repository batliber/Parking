package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.ClienteBean;
import uy.com.parking.bean.IClienteBean;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.entities.VehiculoServicioPrecio;
import uy.com.parking.transferObjects.ClienteTO;
import uy.com.parking.transferObjects.VehiculoServicioPrecioTO;
import uy.com.parking.transferObjects.VehiculoTO;

@RemoteProxy
public class ClienteDWR {

	private IClienteBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = ClienteBean.class.getSimpleName();
		String remoteInterfaceName = IClienteBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IClienteBean) context.lookup(lookupName);
	}
	
	public Collection<ClienteTO> list() {
		Collection<ClienteTO> result = new LinkedList<ClienteTO>();
		
		try {
			IClienteBean iClienteBean = lookupBean();
			
			for (Cliente cliente : iClienteBean.list()) {
				result.add(transform(cliente, true));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ClienteTO getById(Long id) {
		ClienteTO result = null;
		
		try {
			IClienteBean iClienteBean = lookupBean();
			
			result = transform(iClienteBean.getById(id), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ClienteTO getByDocumento(String documento) {
		ClienteTO result = null;
		
		try {
			IClienteBean iClienteBean = lookupBean();
			
			Cliente cliente = iClienteBean.getByDocumento(documento);
			
			if (cliente != null) {
				result = transform(cliente, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String exportarAExcel() {
		String result = null;
		
		try {
			IClienteBean iClienteBean = lookupBean();
			
			result = iClienteBean.exportarAExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(ClienteTO clienteTO) {
		this.update(clienteTO);
	}
	
	public void addConVehiculoServicioPrecios(
		ClienteTO clienteTO, 
		Collection<VehiculoServicioPrecioTO> vehiculoServicioPreciosTO) {
		this.updateConVehiculoServicioPrecios(clienteTO, vehiculoServicioPreciosTO);
	}

	public void remove(ClienteTO clienteTO) {
		try {
			IClienteBean iClienteBean = lookupBean();
			
			Cliente cliente = new Cliente();
			cliente.setId(clienteTO.getId());
			
			iClienteBean.remove(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(ClienteTO clienteTO) {
		try {
			IClienteBean iClienteBean = lookupBean();
			
			iClienteBean.update(transform(clienteTO, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateConVehiculoServicioPrecios(
		ClienteTO clienteTO, 
		Collection<VehiculoServicioPrecioTO> vehiculoServicioPreciosTO) {
		try {
			IClienteBean iClienteBean = lookupBean();
			
			Collection<VehiculoServicioPrecio> vehiculoServicioPrecios = 
				new LinkedList<VehiculoServicioPrecio>();
			
			for (VehiculoServicioPrecioTO vehiculoServicioPrecioTO : vehiculoServicioPreciosTO) {
				vehiculoServicioPrecios.add(VehiculoServicioPrecioDWR.transform(vehiculoServicioPrecioTO));
			}
			
			iClienteBean.updateConVehiculoServicioPrecios(
				transform(clienteTO, true),
				vehiculoServicioPrecios
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ClienteTO transform(Cliente cliente, boolean transformCollections) {
		ClienteTO clienteTO = new ClienteTO();
		
		clienteTO.setApellido(cliente.getApellido());
		clienteTO.setDocumento(cliente.getDocumento());
		clienteTO.setDomicilio(cliente.getDomicilio());
		clienteTO.setFechaAlta(cliente.getFechaAlta());
		clienteTO.setFechaBaja(cliente.getFechaBaja());
		clienteTO.setNombre(cliente.getNombre());
		clienteTO.setTelefono(cliente.getTelefono());
		
		if (transformCollections) {
			Collection<VehiculoTO> vehiculosTO = new LinkedList<VehiculoTO>();
			for (Vehiculo vehiculo : cliente.getVehiculos()) {
				vehiculosTO.add(VehiculoDWR.transform(vehiculo, false));
			}
			
			clienteTO.setVehiculos(vehiculosTO);
		}
		
		clienteTO.setFact(cliente.getFact());
		clienteTO.setId(cliente.getId());
		clienteTO.setTerm(cliente.getTerm());
		clienteTO.setUact(cliente.getUact());
		
		return clienteTO;
	}
	
	public static Cliente transform(ClienteTO clienteTO, boolean transformCollections) {
		Cliente cliente = new Cliente();
		
		cliente.setApellido(clienteTO.getApellido());
		cliente.setDocumento(clienteTO.getDocumento());
		cliente.setDomicilio(clienteTO.getDomicilio());
		cliente.setFechaAlta(clienteTO.getFechaAlta());
		cliente.setFechaBaja(clienteTO.getFechaBaja());
		cliente.setNombre(clienteTO.getNombre());
		cliente.setTelefono(clienteTO.getTelefono());
		
		if (transformCollections) {
			Collection<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
			for (VehiculoTO vehiculoTO : clienteTO.getVehiculos()) {
				vehiculos.add(VehiculoDWR.transform(vehiculoTO, false));
			}
			
			cliente.setVehiculos(vehiculos);
		}
		
		cliente.setId(clienteTO.getId());
		cliente.setFact(clienteTO.getFact());
		cliente.setTerm(clienteTO.getTerm());
		cliente.setUact(clienteTO.getUact());
		
		return cliente;
	}
}