package uy.com.parking.transferObjects;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class CobranzaMovimientoTO extends BaseTO {

	private Date fecha;
	private Double importe;
	private CobranzaTipoDocumentoTO cobranzaTipoDocumento;
	private ProcesoTO proceso;
	private MonedaTO moneda;
	private ClienteTO cliente;
	private ServicioTO servicio;
	private FacturaTO factura;

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

	public CobranzaTipoDocumentoTO getCobranzaTipoDocumento() {
		return cobranzaTipoDocumento;
	}

	public void setCobranzaTipoDocumento(
			CobranzaTipoDocumentoTO cobranzaTipoDocumento) {
		this.cobranzaTipoDocumento = cobranzaTipoDocumento;
	}

	public ProcesoTO getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoTO proceso) {
		this.proceso = proceso;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
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

	public FacturaTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaTO factura) {
		this.factura = factura;
	}
}