package uy.com.parking.bean;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import uy.com.parking.entities.CobranzaTipoDocumento;
import uy.com.parking.entities.Factura;
import uy.com.parking.entities.FacturaLinea;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Registro;
import uy.com.parking.entities.RegistroTipo;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioPrecio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.util.Configuration;
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
			TypedQuery<Factura> query = 
				entityManager.createQuery(
					"SELECT f FROM Factura f",
					Factura.class
				);

			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Collection<Factura> listDesdeHasta(Date desde, Date hasta) {
		Collection<Factura> result = new LinkedList<Factura>();

		try {
			TypedQuery<Factura> query = 
				entityManager.createQuery(
					"SELECT f"
					+ " FROM Factura f"
					+ " WHERE f.fecha >= :desde"
					+ " AND f.fecha <= :hasta"
					+ " ORDER BY f.numero DESC",
					Factura.class
				);
			query.setParameter("desde", desde);
			query.setParameter("hasta", hasta);

			result = query.getResultList();
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

	public String exportarListDesdeHastaAExcel(Date desde, Date hasta) {
		String result = null;
		
		PrintWriter printWriter = null;
		
		try {
			Collection<Factura> facturas = this.listDesdeHasta(desde, hasta);
			
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gregorianCalendar.set(GregorianCalendar.MINUTE, 0);
			gregorianCalendar.set(GregorianCalendar.SECOND, 0);
			gregorianCalendar.set(GregorianCalendar.MILLISECOND, 0);
			
			Date hoy = GregorianCalendar.getInstance().getTime();
			
			SimpleDateFormat formatNombreArchivo = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			
			// Cálculo de nombre de archivo
			String nombreArchivo = 
				formatNombreArchivo.format(hoy)
				+ "."
				+ "csv";
			
			printWriter = 
				new PrintWriter(
					new FileWriter(
						Configuration.getInstance().getProperty("exportacion.carpeta") + nombreArchivo
					)
				);
			
			String cabezal =
				"Número"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Fecha"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Cliente"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Moneda"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Subtotal"
				+ Constantes.__SEPARADOR_CAMPO
				+ "IVA"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Total"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Anulada";
			
			printWriter.println(cabezal);
			
			for (Factura factura : facturas) {
				String linea =
					factura.getNumero()
					+ Constantes.__SEPARADOR_CAMPO
					+ format.format(factura.getFecha())
					+ Constantes.__SEPARADOR_CAMPO
					+ factura.getDocumento() + " - "
					+ factura.getApellido() + ", " + factura.getNombre()
					+ Constantes.__SEPARADOR_CAMPO
					+ factura.getMoneda().getDescripcion()
					+ Constantes.__SEPARADOR_CAMPO
					+ decimalFormat.format(factura.getImporteSubtotal())
					+ Constantes.__SEPARADOR_CAMPO
					+ decimalFormat.format(factura.getImporteIVA())
					+ Constantes.__SEPARADOR_CAMPO
					+ decimalFormat.format(factura.getImporteTotal())
					+ Constantes.__SEPARADOR_CAMPO
					+ (factura.getAnulada() != null && factura.getAnulada() ? "Si" : "No");
				
				printWriter.println(linea);
			}
			
			result = nombreArchivo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
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
			
			Date hoy = GregorianCalendar.getInstance().getTime();
			
			for (CobranzaMovimiento cobranzaMovimiento : cobranzaMovimientos) {
				if (cobranzaMovimiento.getId() != null) {
					CobranzaMovimiento cobranzaMovimientoManaged = 
						entityManager.find(CobranzaMovimiento.class, cobranzaMovimiento.getId());
					
					cobranzaMovimientoManaged.setFactura(result);
					
					cobranzaMovimientoManaged.setUact(new Long(1));
					cobranzaMovimientoManaged.setFact(hoy);
					cobranzaMovimientoManaged.setTerm(new Long(1));
					
					entityManager.merge(cobranzaMovimientoManaged);
				} else {
					cobranzaMovimiento.setFactura(result);
					
					cobranzaMovimiento.setUact(new Long(1));
					cobranzaMovimiento.setFact(hoy);
					cobranzaMovimiento.setTerm(new Long(1));
					
					entityManager.persist(cobranzaMovimiento);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Factura anularFacturaById(Long id) {
		Factura result = null;

		try {
			Date hoy = GregorianCalendar.getInstance().getTime();
			
			result = entityManager.find(Factura.class, id);
			
			result.setAnulada(true);
			
			result.setUact(new Long(1));
			result.setFact(hoy);
			result.setTerm(new Long(1));
			
			TypedQuery<CobranzaMovimiento> query = 
				entityManager.createQuery(
					"SELECT cm"
					+ " FROM CobranzaMovimiento cm"
					+ " WHERE cm.factura.id = :facturaId",
					CobranzaMovimiento.class
				);
			query.setParameter("facturaId", id);
			
			Long cobranzaTipoDocumentoCobranzaParkingABITABId = 
				new Long(Configuration.getInstance().getProperty(
					"CobranzaTipoDocumento.cobranzaParkingABITAB")
				);
			Long cobranzaTipoDocumentoRecargoCobranzaParkingABITABId = 
				new Long(Configuration.getInstance().getProperty(
					"CobranzaTipoDocumento.recargoCobranzaParkingABITAB")
				);
			
			Long cobranzaTipoDocumentoAnulacionFacturaId = 
				new Long(Configuration.getInstance().getProperty(
					"CobranzaTipoDocumento.anulacionFactura")
				);
			
			CobranzaTipoDocumento cobranzaTipoDocumentoAnulacionFactura = 
				entityManager.find(
					CobranzaTipoDocumento.class, 
					cobranzaTipoDocumentoAnulacionFacturaId
				);
			
			for (CobranzaMovimiento cobranzaMovimiento : query.getResultList()) {
				if (cobranzaMovimiento.getCobranzaTipoDocumento().getId().equals(
						cobranzaTipoDocumentoCobranzaParkingABITABId
					)
					|| cobranzaMovimiento.getCobranzaTipoDocumento().getId().equals(
						cobranzaTipoDocumentoRecargoCobranzaParkingABITABId
					)
					) {
					// Anulación de Cobranza ABITAB
					cobranzaMovimiento.setFactura(null);
					
					cobranzaMovimiento.setUact(new Long(1));
					cobranzaMovimiento.setFact(hoy);
					cobranzaMovimiento.setTerm(new Long(1));
					
					entityManager.merge(cobranzaMovimiento);
				} else {
					// Extorno de Cobranza Manual
					CobranzaMovimiento cobranzaMovimientoExtorno = new CobranzaMovimiento();
					cobranzaMovimientoExtorno.setCliente(cobranzaMovimiento.getCliente());
					cobranzaMovimientoExtorno.setCobranzaTipoDocumento(
						cobranzaTipoDocumentoAnulacionFactura
					);
					cobranzaMovimientoExtorno.setFactura(cobranzaMovimiento.getFactura());
					cobranzaMovimientoExtorno.setFecha(hoy);
					cobranzaMovimientoExtorno.setImporte(
						-1 * cobranzaMovimiento.getImporte()
					);
					cobranzaMovimientoExtorno.setMoneda(cobranzaMovimiento.getMoneda());
					cobranzaMovimientoExtorno.setServicio(cobranzaMovimiento.getServicio());
					
					cobranzaMovimientoExtorno.setUact(new Long(1));
					cobranzaMovimientoExtorno.setFact(hoy);
					cobranzaMovimientoExtorno.setTerm(new Long(1));
					
					entityManager.persist(cobranzaMovimientoExtorno);
				}
			}
			
			result = entityManager.merge(result);
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