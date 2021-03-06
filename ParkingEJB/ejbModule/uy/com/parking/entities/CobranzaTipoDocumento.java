package uy.com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cobranza_tipo_documento")
public class CobranzaTipoDocumento extends BaseEntity {

	private static final long serialVersionUID = 2687046282573547928L;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "signo")
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