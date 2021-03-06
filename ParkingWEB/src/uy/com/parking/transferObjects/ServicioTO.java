package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ServicioTO extends BaseTO {

	private String descripcion;
	private ServicioTipoTO servicioTipo;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ServicioTipoTO getServicioTipo() {
		return servicioTipo;
	}

	public void setServicioTipo(ServicioTipoTO servicioTipo) {
		this.servicioTipo = servicioTipo;
	}
}