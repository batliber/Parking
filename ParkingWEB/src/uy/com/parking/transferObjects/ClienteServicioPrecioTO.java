package uy.com.parking.transferObjects;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class ClienteServicioPrecioTO extends BaseTO {

	private Double precio;
	private Date validoHasta;
	private ClienteTO cliente;
	private ServicioTO servicio;
	private MonedaTO moneda;

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

	public ClienteTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}

	public ServicioTO getServicio() {
		return servicio;
	}

	public void setServicio(ServicioTO servicio) {
		this.servicio = servicio;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}
}