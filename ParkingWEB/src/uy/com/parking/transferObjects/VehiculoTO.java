package uy.com.parking.transferObjects;

import java.util.Collection;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class VehiculoTO extends BaseTO {

	private String matricula;
	private String descripcion;
	private DepartamentoTO departamento;
	private Collection<ClienteTO> clientes;

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

	public DepartamentoTO getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
	}
	
	public Collection<ClienteTO> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<ClienteTO> clientes) {
		this.clientes = clientes;
	}
}