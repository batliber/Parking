package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class CajaTipoDocumentoTO extends BaseTO {

	private String descripcion;
	private Long signo;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getSigno() {
		return signo;
	}

	public void setSigno(Long signo) {
		this.signo = signo;
	}
}