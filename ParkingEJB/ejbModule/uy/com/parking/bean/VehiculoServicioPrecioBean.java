package uy.com.parking.bean;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.entities.VehiculoServicioPrecio;

@Stateless
public class VehiculoServicioPrecioBean implements IVehiculoServicioPrecioBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<VehiculoServicioPrecio> list() {
		Collection<VehiculoServicioPrecio> result = new LinkedList<VehiculoServicioPrecio>();
		
		try {
			Query query = entityManager.createQuery("SELECT vsp FROM VehiculoServicioPrecio vsp");
			
			for (Object object : query.getResultList()) {
				result.add((VehiculoServicioPrecio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Collection<VehiculoServicioPrecio> listVigentes() {
		Collection<VehiculoServicioPrecio> result = new LinkedList<VehiculoServicioPrecio>();
		
		try {
			Query query = entityManager.createQuery(
				"SELECT vsp FROM VehiculoServicioPrecio vsp WHERE vsp.validoHasta = null"
			);
			
			for (Object object : query.getResultList()) {
				result.add((VehiculoServicioPrecio) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public VehiculoServicioPrecio getById(Long id) {
		VehiculoServicioPrecio result = null;
		
		try {
			result = entityManager.find(VehiculoServicioPrecio.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public VehiculoServicioPrecio getPrecioVigenteByVehiculoServicioMoneda(
			Vehiculo vehiculo, Servicio servicio, Moneda moneda) {
		VehiculoServicioPrecio result = null;
		
		try {
			TypedQuery<VehiculoServicioPrecio> query = 
				entityManager.createQuery(
					"SELECT vsp FROM VehiculoServicioPrecio vsp"
						+ " WHERE vsp.vehiculo.id = :vehiculoId"
						+ " AND vsp.servicio.id = :servicioId"
						+ " AND vsp.moneda.id = :monedaId"
						+ " AND vsp.validoHasta = null",
					VehiculoServicioPrecio.class
				);
			query.setParameter("vehiculoId", vehiculo.getId());
			query.setParameter("servicioId", servicio.getId());
			query.setParameter("monedaId", moneda.getId());
			
			List<VehiculoServicioPrecio> resultList = query.getResultList();
			
			if (resultList.size() > 0) {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Collection<VehiculoServicioPrecio> listVigentesByVehiculo(Vehiculo vehiculo) {
		Collection<VehiculoServicioPrecio> result = new LinkedList<VehiculoServicioPrecio>();
		
		try {
			TypedQuery<VehiculoServicioPrecio> query = 
				entityManager.createQuery(
					"SELECT vsp FROM VehiculoServicioPrecio vsp"
					+ " WHERE vsp.vehiculo.id = :vehiculoId"
					+ " AND vsp.validoHasta = null",
					VehiculoServicioPrecio.class
				);
			query.setParameter("vehiculoId", vehiculo.getId());
			
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void save(VehiculoServicioPrecio vehiculoServicioPrecio) {
		try {
			entityManager.persist(vehiculoServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(VehiculoServicioPrecio vehiculoServicioPrecio) {
		try {
			VehiculoServicioPrecio managedVehiculoServicioPrecio = 
				entityManager.find(VehiculoServicioPrecio.class, vehiculoServicioPrecio.getId());
			
			entityManager.remove(managedVehiculoServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(VehiculoServicioPrecio vehiculoServicioPrecio) {
		try {
			entityManager.merge(vehiculoServicioPrecio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}