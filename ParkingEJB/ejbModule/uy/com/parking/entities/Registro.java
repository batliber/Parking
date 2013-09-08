package uy.com.parking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registro")
public class Registro extends BaseEntity {

	private static final long serialVersionUID = 9176505273539979065L;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "matricula")
	private String matricula;

	@ManyToOne(optional = false)
	@JoinColumn(name = "registro_tipo_id", nullable = false)
	private RegistroTipo registroTipo;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public RegistroTipo getRegistroTipo() {
		return registroTipo;
	}

	public void setRegistroTipo(RegistroTipo registroTipo) {
		this.registroTipo = registroTipo;
	}
}