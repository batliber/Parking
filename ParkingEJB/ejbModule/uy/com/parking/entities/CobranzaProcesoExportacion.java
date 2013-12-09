package uy.com.parking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cobranza_proceso_exportacion")
public class CobranzaProcesoExportacion extends BaseEntity {

	private static final long serialVersionUID = 5413990389757081114L;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "nombre_archivo")
	private String nombreArchivo;

	@Column(name = "numero_lote")
	private Long numeroLote;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Long numeroLote) {
		this.numeroLote = numeroLote;
	}
}