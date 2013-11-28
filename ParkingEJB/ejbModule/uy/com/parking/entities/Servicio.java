package uy.com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servicio")
public class Servicio extends BaseEntity {

	private static final long serialVersionUID = -6351544982185834371L;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "servicio_tipo_id", nullable = false)
	private ServicioTipo servicioTipo;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ServicioTipo getServicioTipo() {
		return servicioTipo;
	}

	public void setServicioTipo(ServicioTipo servicioTipo) {
		this.servicioTipo = servicioTipo;
	}
}