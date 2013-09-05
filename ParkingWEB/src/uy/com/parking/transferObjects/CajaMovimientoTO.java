package uy.com.parking.transferObjects;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class CajaMovimientoTO extends BaseTO {

	private Date fecha;
	private Double importe;
	private String observaciones;
	private Long documentoId;
	private CajaTipoDocumentoTO cajaTipoDocumento;
	private MonedaTO moneda;

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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}

	public CajaTipoDocumentoTO getCajaTipoDocumento() {
		return cajaTipoDocumento;
	}

	public void setCajaTipoDocumento(CajaTipoDocumentoTO cajaTipoDocumento) {
		this.cajaTipoDocumento = cajaTipoDocumento;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}
}