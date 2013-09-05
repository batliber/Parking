package uy.com.parking.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura extends BaseEntity {

	private static final long serialVersionUID = 7926991811875600524L;

	@Column(name = "numero")
	private Long numero;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "importe")
	private Double importe;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "moneda_id", nullable = false)
	private Moneda moneda;

	@OneToMany(mappedBy = "factura")
	private Collection<FacturaLinea> facturaLineas;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Collection<FacturaLinea> getFacturaLineas() {
		return facturaLineas;
	}

	public void setFacturaLineas(Collection<FacturaLinea> facturaLineas) {
		this.facturaLineas = facturaLineas;
	}
}