package uy.com.parking.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "seguridad_usuario")
public class Usuario extends BaseEntity {

	private static final long serialVersionUID = 5083694247301452198L;

	@Column(name = "login")
	private String login;

	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "fecha_baja")
	private Date fechaBaja;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "seguridad_usuario_grupo", joinColumns = @JoinColumn(name = "seguridad_usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "seguridad_grupo_id", referencedColumnName = "id"))
	private Collection<Grupo> grupos;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Collection<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Collection<Grupo> grupos) {
		this.grupos = grupos;
	}
}