package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.CobranzaMovimientoBean;
import uy.com.parking.bean.ICobranzaMovimientoBean;
import uy.com.parking.entities.CobranzaMovimiento;
import uy.com.parking.transferObjects.ClienteTO;
import uy.com.parking.transferObjects.CobranzaMovimientoTO;
import uy.com.parking.transferObjects.ProcesoDWR;

@RemoteProxy
public class CobranzaMovimientoDWR {

	private ICobranzaMovimientoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = CobranzaMovimientoBean.class.getSimpleName();
		String remoteInterfaceName = ICobranzaMovimientoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (ICobranzaMovimientoBean) context.lookup(lookupName);
	}
	
	public Collection<CobranzaMovimientoTO> listSinFacturarByCliente(ClienteTO clienteTO) {
		Collection<CobranzaMovimientoTO> result = new LinkedList<CobranzaMovimientoTO>();
		
		try {
			ICobranzaMovimientoBean iCobranzaMovimientoBean = lookupBean();
			
			for (CobranzaMovimiento cobranzaMovimiento : 
				iCobranzaMovimientoBean.listSinFacturarByCliente(
					ClienteDWR.transform(clienteTO, false))) {
				result.add(transform(cobranzaMovimiento));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static CobranzaMovimiento transform(CobranzaMovimientoTO cobranzaMovimientoTO) {
		CobranzaMovimiento cobranzaMovimiento = new CobranzaMovimiento();
		
		cobranzaMovimiento.setFecha(cobranzaMovimientoTO.getFecha());
		cobranzaMovimiento.setImporte(cobranzaMovimientoTO.getImporte());
		
		cobranzaMovimiento.setCliente(ClienteDWR.transform(cobranzaMovimientoTO.getCliente(), false));
		
		cobranzaMovimiento.setCobranzaTipoDocumento(
			CobranzaTipoDocumentoDWR.transform(
				cobranzaMovimientoTO.getCobranzaTipoDocumento(), false
			)
		);
		
		cobranzaMovimiento.setMoneda(MonedaDWR.transform(cobranzaMovimientoTO.getMoneda(), false));
		
		cobranzaMovimiento.setProceso(ProcesoDWR.transform(cobranzaMovimientoTO.getProceso(), false));
		
		cobranzaMovimiento.setServicio(ServicioDWR.transform(cobranzaMovimientoTO.getServicio(), false));
	
		cobranzaMovimiento.setFact(cobranzaMovimientoTO.getFact());
		cobranzaMovimiento.setId(cobranzaMovimientoTO.getId());
		cobranzaMovimiento.setTerm(cobranzaMovimientoTO.getTerm());
		cobranzaMovimiento.setUact(cobranzaMovimientoTO.getUact());
		
		return cobranzaMovimiento;
	}
	
	public static CobranzaMovimientoTO transform(CobranzaMovimiento cobranzaMovimiento) {
		CobranzaMovimientoTO cobranzaMovimientoTO = new CobranzaMovimientoTO();
		
		cobranzaMovimientoTO.setFecha(cobranzaMovimiento.getFecha());
		cobranzaMovimientoTO.setImporte(cobranzaMovimiento.getImporte());
		
		cobranzaMovimientoTO.setCliente(ClienteDWR.transform(cobranzaMovimiento.getCliente(), false));
		
		cobranzaMovimientoTO.setCobranzaTipoDocumento(
			CobranzaTipoDocumentoDWR.transform(
				cobranzaMovimiento.getCobranzaTipoDocumento(), false
			)
		);
		
		cobranzaMovimientoTO.setMoneda(MonedaDWR.transform(cobranzaMovimiento.getMoneda(), false));
		
		cobranzaMovimientoTO.setProceso(ProcesoDWR.transform(cobranzaMovimiento.getProceso(), false));
		
		cobranzaMovimientoTO.setServicio(ServicioDWR.transform(cobranzaMovimiento.getServicio(), false));
	
		cobranzaMovimientoTO.setFact(cobranzaMovimiento.getFact());
		cobranzaMovimientoTO.setId(cobranzaMovimiento.getId());
		cobranzaMovimientoTO.setTerm(cobranzaMovimiento.getTerm());
		cobranzaMovimientoTO.setUact(cobranzaMovimiento.getUact());
		
		return cobranzaMovimientoTO;
	}
}