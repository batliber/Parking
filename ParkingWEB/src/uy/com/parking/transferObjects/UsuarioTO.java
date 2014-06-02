package uy.com.parking.transferObjects;

import java.util.Collection;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class UsuarioTO extends BaseTO {

	private String login;
	private String contrasena;
	private String nombre;
	private Collection<GrupoTO> grupos;

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

	public Collection<GrupoTO> getGrupos() {
		return grupos;
	}

	public void setGrupos(Collection<GrupoTO> grupos) {
		this.grupos = grupos;
	}
}