package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IVehiculoBean;
import uy.com.parking.bean.VehiculoBean;
import uy.com.parking.entities.Vehiculo;
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
				VehiculoTO vehiculoTO = new VehiculoTO();
				
				vehiculoTO.setFact(vehiculo.getFact());
				vehiculoTO.setId(vehiculo.getId());
				vehiculoTO.setTerm(vehiculo.getTerm());
				vehiculoTO.setUact(vehiculo.getUact());
				
				result.add(vehiculoTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(VehiculoTO vehiculoTO) {
		try {
			IVehiculoBean iVehiculoBean = lookupBean();
			
			Vehiculo vehiculo = new Vehiculo();
			
			vehiculo.setFact(vehiculoTO.getFact());
			vehiculo.setTerm(vehiculoTO.getTerm());
			vehiculo.setUact(vehiculoTO.getUact());
			
			iVehiculoBean.save(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			
			Vehiculo vehiculo = new Vehiculo();
			
			vehiculo.setId(vehiculoTO.getId());
			vehiculo.setFact(vehiculoTO.getFact());
			vehiculo.setTerm(vehiculoTO.getTerm());
			vehiculo.setUact(vehiculoTO.getUact());
			
			iVehiculoBean.update(vehiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}