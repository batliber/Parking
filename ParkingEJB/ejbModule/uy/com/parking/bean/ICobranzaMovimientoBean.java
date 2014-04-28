package uy.com.parking.bean;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import uy.com.parking.entities.Archivo;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.CobranzaMovimiento;

@Remote
public interface ICobranzaMovimientoBean {

	public void procesarArchivoCobranza(String nombreArchivo);

	public String generarArchivoCobranzaAbitabByFecha(Date fecha);
	
	public void generarCobranzaMovimientosByFecha(Date fecha);
	
	public void deshacerCobranzaMovimientosByFecha(Date fecha);
	
	public void deshacerUltimaImportacion();
	
	public Collection<CobranzaMovimiento> listDeudas();
	
	public Collection<CobranzaMovimiento> listSinFacturarByCliente(Cliente cliente);
	
	public Collection<Archivo> listArchivos();
}