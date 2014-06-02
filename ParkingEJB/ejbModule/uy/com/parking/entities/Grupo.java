package uy.com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seguridad_grupo")
public class Grupo extends BaseEntity {

	private static final long serialVersionUID = 8810363763218022658L;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "nivel")
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