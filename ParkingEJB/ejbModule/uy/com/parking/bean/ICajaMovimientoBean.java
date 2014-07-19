package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.CajaMovimiento;

@Remote
public interface ICajaMovimientoBean {

	public Collection<CajaMovimiento> list();
	
	public CajaMovimiento getUltimoMovimientoByUsuario(Long usuarioId);
	
	public void save(CajaMovimiento cajaMovimiento);
	
	public void remove(CajaMovimiento cajaMovimiento);
	
	public void update(CajaMovimiento cajaMovimiento);
}