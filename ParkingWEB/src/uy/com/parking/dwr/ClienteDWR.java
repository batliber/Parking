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
import uy.com.parking.transferObjects.ClienteTO;
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
				result.add(this.transform(cliente));
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
			
			result = this.transform(iClienteBean.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(ClienteTO clienteTO) {
		this.update(clienteTO);
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
			
			Cliente cliente = new Cliente();
			
			cliente.setFechaBaja(clienteTO.getFechaBaja());
			cliente.setNombre(clienteTO.getNombre());
			
			Collection<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
			for (VehiculoTO vehiculoTO : clienteTO.getVehiculos()) {
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setId(vehiculoTO.getId());
				
				vehiculos.add(vehiculo);
			}
			
			cliente.setVehiculos(vehiculos);
			
			cliente.setId(clienteTO.getId());
			cliente.setFact(clienteTO.getFact());
			cliente.setTerm(clienteTO.getTerm());
			cliente.setUact(clienteTO.getUact());
			
			iClienteBean.update(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClienteTO transform(Cliente cliente) {
		ClienteTO clienteTO = new ClienteTO();
		
		clienteTO.setFechaBaja(cliente.getFechaBaja());
		clienteTO.setNombre(cliente.getNombre());
		
		Collection<VehiculoTO> vehiculosTO = new LinkedList<VehiculoTO>();
		for (Vehiculo vehiculo : cliente.getVehiculos()) {
			VehiculoTO vehiculoTO = new VehiculoTO();
			vehiculoTO.setDescripcion(vehiculo.getDescripcion());
			vehiculoTO.setFact(vehiculo.getFact());
			vehiculoTO.setId(vehiculo.getId());
			vehiculoTO.setMatricula(vehiculo.getMatricula());
			vehiculoTO.setTerm(vehiculo.getTerm());
			vehiculoTO.setUact(vehiculo.getUact());
			
			vehiculosTO.add(vehiculoTO);
		}
		
		clienteTO.setVehiculos(vehiculosTO);
		
		clienteTO.setFact(cliente.getFact());
		clienteTO.setId(cliente.getId());
		clienteTO.setTerm(cliente.getTerm());
		clienteTO.setUact(cliente.getUact());
		
		return clienteTO;
	}
}