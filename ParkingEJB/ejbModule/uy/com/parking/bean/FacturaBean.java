package uy.com.parking.bean;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import uy.com.parking.entities.CajaMovimiento;
import uy.com.parking.entities.CajaTipoDocumento;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.CobranzaMovimiento;
import uy.com.parking.entities.Factura;
import uy.com.parking.entities.FacturaLinea;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Registro;
import uy.com.parking.entities.RegistroTipo;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioPrecio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.util.Constantes;

@Stateless
public class FacturaBean implements IFacturaBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;

	@EJB
	private IRegistroBean iRegistroBean;

	@EJB
	private IVehiculoBean iVehiculoBean;

	@EJB
	private IClienteBean iClienteBean;

	@EJB
	private IServicioBean iServicioBean;

	@EJB
	private IMonedaBean iMonedaBean;

	@EJB
	private IServicioPrecioBean iServicioPrecioBean;

	@EJB
	private ICajaTipoDocumentoBean iCajaTipoDocumentoBean;

	@EJB
	private ICajaMovimientoBean iCajaMovimientoBean;

	public Collection<Factura> list() {
		Collection<Factura> result = new LinkedList<Factura>();

		try {
			Query query = entityManager.createQuery("SELECT f FROM Factura f");

			for (Object object : query.getResultList()) {
				result.add((Factura) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Factura getById(Long id) {
		Factura result = null;

		try {
			result = entityManager.find(Factura.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Factura getByNumero(Long numero) {
		Factura result = null;

		try {
			TypedQuery<Factura> query = entityManager.createQuery(
					"SELECT f FROM Factura f" + " WHERE f.numero = :numero",
					Factura.class);
			query.setParameter("numero", numero);

			List<Factura> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void save(Factura factura) {
		try {
			Query query = entityManager
				.createQuery("SELECT MAX(f.numero) FROM Factura f");

			Long maxNumero = (Long) query.getSingleResult();

			if (maxNumero != null) {
				factura.setNumero(maxNumero + 1);
			} else {
				factura.setNumero(new Long(1));
			}

			entityManager.persist(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Factura saveAndCloseRegistro(Factura factura, String matricula) {
		Factura result = factura;

		try {
			Registro registro = new Registro();
			registro.setFecha(new Date());
			registro.setMatricula(matricula);

			RegistroTipo registroTipo = new RegistroTipo();
			registroTipo.setId(new Long(2));

			registro.setRegistroTipo(registroTipo);

			registro.setFact(new Date());
			registro.setTerm(new Long(1));
			registro.setUact(new Long(1));

			this.save(result);

			iRegistroBean.save(registro);

			CajaMovimiento cajaMovimiento = new CajaMovimiento();

			CajaTipoDocumento cajaTipoDocumento = iCajaTipoDocumentoBean
					.getById(new Long(1));

			cajaMovimiento.setCajaTipoDocumento(cajaTipoDocumento);

			cajaMovimiento.setDocumentoId(result.getId());
			cajaMovimiento.setFecha(new Date());
			cajaMovimiento.setImporte(factura.getImporteTotal());
			cajaMovimiento.setMoneda(result.getMoneda());
			cajaMovimiento.setObservaciones("");

			cajaMovimiento.setFact(new Date());
			cajaMovimiento.setTerm(new Long(1));
			cajaMovimiento.setUact(new Long(1));

			iCajaMovimientoBean.save(cajaMovimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Factura generateFacturaByMatricula(String matricula) {
		Factura factura = null;

		try {
			// Creación de Factura a guardar
			factura = new Factura();

			// Recuperación de datos de Vehículo
			Vehiculo vehiculo = iVehiculoBean.getByMatricula(matricula);

			Cliente cliente = null;
			if (vehiculo != null) {
				if (vehiculo.getClientes().size() > 0) {
					cliente = vehiculo.getClientes().toArray(new Cliente[] {})[0];
				} else {
					// Recuperación de datos de Cliente
					cliente = iClienteBean.getById(new Long(1));
				}
			} else {
				// Recuperación de datos de Cliente
				cliente = iClienteBean.getById(new Long(1));
			}

			factura.setCliente(cliente);

			factura.setFact(new Date());

			// Variables para cálculo de importes
			Double importeIVA = new Double(0);
			Double importeSubtotal = new Double(0);
			Double importeTotal = new Double(0);

			Set<FacturaLinea> facturaLineas = new HashSet<FacturaLinea>();

			// Creación de FacturaLínea a guardar
			FacturaLinea facturaLinea = new FacturaLinea();

			// Recuperación de datos de Servicio
			Servicio servicio = iServicioBean.getById(new Long(1));

			facturaLinea.setServicio(servicio);

			facturaLinea.setDetalle(servicio.getDescripcion());

			facturaLinea.setFact(new Date());
			facturaLinea.setFactura(factura);

			// Recuperación de datos de Moneda
			Moneda moneda = iMonedaBean.getById(new Long(1));

			factura.setMoneda(moneda);

			facturaLinea.setNumero(new Long(1));

			// Recuperación de datos de ServicioPrecio
			ServicioPrecio servicioPrecio = iServicioPrecioBean
					.getPrecioVigenteByServicioMoneda(servicio, moneda);

			facturaLinea.setTerm(new Long(1));
			facturaLinea.setUact(new Long(1));

			// Recuperación de datos de Registro
			Registro registro = iRegistroBean.getLastByMatricula(matricula);

			// Cálculo de unidades
			GregorianCalendar gregorianCalendarEntrada = new GregorianCalendar();
			gregorianCalendarEntrada.setTime(registro.getFecha());

			GregorianCalendar gregorianCalendarSalida = new GregorianCalendar();

			long millisEntrada = gregorianCalendarEntrada.getTimeInMillis();
			long millisSalida = gregorianCalendarSalida.getTimeInMillis();

			double unidades = ((millisSalida - millisEntrada) / Constantes.__MILISEGUNDOS_HORA)
					+ (((millisSalida - millisEntrada) % Constantes.__MILISEGUNDOS_HORA) / Constantes.__MILISEGUNDOS_MINUTO)
					* 0.01;

			facturaLinea.setUnidades(Math.max(
					Constantes.__MINIMO_UNIDADES_PARKING, unidades));

			// Cálculo de importes
			facturaLinea.setImporteUnitario(servicioPrecio.getPrecio());
			facturaLinea.setImporteTotal(facturaLinea.getImporteUnitario()
					* facturaLinea.getUnidades());

			importeSubtotal += facturaLinea.getImporteTotal();

			importeIVA = importeSubtotal * Constantes.__IMPUESTO_IVA;
			importeTotal = importeSubtotal + importeIVA;

			facturaLineas.add(facturaLinea);

			factura.setFacturaLineas(facturaLineas);

			factura.setFecha(new Date());
			factura.setImporteIVA(importeIVA);
			factura.setImporteSubtotal(importeSubtotal);
			factura.setImporteTotal(importeTotal);

			// Query query =
			// entityManager.createQuery("SELECT MAX(f.numero) FROM Factura f");
			//
			// Long maxId = (Long) query.getSingleResult();
			//
			// if (maxId != null) {
			// factura.setNumero(maxId + 1);
			// } else {
			// factura.setNumero(new Long(1));
			// }

			factura.setTerm(new Long(1));
			factura.setUact(new Long(1));

			// entityManager.persist(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return factura;
	}

	public Factura facturarCobranzaMovimientos(Factura factura,
			Collection<CobranzaMovimiento> cobranzaMovimientos) {
		Factura result = factura;

		try {
			this.save(result);
			
			for (CobranzaMovimiento cobranzaMovimiento : cobranzaMovimientos) {
				CobranzaMovimiento cobranzaMovimientoManaged = 
					entityManager.find(CobranzaMovimiento.class, cobranzaMovimiento.getId());
				
				cobranzaMovimientoManaged.setFactura(result);
				
				entityManager.merge(cobranzaMovimientoManaged);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void remove(Factura factura) {
		try {
			Factura managedFactura = entityManager.find(Factura.class,
					factura.getId());

			entityManager.remove(managedFactura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Factura factura) {
		try {
			entityManager.merge(factura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}