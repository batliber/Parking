package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Usuario;

@Remote
public interface IUsuarioBean {

	public Collection<Usuario> list();
	
	public Usuario getById(Long id);
	
	public Usuario getByLogin(String login);
	
	public void save(Usuario usuario);
	
	public void remove(Usuario usuario);
	
	public void update(Usuario usuario);
}