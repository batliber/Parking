package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IVehiculoBean;
import uy.com.parking.bean.VehiculoBean;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.transferObjects.ClienteTO;
import uy.com.parking.transferObjects.VehiculoTO;

@RemoteProxy
public class VehiculoDWR {

	private IVehiculoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = VehiculoBean.class.getSimpleName();
		String remoteInterfaceName = IVehiculoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IVehiculoBean) context.lookup(lookupName);
	}
	
	public Collection<VehiculoTO> list() {
		Collection<VehiculoTO> result = new LinkedList<VehiculoTO>();
		
		try {
			IVehiculoBean iVehiculoBean = lookupBean();
			
			for (Vehiculo vehiculo : iVehiculoBean.list()) {
				result.add(transform(vehiculo, true));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public VehiculoTO getById(Long id) {
		VehiculoTO result = null;
		
		try {
			IVehiculoBean iVehiculoBean = lookupBean();
			
			Vehiculo vehiculo = iVehiculoBean.getById(id);
			
			result = transform(vehiculo, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public VehiculoTO getByMatricula(String matricula) {
		VehiculoTO result = null;
		
		try {
			IVehiculoBean iVehiculoBean = lookupBean();
			
			Vehiculo vehiculo = iVehiculoBean.getByMatricula(matricula);
			
			if (vehiculo != null) {
				result = transform(vehiculo, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(VehiculoTO vehiculoTO) {
		this.update(vehiculoTO);
	}

	public void remove(VehiculoTO vehiculoTO) {
		try {
			IVehiculoBean iVehiculoBean = lookupBean();
			
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setId(vehiculoTO.getId());
			
			iVehiculoBean.remove(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(VehiculoTO vehiculoTO) {
		try {
			IVehiculoBean iVehiculoBean = lookupBean();
			
			iVehiculoBean.update(transform(vehiculoTO, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static VehiculoTO transform(Vehiculo vehiculo, boolean transformCollections) {
		VehiculoTO vehiculoTO = new VehiculoTO();
		
		if (transformCollections) {
			Collection<ClienteTO> clientesTO = new LinkedList<ClienteTO>();
			for (Cliente cliente : vehiculo.getClientes()) {
				clientesTO.add(ClienteDWR.transform(cliente, false));
			}
			
			vehiculoTO.setClientes(clientesTO);
		}
		
		vehiculoTO.setDepartamento(DepartamentoDWR.transform(vehiculo.getDepartamento()));
		
		vehiculoTO.setDescripcion(vehiculo.getDescripcion());
		vehiculoTO.setMatricula(vehiculo.getMatricula());
		
		vehiculoTO.setFact(vehiculo.getFact());
		vehiculoTO.setId(vehiculo.getId());
		vehiculoTO.setTerm(vehiculo.getTerm());
		vehiculoTO.setUact(vehiculo.getUact());
		
		return vehiculoTO;
	}
	
	public static Vehiculo transform(VehiculoTO vehiculoTO, boolean transformCollections) {
		Vehiculo vehiculo = new Vehiculo();
		
		if (transformCollections) {
			Collection<Cliente> clientes = new LinkedList<Cliente>();
			for (ClienteTO clienteTO : vehiculoTO.getClientes()) {
				clientes.add(ClienteDWR.transform(clienteTO, false));
			}
			
			vehiculo.setClientes(clientes);
		}
		
		vehiculo.setDepartamento(DepartamentoDWR.transform(vehiculoTO.getDepartamento()));
		
		vehiculo.setDescripcion(vehiculoTO.getDescripcion());
		vehiculo.setMatricula(vehiculoTO.getMatricula());
		
		vehiculo.setId(vehiculoTO.getId());
		vehiculo.setFact(vehiculoTO.getFact());
		vehiculo.setTerm(vehiculoTO.getTerm());
		vehiculo.setUact(vehiculoTO.getUact());
		
		return vehiculo;
	}
}