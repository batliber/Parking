package uy.com.parking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "factura_linea")
public class FacturaLinea extends BaseEntity {

	private static final long serialVersionUID = -2682276448205843527L;

	@Column(name = "numero")
	private Long numero;

	@Column(name = "detalle")
	private String detalle;

	@Column(name = "importe_unitario")
	private Double importeUnitario;

	@Column(name = "unidades")
	private Double unidades;

	@Column(name = "importe_total")
	private Double importeTotal;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "servicio_id", nullable = false)
	private Servicio servicio;

	@ManyToOne(optional = false)
	@JoinColumn(name = "factura_id", nullable = false)
	private Factura factura;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Double getImporteUnitario() {
		return importeUnitario;
	}

	public void setImporteUnitario(Double importeUnitario) {
		this.importeUnitario = importeUnitario;
	}

	public Double getUnidades() {
		return unidades;
	}

	public void setUnidades(Double unidades) {
		this.unidades = unidades;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
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