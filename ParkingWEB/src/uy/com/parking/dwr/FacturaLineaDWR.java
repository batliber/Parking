package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.FacturaLineaBean;
import uy.com.parking.bean.IFacturaLineaBean;
import uy.com.parking.entities.Factura;
import uy.com.parking.entities.FacturaLinea;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.transferObjects.ClienteTO;
import uy.com.parking.transferObjects.FacturaLineaTO;
import uy.com.parking.transferObjects.FacturaTO;
import uy.com.parking.transferObjects.MonedaTO;
import uy.com.parking.transferObjects.ServicioTO;
import uy.com.parking.transferObjects.VehiculoTO;

@RemoteProxy
public class FacturaLineaDWR {

	private IFacturaLineaBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = FacturaLineaBean.class.getSimpleName();
		String remoteInterfaceName = IFacturaLineaBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IFacturaLineaBean) context.lookup(lookupName);
	}
	
	public Collection<FacturaLineaTO> list() {
		Collection<FacturaLineaTO> result = new LinkedList<FacturaLineaTO>();
		
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			for (FacturaLinea facturaLinea : iFacturaLineaBean.list()) {
				FacturaLineaTO facturaLineaTO = new FacturaLineaTO();
				
				facturaLineaTO.setDetalle(facturaLinea.getDetalle());
				
				FacturaTO facturaTO = new FacturaTO();
				
				ClienteTO clienteTO = new ClienteTO();
				clienteTO.setFact(facturaLinea.getFactura().getCliente().getFact());
				clienteTO.setFechaBaja(facturaLinea.getFactura().getCliente().getFechaBaja());
				clienteTO.setId(facturaLinea.getFactura().getCliente().getId());
				clienteTO.setNombre(facturaLinea.getFactura().getCliente().getNombre());
				clienteTO.setTerm(facturaLinea.getFactura().getCliente().getTerm());
				clienteTO.setUact(facturaLinea.getFactura().getCliente().getUact());
				
				Collection<VehiculoTO> vehiculos = new LinkedList<VehiculoTO>();
				for (Vehiculo vehiculo : facturaLinea.getFactura().getCliente().getVehiculos()) {
					VehiculoTO vehiculoTO = new VehiculoTO();
					vehiculoTO.setDescripcion(vehiculo.getDescripcion());
					vehiculoTO.setFact(vehiculo.getFact());
					vehiculoTO.setId(vehiculo.getId());
					vehiculoTO.setMatricula(vehiculo.getMatricula());
					vehiculoTO.setTerm(vehiculo.getTerm());
					vehiculoTO.setUact(vehiculo.getUact());
					
					vehiculos.add(vehiculoTO);
				}
				
				clienteTO.setVehiculos(vehiculos);
				
				facturaTO.setCliente(clienteTO);
				
				facturaTO.setFact(facturaLinea.getFactura().getFact());
				facturaTO.setFecha(facturaLinea.getFactura().getFecha());
				facturaTO.setId(facturaLinea.getFactura().getId());
				facturaTO.setImporte(facturaLinea.getFactura().getImporte());
				
				MonedaTO monedaTO = new MonedaTO();
				monedaTO.setAbreviacion(facturaLinea.getFactura().getMoneda().getAbreviacion());
				monedaTO.setDescripcion(facturaLinea.getFactura().getMoneda().getDescripcion());
				monedaTO.setFact(facturaLinea.getFactura().getMoneda().getFact());
				monedaTO.setId(facturaLinea.getFactura().getMoneda().getId());
				monedaTO.setTerm(facturaLinea.getFactura().getMoneda().getTerm());
				monedaTO.setUact(facturaLinea.getFactura().getMoneda().getUact());
				
				facturaTO.setMoneda(monedaTO);
				
				facturaTO.setNumero(facturaLinea.getFactura().getNumero());
				facturaTO.setTerm(facturaLinea.getFactura().getTerm());
				facturaTO.setUact(facturaLinea.getFactura().getUact());
				
				facturaLineaTO.setFactura(facturaTO);
				
				facturaLineaTO.setImporteTotal(facturaLinea.getImporteTotal());
				facturaLineaTO.setImporteUnitario(facturaLinea.getImporteUnitario());
				facturaLineaTO.setNumero(facturaLinea.getNumero());
				
				ServicioTO servicioTO = new ServicioTO();
				servicioTO.setDescripcion(facturaLinea.getServicio().getDescripcion());
				servicioTO.setFact(facturaLinea.getServicio().getFact());
				servicioTO.setId(facturaLinea.getServicio().getId());
				servicioTO.setPrecio(facturaLinea.getServicio().getPrecio());
				servicioTO.setTerm(facturaLinea.getServicio().getTerm());
				servicioTO.setUact(facturaLinea.getServicio().getUact());
				
				facturaLineaTO.setServicio(servicioTO);
				
				facturaLineaTO.setUnidades(facturaLinea.getUnidades());
				
				facturaLineaTO.setFact(facturaLinea.getFact());
				facturaLineaTO.setId(facturaLinea.getId());
				facturaLineaTO.setTerm(facturaLinea.getTerm());
				facturaLineaTO.setUact(facturaLinea.getUact());
				
				result.add(facturaLineaTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(FacturaLineaTO facturaLineaTO) {
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			FacturaLinea facturaLinea = new FacturaLinea();
			
			facturaLinea.setDetalle(facturaLineaTO.getDetalle());
			
			Factura factura = new Factura();
			factura.setId(facturaLineaTO.getFactura().getId());
			
			facturaLinea.setFactura(factura);
			
			facturaLinea.setImporteTotal(facturaLineaTO.getImporteTotal());
			facturaLinea.setImporteUnitario(facturaLineaTO.getImporteUnitario());
			facturaLinea.setNumero(facturaLineaTO.getNumero());
			
			Servicio servicio = new Servicio();
			servicio.setId(facturaLineaTO.getServicio().getId());
			
			facturaLinea.setServicio(servicio);
			
			facturaLinea.setUnidades(facturaLineaTO.getUnidades());
			
			facturaLinea.setFact(facturaLineaTO.getFact());
			facturaLinea.setTerm(facturaLineaTO.getTerm());
			facturaLinea.setUact(facturaLineaTO.getUact());
			
			iFacturaLineaBean.save(facturaLinea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(FacturaLineaTO facturaLineaTO) {
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			FacturaLinea facturaLinea = new FacturaLinea();
			facturaLinea.setId(facturaLineaTO.getId());
			
			iFacturaLineaBean.remove(facturaLinea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(FacturaLineaTO facturaLineaTO) {
		try {
			IFacturaLineaBean iFacturaLineaBean = lookupBean();
			
			FacturaLinea facturaLinea = new FacturaLinea();
			
			facturaLinea.setDetalle(facturaLineaTO.getDetalle());
			
			Factura factura = new Factura();
			factura.setId(facturaLineaTO.getFactura().getId());
			
			facturaLinea.setFactura(factura);
			
			facturaLinea.setImporteTotal(facturaLineaTO.getImporteTotal());
			facturaLinea.setImporteUnitario(facturaLineaTO.getImporteUnitario());
			facturaLinea.setNumero(facturaLineaTO.getNumero());
			
			Servicio servicio = new Servicio();
			servicio.setId(facturaLineaTO.getServicio().getId());
			
			facturaLinea.setServicio(servicio);
			
			facturaLinea.setUnidades(facturaLineaTO.getUnidades());
			
			facturaLinea.setId(facturaLineaTO.getId());
			facturaLinea.setFact(facturaLineaTO.getFact());
			facturaLinea.setTerm(facturaLineaTO.getTerm());
			facturaLinea.setUact(facturaLineaTO.getUact());
			
			iFacturaLineaBean.update(facturaLinea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}