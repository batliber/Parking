package uy.com.parking.dwr;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.entities.ServicioTipo;
import uy.com.parking.transferObjects.ServicioTipoTO;

@RemoteProxy
public class ServicioTipoDWR {

	public static ServicioTipo transform(ServicioTipoTO servicioTipoTO) {
		ServicioTipo servicioTipo = new ServicioTipo();
		
		servicioTipo.setDescripcion(servicioTipoTO.getDescripcion());
		
		servicioTipo.setFact(servicioTipoTO.getFact());
		servicioTipo.setId(servicioTipoTO.getId());
		servicioTipo.setTerm(servicioTipoTO.getTerm());
		servicioTipo.setUact(servicioTipoTO.getUact());
		
		return servicioTipo;
	}
	
	public static ServicioTipoTO transform(ServicioTipo servicioTipo) {
		ServicioTipoTO servicioTipoTO = new ServicioTipoTO();
		
		servicioTipoTO.setDescripcion(servicioTipo.getDescripcion());
		
		servicioTipoTO.setFact(servicioTipo.getFact());
		servicioTipoTO.setId(servicioTipo.getId());
		servicioTipoTO.setTerm(servicioTipo.getTerm());
		servicioTipoTO.setUact(servicioTipo.getUact());
		
		return servicioTipoTO;
	}
}