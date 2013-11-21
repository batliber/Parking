package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class DepartamentoTO extends BaseTO {

	private String nombre;
	private String prefijo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
}