package uy.com.parking.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends BaseEntity {

	private static final long serialVersionUID = -7486761532528082043L;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToMany(mappedBy = "vehiculos")
	private Collection<Cliente> clientes;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}
}