package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ServicioTipoTO extends BaseTO {

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}