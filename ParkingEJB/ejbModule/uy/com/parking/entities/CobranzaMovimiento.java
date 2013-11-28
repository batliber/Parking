package uy.com.parking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cobranza_movimiento")
public class CobranzaMovimiento extends BaseEntity {

	private static final long serialVersionUID = 2178479548966413056L;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "importe")
	private Double importe;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cobranza_tipo_documento_id", nullable = false)
	private CobranzaTipoDocumento cobranzaTipoDocumento;

	@ManyToOne(optional = false)
	@JoinColumn(name = "proceso_id", nullable = false)
	private Proceso proceso;

	@ManyToOne(optional = false)
	@JoinColumn(name = "moneda_id", nullable = false)
	private Moneda moneda;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "servicio_id", nullable = false)
	private Servicio servicio;

	@ManyToOne
	@JoinColumn(name = "factura_id", nullable = true)
	private Factura factura;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public CobranzaTipoDocumento getCobranzaTipoDocumento() {
		return cobranzaTipoDocumento;
	}

	public void setCobranzaTipoDocumento(
			CobranzaTipoDocumento cobranzaTipoDocumento) {
		this.cobranzaTipoDocumento = cobranzaTipoDocumento;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}