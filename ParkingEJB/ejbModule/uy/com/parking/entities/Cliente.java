package uy.com.parking.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends BaseEntity {

	private static final long serialVersionUID = -8500119109940823099L;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "fecha_baja")
	private Date fechaBaja;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "vehiculo_cliente",
		joinColumns=
			@JoinColumn(name = "cliente_id", referencedColumnName = "id"),
		inverseJoinColumns=
			@JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
	)
	private Collection<Vehiculo> vehiculos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Collection<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Collection<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
}