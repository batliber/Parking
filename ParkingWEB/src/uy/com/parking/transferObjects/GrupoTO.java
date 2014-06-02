package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class GrupoTO extends BaseTO {

	private String descripcion;
	private Long nivel;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getNivel() {
		return nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}
}