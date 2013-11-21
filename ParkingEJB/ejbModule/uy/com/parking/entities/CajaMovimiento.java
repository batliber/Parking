package uy.com.parking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "caja_movimiento")
public class CajaMovimiento extends BaseEntity {

	private static final long serialVersionUID = 3380012065408488513L;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "importe")
	private Double importe;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "documentoId")
	private Long documentoId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "caja_tipo_documento_id", nullable = false)
	private CajaTipoDocumento cajaTipoDocumento;

	@ManyToOne(optional = false)
	@JoinColumn(name = "moneda_id", nullable = false)
	private Moneda moneda;

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

	public CajaTipoDocumento getCajaTipoDocumento() {
		return cajaTipoDocumento;
	}

	public void setCajaTipoDocumento(CajaTipoDocumento cajaTipoDocumento) {
		this.cajaTipoDocumento = cajaTipoDocumento;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
}