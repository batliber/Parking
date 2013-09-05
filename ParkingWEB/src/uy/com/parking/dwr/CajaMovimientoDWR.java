package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.CajaMovimientoBean;
import uy.com.parking.bean.ICajaMovimientoBean;
import uy.com.parking.entities.CajaMovimiento;
import uy.com.parking.entities.CajaTipoDocumento;
import uy.com.parking.entities.Moneda;
import uy.com.parking.transferObjects.CajaMovimientoTO;
import uy.com.parking.transferObjects.CajaTipoDocumentoTO;
import uy.com.parking.transferObjects.MonedaTO;

@RemoteProxy
public class CajaMovimientoDWR {

	private ICajaMovimientoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = CajaMovimientoBean.class.getSimpleName();
		String remoteInterfaceName = ICajaMovimientoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (ICajaMovimientoBean) context.lookup(lookupName);
	}
	
	public Collection<CajaMovimientoTO> list() {
		Collection<CajaMovimientoTO> result = new LinkedList<CajaMovimientoTO>();
		
		try {
			ICajaMovimientoBean iCajaMovimientoBean = lookupBean();
			
			for (CajaMovimiento cajaMovimiento : iCajaMovimientoBean.list()) {
				CajaMovimientoTO cajaMovimientoTO = new CajaMovimientoTO();
				
				CajaTipoDocumentoTO cajaTipoDocumentoTO = new CajaTipoDocumentoTO();
				cajaTipoDocumentoTO.setDescripcion(cajaMovimiento.getCajaTipoDocumento().getDescripcion());
				cajaTipoDocumentoTO.setFact(cajaMovimiento.getCajaTipoDocumento().getFact());
				cajaTipoDocumentoTO.setId(cajaMovimiento.getCajaTipoDocumento().getId());
				cajaTipoDocumentoTO.setSigno(cajaMovimiento.getCajaTipoDocumento().getSigno());
				cajaTipoDocumentoTO.setTerm(cajaMovimiento.getCajaTipoDocumento().getTerm());
				cajaTipoDocumentoTO.setUact(cajaMovimiento.getCajaTipoDocumento().getUact());
				
				cajaMovimientoTO.setCajaTipoDocumento(cajaTipoDocumentoTO);
				
				cajaMovimientoTO.setDocumentoId(cajaMovimiento.getDocumentoId());
				cajaMovimientoTO.setImporte(cajaMovimiento.getImporte());
				
				MonedaTO monedaTO = new MonedaTO();
				monedaTO.setAbreviacion(cajaMovimiento.getMoneda().getAbreviacion());
				monedaTO.setDescripcion(cajaMovimiento.getMoneda().getDescripcion());
				monedaTO.setFact(cajaMovimiento.getMoneda().getFact());
				monedaTO.setId(cajaMovimiento.getMoneda().getId());
				monedaTO.setTerm(cajaMovimiento.getMoneda().getTerm());
				monedaTO.setUact(cajaMovimiento.getMoneda().getUact());
				
				cajaMovimientoTO.setMoneda(monedaTO);
				
				cajaMovimientoTO.setObservaciones(cajaMovimiento.getObservaciones());
				
				cajaMovimientoTO.setFact(cajaMovimiento.getFact());
				cajaMovimientoTO.setId(cajaMovimiento.getId());
				cajaMovimientoTO.setTerm(cajaMovimiento.getTerm());
				cajaMovimientoTO.setUact(cajaMovimiento.getUact());
				
				result.add(cajaMovimientoTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(CajaMovimientoTO cajaMovimientoTO) {
		try {
			ICajaMovimientoBean iCajaMovimientoBean = lookupBean();
			
			CajaMovimiento cajaMovimiento = new CajaMovimiento();
			
			CajaTipoDocumento cajaTipoDocumento = new CajaTipoDocumento();
			cajaTipoDocumento.setId(cajaMovimientoTO.getCajaTipoDocumento().getId());
			
			cajaMovimiento.setCajaTipoDocumento(cajaTipoDocumento);
			
			cajaMovimiento.setDocumentoId(cajaMovimientoTO.getDocumentoId());
			cajaMovimiento.setImporte(cajaMovimientoTO.getImporte());
			
			Moneda moneda = new Moneda();
			moneda.setId(cajaMovimientoTO.getMoneda().getId());
			
			cajaMovimiento.setMoneda(moneda);
			
			cajaMovimiento.setObservaciones(cajaMovimientoTO.getObservaciones());
			
			cajaMovimiento.setFact(cajaMovimientoTO.getFact());
			cajaMovimiento.setTerm(cajaMovimientoTO.getTerm());
			cajaMovimiento.setUact(cajaMovimientoTO.getUact());
			
			iCajaMovimientoBean.save(cajaMovimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(CajaMovimientoTO cajaMovimientoTO) {
		try {
			ICajaMovimientoBean iCajaMovimientoBean = lookupBean();
			
			CajaMovimiento cajaMovimiento = new CajaMovimiento();
			cajaMovimiento.setId(cajaMovimientoTO.getId());
			
			iCajaMovimientoBean.remove(cajaMovimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(CajaMovimientoTO cajaMovimientoTO) {
		try {
			ICajaMovimientoBean iCajaMovimientoBean = lookupBean();
			
			CajaMovimiento cajaMovimiento = new CajaMovimiento();
			
			CajaTipoDocumento cajaTipoDocumento = new CajaTipoDocumento();
			cajaTipoDocumento.setId(cajaMovimientoTO.getCajaTipoDocumento().getId());
			
			cajaMovimiento.setCajaTipoDocumento(cajaTipoDocumento);
			
			cajaMovimiento.setDocumentoId(cajaMovimientoTO.getDocumentoId());
			cajaMovimiento.setImporte(cajaMovimientoTO.getImporte());
			
			Moneda moneda = new Moneda();
			moneda.setId(cajaMovimientoTO.getMoneda().getId());
			
			cajaMovimiento.setMoneda(moneda);
			
			cajaMovimiento.setObservaciones(cajaMovimientoTO.getObservaciones());
			
			cajaMovimiento.setId(cajaMovimientoTO.getId());
			cajaMovimiento.setFact(cajaMovimientoTO.getFact());
			cajaMovimiento.setTerm(cajaMovimientoTO.getTerm());
			cajaMovimiento.setUact(cajaMovimientoTO.getUact());
			
			iCajaMovimientoBean.update(cajaMovimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}