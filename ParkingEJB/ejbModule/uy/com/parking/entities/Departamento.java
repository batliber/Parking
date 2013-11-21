package uy.com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento extends BaseEntity {

	private static final long serialVersionUID = -3630951846286180214L;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "prefijo")
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