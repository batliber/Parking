package uy.com.parking.transferObjects;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class FacturaTO extends BaseTO {

	private Long numero;
	private Date fecha;
	private String documento;
	private String nombre;
	private String apellido;
	private String domicilio;
	private String telefono;
	private Double importeSubtotal;
	private Double importeIVA;
	private Double importeTotal;
	private ClienteTO cliente;
	private MonedaTO moneda;
	private Collection<FacturaLineaTO> facturaLineas;

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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public ClienteTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}

	public Collection<FacturaLineaTO> getFacturaLineas() {
		return facturaLineas;
	}

	public void setFacturaLineas(Collection<FacturaLineaTO> facturaLineas) {
		this.facturaLineas = facturaLineas;
	}
}