package uy.com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "moneda")
public class Moneda extends BaseEntity {

	private static final long serialVersionUID = -6824314029732535934L;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "abreviacion")
	private String abreviacion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviacion() {
		return abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}
}