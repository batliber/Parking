package uy.com.parking.entities;

import java.io.Serializable;

public class Archivo implements Serializable {

	private static final long serialVersionUID = -4569594899779116168L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}