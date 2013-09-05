package uy.com.parking.transferObjects;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ClienteTO extends BaseTO {

	private String nombre;
	private Date fechaBaja;
	private Collection<VehiculoTO> vehiculos;

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

	public Collection<VehiculoTO> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Collection<VehiculoTO> vehiculos) {
		this.vehiculos = vehiculos;
	}
}