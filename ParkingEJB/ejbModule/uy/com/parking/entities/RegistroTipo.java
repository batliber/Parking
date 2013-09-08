package uy.com.parking.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registro_tipo")
public class RegistroTipo extends BaseEntity {

	private static final long serialVersionUID = 2953078929016222093L;

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}