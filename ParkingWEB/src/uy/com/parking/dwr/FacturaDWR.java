package uy.com.parking.dwr;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.FacturaBean;
import uy.com.parking.bean.IFacturaBean;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.CobranzaMovimiento;
import uy.com.parking.entities.Factura;
import uy.com.parking.entities.FacturaLinea;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioTipo;
import uy.com.parking.transferObjects.CobranzaMovimientoTO;
import uy.com.parking.transferObjects.FacturaLineaTO;
import uy.com.parking.transferObjects.FacturaTO;

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
				result.add(this.transform(factura));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public FacturaTO getById(Long id) {
		FacturaTO result = null;
		
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Factura factura = iFacturaBean.getById(id);
			
			if (factura != null) {
				result = this.transform(factura);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public FacturaTO getByNumero(Long numero) {
		FacturaTO result = null;
		
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Factura factura = iFacturaBean.getByNumero(numero);
			
			if (factura != null) {
				result = this.transform(factura);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(FacturaTO facturaTO) {
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			iFacturaBean.save(this.transform(facturaTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FacturaTO saveAndCloseRegistro(FacturaTO facturaTO, String matricula) {
		FacturaTO result = null;
		
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Factura factura = this.transform(facturaTO);
			
			result = this.transform(iFacturaBean.saveAndCloseRegistro(factura, matricula));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public FacturaTO generateFacturaByMatricula(String matricula) {
		FacturaTO result = null;
		
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			result = this.transform(iFacturaBean.generateFacturaByMatricula(matricula));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public FacturaTO facturarCobranzaMovimientos(FacturaTO facturaTO, 
			Collection<CobranzaMovimientoTO> cobranzaMovimientoTOs) {
		FacturaTO result = null;
		
		try {
			IFacturaBean iFacturaBean = lookupBean();
			
			Collection<CobranzaMovimiento> cobranzaMovimientos = new LinkedList<CobranzaMovimiento>();
			for (CobranzaMovimientoTO cobranzaMovimientoTO : cobranzaMovimientoTOs) {
				cobranzaMovimientos.add(CobranzaMovimientoDWR.transform(cobranzaMovimientoTO));
			}
			
			result = this.transform(
				iFacturaBean.facturarCobranzaMovimientos(
					transform(facturaTO), cobranzaMovimientos
				)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
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
			
			Set<FacturaLinea> facturaLineas = new HashSet<FacturaLinea>();
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
			factura.setImporteIVA(facturaTO.getImporteIVA());
			factura.setImporteSubtotal(facturaTO.getImporteSubtotal());
			factura.setImporteTotal(facturaTO.getImporteTotal());
			
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

	private FacturaTO transform(Factura factura) {
		FacturaTO facturaTO = new FacturaTO();
		
		facturaTO.setApellido(factura.getApellido());
		
		facturaTO.setCliente(ClienteDWR.transform(factura.getCliente(), false));
		
		facturaTO.setDomicilio(factura.getDomicilio());
		
		List<FacturaLineaTO> facturaLineas = new LinkedList<FacturaLineaTO>();
		for (FacturaLinea facturaLinea : factura.getFacturaLineas()) {
			FacturaLineaTO facturaLineaTO = new FacturaLineaTO();
			facturaLineaTO.setDetalle(facturaLinea.getDetalle());
			facturaLineaTO.setFact(facturaLinea.getFact());
			facturaLineaTO.setId(facturaLinea.getId());
			facturaLineaTO.setImporteTotal(facturaLinea.getImporteTotal());
			facturaLineaTO.setImporteUnitario(facturaLinea.getImporteUnitario());
			facturaLineaTO.setNumero(facturaLinea.getNumero());
			
			facturaLineaTO.setServicio(ServicioDWR.transform(facturaLinea.getServicio(), false));
			
			facturaLineaTO.setTerm(facturaLinea.getTerm());
			facturaLineaTO.setUact(facturaLinea.getUact());
			facturaLineaTO.setUnidades(facturaLinea.getUnidades());
			
			facturaLineas.add(facturaLineaTO);
		}
		
		Collections.sort(facturaLineas, new Comparator<FacturaLineaTO>() {
			public int compare(FacturaLineaTO arg0, FacturaLineaTO arg1) {
				return arg0.getNumero().compareTo(arg1.getNumero());
			}
		});
		
		facturaTO.setFacturaLineas(facturaLineas);
		
		facturaTO.setFecha(factura.getFecha());
		facturaTO.setImporteIVA(factura.getImporteIVA());
		facturaTO.setImporteSubtotal(factura.getImporteSubtotal());
		facturaTO.setImporteTotal(factura.getImporteTotal());
		
		facturaTO.setMoneda(MonedaDWR.transform(factura.getMoneda(), false));
		
		facturaTO.setNombre(factura.getNombre());
		facturaTO.setNumero(factura.getNumero());
		facturaTO.setTelefono(factura.getTelefono());
		
		facturaTO.setFact(factura.getFact());
		facturaTO.setId(factura.getId());
		facturaTO.setTerm(factura.getTerm());
		facturaTO.setUact(factura.getUact());
		
		return facturaTO;
	}

	private Factura transform(FacturaTO facturaTO) {
		Factura factura = new Factura();
		
		factura.setApellido(facturaTO.getApellido());
		
		Cliente cliente = new Cliente();
		cliente.setId(facturaTO.getCliente().getId());
		
		factura.setCliente(cliente);
		
		factura.setDomicilio(facturaTO.getDomicilio());
		
		Set<FacturaLinea> facturaLineas = new HashSet<FacturaLinea>();
		for (FacturaLineaTO facturaLineaTO : facturaTO.getFacturaLineas()) {
			FacturaLinea facturaLinea = new FacturaLinea();
			facturaLinea.setDetalle(facturaLineaTO.getDetalle());
			
			facturaLinea.setFactura(factura);
			
			facturaLinea.setImporteTotal(facturaLineaTO.getImporteTotal());
			facturaLinea.setImporteUnitario(facturaLineaTO.getImporteUnitario());
			facturaLinea.setNumero(facturaLineaTO.getNumero());
			
			Servicio servicio = new Servicio();
			servicio.setId(facturaLineaTO.getServicio().getId());
			
			ServicioTipo servicioTipo = new ServicioTipo();
			servicioTipo.setId(facturaLineaTO.getServicio().getServicioTipo().getId());
			
			servicio.setServicioTipo(servicioTipo);
			
			facturaLinea.setServicio(servicio);
			
			facturaLinea.setUnidades(facturaLineaTO.getUnidades());
			
			facturaLinea.setFact(facturaLineaTO.getFact());
			facturaLinea.setTerm(facturaLineaTO.getTerm());
			facturaLinea.setUact(facturaLineaTO.getUact());
			
			facturaLineas.add(facturaLinea);
		}
		
		factura.setFacturaLineas(facturaLineas);
		
		factura.setFecha(facturaTO.getFecha());
		factura.setImporteIVA(facturaTO.getImporteIVA());
		factura.setImporteSubtotal(facturaTO.getImporteSubtotal());
		factura.setImporteTotal(facturaTO.getImporteTotal());
		
		Moneda moneda = new Moneda();
		moneda.setId(facturaTO.getMoneda().getId());
		
		factura.setMoneda(moneda);
		
		factura.setNombre(facturaTO.getNombre());
		factura.setRut(facturaTO.getRut());
		factura.setTelefono(facturaTO.getTelefono());
		
		factura.setFact(facturaTO.getFact());
		factura.setTerm(facturaTO.getTerm());
		factura.setUact(facturaTO.getUact());
		
		return factura;
	}
}