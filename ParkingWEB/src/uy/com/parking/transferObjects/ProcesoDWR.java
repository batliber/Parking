package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.entities.Proceso;

@RemoteProxy
public class ProcesoDWR {

	public static Proceso transform(ProcesoTO procesoTO, boolean transformCollections) {
		Proceso proceso = new Proceso();

		proceso.setFechaFin(procesoTO.getFechaFin());
		proceso.setFechaInicio(procesoTO.getFechaInicio());
		proceso.setNombreArchivo(procesoTO.getNombreArchivo());

		proceso.setFact(procesoTO.getFact());
		proceso.setId(procesoTO.getId());
		proceso.setTerm(procesoTO.getTerm());
		proceso.setUact(procesoTO.getUact());

		return proceso;
	}

	public static ProcesoTO transform(Proceso proceso, boolean transformCollections) {
		ProcesoTO procesoTO = new ProcesoTO();

		procesoTO.setFechaFin(proceso.getFechaFin());
		procesoTO.setFechaInicio(proceso.getFechaInicio());
		procesoTO.setNombreArchivo(proceso.getNombreArchivo());

		procesoTO.setFact(proceso.getFact());
		procesoTO.setId(proceso.getId());
		procesoTO.setTerm(proceso.getTerm());
		procesoTO.setUact(proceso.getUact());

		return procesoTO;
	}
}