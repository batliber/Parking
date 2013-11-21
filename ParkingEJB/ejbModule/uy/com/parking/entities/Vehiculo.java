package uy.com.parking.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends BaseEntity {

	private static final long serialVersionUID = -7486761532528082043L;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "departamento_id", nullable = false)
	private Departamento departamento;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "vehiculo_cliente", joinColumns = @JoinColumn(name = "vehiculo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"))
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}
}