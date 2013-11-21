package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.ClienteServicioPrecio;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;

@Stateless
public class ClienteServicioPrecioBean implements IClienteServicioPrecioBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<ClienteServicioPrecio> list() {
		Collection<ClienteServicioPrecio> result = new LinkedList<ClienteServicioPrecio>();
		
		try {
			Query query = entityManager.createQuery("SELECT csp FROM ClienteServicioPrecio csp");
			
			for (Object object : query.getResultList()) {
				result.add((ClienteServicioPrecio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Collection<ClienteServicioPrecio> listVigentes() {
		Collection<ClienteServicioPrecio> result = new LinkedList<ClienteServicioPrecio>();
		
		try {
			Query query = entityManager.createQuery("SELECT csp FROM ClienteServicioPrecio csp WHERE csp.validoHasta = null");
			
			for (Object object : query.getResultList()) {
				result.add((ClienteServicioPrecio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ClienteServicioPrecio getById(Long id) {
		ClienteServicioPrecio result = null;
		
		try {
			result = entityManager.find(ClienteServicioPrecio.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ClienteServicioPrecio getPrecioVigenteByClienteServicioMoneda(
			Cliente cliente, Servicio servicio, Moneda moneda) {
		ClienteServicioPrecio result = null;
		
		try {
			Query query = 
				entityManager.createQuery(
					"SELECT csp FROM ClienteServicioPrecio csp"
					+ " WHERE csp.cliente.id = :clienteId"
					+ " AND csp.servicio.id = :servicioId"
					+ " AND csp.moneda.id = :monedaId"
					+ " AND csp.validoHasta = null");
			query.setParameter("clienteId", cliente.getId());
			query.setParameter("servicioId", servicio.getId());
			query.setParameter("monedaId", moneda.getId());
			
			result = (ClienteServicioPrecio) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(ClienteServicioPrecio clienteServicioPrecio) {
		try {
			entityManager.persist(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(ClienteServicioPrecio clienteServicioPrecio) {
		try {
			ClienteServicioPrecio managedClienteServicioPrecio = entityManager.find(ClienteServicioPrecio.class, clienteServicioPrecio.getId());
			
			entityManager.remove(managedClienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(ClienteServicioPrecio clienteServicioPrecio) {
		try {
			entityManager.merge(clienteServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}