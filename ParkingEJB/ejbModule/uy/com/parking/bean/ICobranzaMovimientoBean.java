package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.CobranzaMovimiento;

@Remote
public interface ICobranzaMovimientoBean {

	public void procesarArchivoCobranza(String nombreArchivo);

	public Collection<CobranzaMovimiento> listSinFacturarByCliente(Cliente cliente);
}