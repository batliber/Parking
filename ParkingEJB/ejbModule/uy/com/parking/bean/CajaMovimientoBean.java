package uy.com.parking.bean;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import uy.com.parking.entities.CajaMovimiento;

@Stateless
public class CajaMovimientoBean implements ICajaMovimientoBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	public Collection<CajaMovimiento> list() {
		return null;
	}
	
	public CajaMovimiento getUltimoMovimientoByUsuario(Long usuarioId) {
		CajaMovimiento result = null;
		
		try {
			TypedQuery<CajaMovimiento> query = 
				entityManager.createQuery(
					"SELECT cm"
					+ " FROM CajaMovimiento cm"
					+ " WHERE cm.uact = :uact"
					+ " ORDER BY cm.fact DESC", 
					CajaMovimiento.class
				);
			query.setParameter("uact", usuarioId);
			query.setMaxResults(1);
			
			List<CajaMovimiento> resultList = query.getResultList();
			if (resultList.size() > 0) {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(CajaMovimiento cajaMovimiento) {
		
	}

	public void remove(CajaMovimiento cajaMovimiento) {
		
	}

	public void update(CajaMovimiento cajaMovimiento) {
		
	}
}