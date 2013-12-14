package uy.com.parking.transferObjects;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ClienteTO extends BaseTO {

	private String nombre;
	private String apellido;
	private String documento;
	private String domicilio;
	private String telefono;
	private Date fechaAlta;
	private Date fechaBaja;
	private Collection<VehiculoTO> vehiculos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Collection<VehiculoTO> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Collection<VehiculoTO> vehiculos) {
		this.vehiculos = vehiculos;
	}
}