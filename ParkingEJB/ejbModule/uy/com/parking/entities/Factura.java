package uy.com.parking.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@Column(name = "rut")
	private String rut;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "domicilio")
	private String domicilio;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "importe_subtotal")
	private Double importeSubtotal;

	@Column(name = "importe_iva")
	private Double importeIVA;

	@Column(name = "importe_total")
	private Double importeTotal;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "moneda_id", nullable = false)
	private Moneda moneda;

	@OneToMany(mappedBy = "factura", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.REMOVE })
	private Set<FacturaLinea> facturaLineas;

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

	public Double getImporteSubtotal() {
		return importeSubtotal;
	}

	public void setImporteSubtotal(Double importeSubtotal) {
		this.importeSubtotal = importeSubtotal;
	}

	public Double getImporteIVA() {
		return importeIVA;
	}

	public void setImporteIVA(Double importeIVA) {
		this.importeIVA = importeIVA;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
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

	public Set<FacturaLinea> getFacturaLineas() {
		return facturaLineas;
	}

	public void setFacturaLineas(Set<FacturaLinea> facturaLineas) {
		this.facturaLineas = facturaLineas;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

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
}