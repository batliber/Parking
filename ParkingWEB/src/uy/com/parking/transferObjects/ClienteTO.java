package uy.com.parking.transferObjects;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ClienteTO extends BaseTO {

	private String nombre;
	private String documento;
	private String domicilio;
	private String telefono;
	private Date fechaBaja;
	private Collection<VehiculoTO> vehiculos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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