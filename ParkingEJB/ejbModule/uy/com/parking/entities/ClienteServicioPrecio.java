package uy.com.parking.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente_servicio_precio")
public class ClienteServicioPrecio extends BaseEntity {

	private static final long serialVersionUID = -3481616900019566253L;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "valido_hasta")
	private Date validoHasta;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "servicio_id", nullable = false)
	private Servicio servicio;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "moneda_id", nullable = false)
	private Moneda moneda;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
}