package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.FacturaBean;
import uy.com.parking.bean.IFacturaBean;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.Factura;
import uy.com.parking.entities.FacturaLinea;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.transferObjects.ClienteTO;
import uy.com.parking.transferObjects.FacturaLineaTO;
import uy.com.parking.transferObjects.FacturaTO;
import uy.com.parking.transferObjects.MonedaTO;
import uy.com.parking.transferObjects.ServicioTO;

@RemoteProxy
public class FacturaDWR {

	private IFacturaBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = FacturaBean.class.getSimpleName();
		String remoteInterfaceName = IFacturaBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IFacturaBean) context.lookup(lookupName);
	}
	
	public Collection<FacturaTO> list() {
		Collection<FacturaTO> result = new LinkedList<FacturaTO>();
		
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			for (Factura factura : iFacturaBean.list()) {
				FacturaTO facturaTO = new FacturaTO();
				
				ClienteTO clienteTO = new ClienteTO();
				clienteTO.setFact(factura.getCliente().getFact());
				clienteTO.setFechaBaja(factura.getCliente().getFechaBaja());
				clienteTO.setId(factura.getCliente().getId());
				clienteTO.setNombre(factura.getCliente().getNombre());
				clienteTO.setTerm(factura.getCliente().getTerm());
				clienteTO.setUact(factura.getCliente().getUact());
				
				facturaTO.setCliente(clienteTO);
				
				Collection<FacturaLineaTO> facturaLineas = new LinkedList<FacturaLineaTO>();
				for (FacturaLinea facturaLinea : factura.getFacturaLineas()) {
					FacturaLineaTO facturaLineaTO = new FacturaLineaTO();
					facturaLineaTO.setDetalle(facturaLinea.getDetalle());
					facturaLineaTO.setFact(facturaLinea.getFact());
					facturaLineaTO.setId(facturaLinea.getId());
					facturaLineaTO.setImporteTotal(facturaLinea.getImporteTotal());
					facturaLineaTO.setImporteUnitario(facturaLinea.getImporteUnitario());
					facturaLineaTO.setNumero(facturaLinea.getNumero());
					
					ServicioTO servicioTO = new ServicioTO();
					servicioTO.setDescripcion(facturaLinea.getServicio().getDescripcion());
					servicioTO.setFact(facturaLinea.getServicio().getFact());
					servicioTO.setId(facturaLinea.getServicio().getId());
					servicioTO.setTerm(facturaLinea.getServicio().getTerm());
					servicioTO.setUact(facturaLinea.getServicio().getUact());
					
					facturaLineaTO.setServicio(servicioTO);
					
					facturaLineaTO.setTerm(facturaLinea.getTerm());
					facturaLineaTO.setUact(facturaLinea.getUact());
					facturaLineaTO.setUnidades(facturaLinea.getUnidades());
					
					facturaLineas.add(facturaLineaTO);
				}
				
				facturaTO.setFacturaLineas(facturaLineas);
				
				facturaTO.setFecha(factura.getFecha());
				facturaTO.setImporte(factura.getImporte());
				
				MonedaTO monedaTO = new MonedaTO();
				monedaTO.setAbreviacion(factura.getMoneda().getAbreviacion());
				monedaTO.setDescripcion(factura.getMoneda().getDescripcion());
				monedaTO.setFact(factura.getMoneda().getFact());
				monedaTO.setId(factura.getMoneda().getId());
				monedaTO.setTerm(factura.getMoneda().getTerm());
				monedaTO.setUact(factura.getMoneda().getUact());
				
				facturaTO.setMoneda(monedaTO);
				
				facturaTO.setNumero(factura.getNumero());
				
				facturaTO.setFact(factura.getFact());
				facturaTO.setId(factura.getId());
				facturaTO.setTerm(factura.getTerm());
				facturaTO.setUact(factura.getUact());
				
				result.add(facturaTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(FacturaTO facturaTO) {
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Factura factura = new Factura();
			
			Cliente cliente = new Cliente();
			cliente.setId(facturaTO.getCliente().getId());
			
			factura.setCliente(cliente);
			
			Collection<FacturaLinea> facturaLineas = new LinkedList<FacturaLinea>();
			for (FacturaLineaTO facturaLineaTO : facturaTO.getFacturaLineas()) {
				FacturaLinea facturaLinea = new FacturaLinea();
				facturaLinea.setDetalle(facturaLineaTO.getDetalle());
				facturaLinea.setFact(facturaLineaTO.getFact());
				facturaLinea.setImporteTotal(facturaLineaTO.getImporteTotal());
				facturaLinea.setImporteUnitario(facturaLineaTO.getImporteUnitario());
				facturaLinea.setNumero(facturaLineaTO.getNumero());
				
				Servicio servicio = new Servicio();
				servicio.setId(facturaLinea.getServicio().getId());
				
				facturaLinea.setServicio(servicio);
				
				facturaLinea.setTerm(facturaLineaTO.getTerm());
				facturaLinea.setUact(facturaLineaTO.getUact());
				facturaLinea.setUnidades(facturaLineaTO.getUnidades());
				
				facturaLineas.add(facturaLinea);
			}
			
			factura.setFacturaLineas(facturaLineas);
			
			factura.setFecha(facturaTO.getFecha());
			factura.setImporte(facturaTO.getImporte());
			
			Moneda moneda = new Moneda();
			moneda.setId(facturaTO.getMoneda().getId());
			
			factura.setMoneda(moneda);
			
			factura.setNumero(facturaTO.getNumero());
			
			factura.setFact(facturaTO.getFact());
			factura.setTerm(facturaTO.getTerm());
			factura.setUact(facturaTO.getUact());
			
			iFacturaBean.save(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(FacturaTO facturaTO) {
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Factura factura = new Factura();
			factura.setId(facturaTO.getId());
			
			iFacturaBean.remove(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(FacturaTO facturaTO) {
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Factura factura = new Factura();
			
			Cliente cliente = new Cliente();
			cliente.setId(facturaTO.getCliente().getId());
			
			factura.setCliente(cliente);
			
			Collection<FacturaLinea> facturaLineas = new LinkedList<FacturaLinea>();
			for (FacturaLineaTO facturaLineaTO : facturaTO.getFacturaLineas()) {
				FacturaLinea facturaLinea = new FacturaLinea();
				facturaLinea.setDetalle(facturaLineaTO.getDetalle());
				facturaLinea.setFact(facturaLineaTO.getFact());
				facturaLinea.setImporteTotal(facturaLineaTO.getImporteTotal());
				facturaLinea.setImporteUnitario(facturaLineaTO.getImporteUnitario());
				facturaLinea.setNumero(facturaLineaTO.getNumero());
				
				Servicio servicio = new Servicio();
				servicio.setId(facturaLinea.getServicio().getId());
				
				facturaLinea.setServicio(servicio);
				
				facturaLinea.setTerm(facturaLineaTO.getTerm());
				facturaLinea.setUact(facturaLineaTO.getUact());
				facturaLinea.setUnidades(facturaLineaTO.getUnidades());
				
				facturaLineas.add(facturaLinea);
			}
			
			factura.setFacturaLineas(facturaLineas);
			
			factura.setFecha(facturaTO.getFecha());
			factura.setImporte(facturaTO.getImporte());
			
			Moneda moneda = new Moneda();
			moneda.setId(facturaTO.getMoneda().getId());
			
			factura.setMoneda(moneda);
			
			factura.setNumero(facturaTO.getNumero());
			
			factura.setId(facturaTO.getId());
			factura.setFact(facturaTO.getFact());
			factura.setTerm(facturaTO.getTerm());
			factura.setUact(facturaTO.getUact());
			
			iFacturaBean.update(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}