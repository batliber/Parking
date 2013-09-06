package uy.com.parking.transferObjects;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ServicioPrecioTO extends BaseTO {

	private Double precio;
	private Date validoHasta;
	private MonedaTO moneda;
	private ServicioTO servicio;

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}

	public void setValidoHasta(Date validoHasta) {
		this.validoHasta = validoHasta;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}

	public ServicioTO getServicio() {
		return servicio;
	}

	public void setServicio(ServicioTO servicio) {
		this.servicio = servicio;
	}
}