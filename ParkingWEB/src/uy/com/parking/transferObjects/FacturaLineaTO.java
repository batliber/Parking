package uy.com.parking.transferObjects;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class FacturaLineaTO extends BaseTO {

	private Long numero;
	private String detalle;
	private Double importeUnitario;
	private Double unidades;
	private Double importeTotal;
	private ServicioTO servicio;
	private FacturaTO factura;

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

	public ServicioTO getServicio() {
		return servicio;
	}

	public void setServicio(ServicioTO servicio) {
		this.servicio = servicio;
	}

	public FacturaTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaTO factura) {
		this.factura = factura;
	}
}