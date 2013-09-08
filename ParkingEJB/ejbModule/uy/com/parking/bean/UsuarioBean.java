package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Usuario;

@Stateless
public class UsuarioBean implements IUsuarioBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<Usuario> list() {
		Collection<Usuario> result = new LinkedList<Usuario>();
		
		try {
			Query query = entityManager.createQuery("SELECT u FROM Usuario u");
			
			for (Object object : query.getResultList()) {
				result.add((Usuario) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(Usuario usuario) {
		try {
			entityManager.persist(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Usuario usuario) {
		try {
			Usuario managedUsuario = entityManager.find(Usuario.class, usuario.getId());
			
			entityManager.remove(managedUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Usuario usuario) {
		try {
			entityManager.merge(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}