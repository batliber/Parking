package uy.com.parking.transferObjects;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class RegistroTO extends BaseTO {

	private Date fecha;
	private String matricula;
	private RegistroTipoTO registroTipo;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public RegistroTipoTO getRegistroTipo() {
		return registroTipo;
	}

	public void setRegistroTipo(RegistroTipoTO registroTipo) {
		this.registroTipo = registroTipo;
	}
}