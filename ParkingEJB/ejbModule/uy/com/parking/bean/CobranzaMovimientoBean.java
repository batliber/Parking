package uy.com.parking.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import uy.com.parking.entities.Archivo;
import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.CobranzaMovimiento;
import uy.com.parking.entities.CobranzaProcesoExportacion;
import uy.com.parking.entities.CobranzaTipoDocumento;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Proceso;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.ServicioPrecio;
import uy.com.parking.entities.VehiculoServicioPrecio;
import uy.com.parking.util.Configuration;

@Stateless
public class CobranzaMovimientoBean implements ICobranzaMovimientoBean {
	
	private static String __SEPARADOR_NOMBRE_ARCHIVO = "_";
	private static String __CAMPO_VACIO = " ";
	private static String __SEPARADOR_CAMPO = "|";
	
	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	@EJB
	private IClienteBean iClienteBean;
	
	@EJB
	private ICobranzaTipoDocumentoBean iCobranzaTipoDocumentoBean;
	
	@EJB
	private IMonedaBean iMonedaBean;
	
	@EJB
	private IServicioBean iServicioBean;
	
	public Collection<CobranzaMovimiento> listDeudas() {
		Collection<CobranzaMovimiento> result = new LinkedList<CobranzaMovimiento>();
		
		try {
			Query query = entityManager.createQuery(
				"SELECT cm.moneda.id, cm.cliente.id, cm.servicio.id, SUM(cm.importe), cm.cliente.apellido"
				+ " FROM CobranzaMovimiento cm"
				+ " WHERE cm.cliente.fechaBaja IS NULL"
				+ " AND cm.cobranzaTipoDocumento.id IN ("
					+ " :cobranzaTipoDocumentoDeudaParkingABITAB,"
					+ " :cobranzaTipoDocumentoCobranzaParkingABITAB"
				+ " )"
				+ " GROUP BY cm.moneda.id, cm.cliente.id, cm.servicio.id, cm.cliente.apellido"
				+ " ORDER BY cm.cliente.apellido ASC"
			);
			query.setParameter(
				"cobranzaTipoDocumentoDeudaParkingABITAB",
				new Long(Configuration.getInstance().getProperty("CobranzaTipoDocumento.deudaParkingABITAB"))
			);
			query.setParameter(
				"cobranzaTipoDocumentoCobranzaParkingABITAB",
				new Long(Configuration.getInstance().getProperty("CobranzaTipoDocumento.cobranzaParkingABITAB"))
			);

			for (Object object : query.getResultList()) {
				Object[] tuple = (Object[]) object;
				
				CobranzaMovimiento cobranzaMovimiento = new CobranzaMovimiento();
				
				Moneda moneda = entityManager.find(Moneda.class, tuple[0]);
				
				cobranzaMovimiento.setMoneda(moneda);
				
				Cliente cliente = entityManager.find(Cliente.class, tuple[1]);
				
				cobranzaMovimiento.setCliente(cliente);
				
				Servicio servicio = entityManager.find(Servicio.class, tuple[2]);
				
				cobranzaMovimiento.setServicio(servicio);
				
				cobranzaMovimiento.setImporte((Double) tuple[3]);
				
				result.add(cobranzaMovimiento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<CobranzaMovimiento> listSinFacturarByCliente(Cliente cliente) {
		Collection<CobranzaMovimiento> result = new LinkedList<CobranzaMovimiento>();
		
		try {
			TypedQuery<CobranzaMovimiento> query = entityManager.createQuery(
				"SELECT cm"
				+ " FROM CobranzaMovimiento cm"
				+ " WHERE cm.cliente.id = :clienteId"
				+ " AND cm.factura IS NULL"
				+ " AND cm.proceso IS NOT NULL",
				CobranzaMovimiento.class
			);
			query.setParameter("clienteId", cliente.getId());
			
			for (CobranzaMovimiento cobranzaMovimiento : query.getResultList()) {
				result.add(cobranzaMovimiento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void save(CobranzaMovimiento cobranzaMovimiento) {
		try {
			entityManager.persist(cobranzaMovimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void procesarArchivoCobranza(String nombreArchivo) {
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(nombreArchivo));
			
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			
			Date hoy = gregorianCalendar.getTime();
			
			Long diaDelMesVencimiento = new Long(Configuration.getInstance().getProperty("Cobranza.diaMesVencimiento"));
			
			Proceso proceso = new Proceso();
			proceso.setFechaInicio(hoy);
			proceso.setNombreArchivo(nombreArchivo);
			
			proceso.setFact(hoy);
			proceso.setTerm(new Long(1));
			proceso.setUact(new Long(1));
			
			entityManager.persist(proceso);
			
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
			
			CobranzaTipoDocumento cobranzaTipoDocumentoCobranzaParkingABITAB = 
				iCobranzaTipoDocumentoBean.getById(
					new Long(Configuration.getInstance().getProperty("CobranzaTipoDocumento.cobranzaParkingABITAB"))
				);
			
			CobranzaTipoDocumento cobranzaTipoDocumentoRecargoDeudaParkingABITAB =
				iCobranzaTipoDocumentoBean.getById(
					new Long(Configuration.getInstance().getProperty("CobranzaTipoDocumento.recargoDeudaParkingABITAB"))
				);
			
			Servicio servicio = iServicioBean.getById(
					new Long(Configuration.getInstance().getProperty("Servicio.ParkingMensual"))
				);
			
			Double porcentajeRecargo = 
				new Double(Configuration.getInstance().getProperty("Cobranza.PorcentajeRecargo"));
			
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				String[] fields = line.split("\\|");
				
				String clienteDocumento = fields[0].trim();
//				String clienteNombre = fields[1].trim();
//				String clienteApellido = fields[2].trim();
//				String numeroDocumento = fields[3].trim();
				String monedaArchivo = fields[4].trim();
//				String importeEnviado = fields[5].trim();
//				String importeMora = fields[6].trim();
				String importeAbonado = fields[7].trim();
//				String concepto = fields[8].trim();
//				String agencia = fields[9].trim();
//				String subAgencia = fields[10].trim();
				String fechaPago = fields[11].trim();
				
				Cliente cliente = iClienteBean.getByDocumento(clienteDocumento);
				
				if (cliente != null) {
					Moneda moneda = iMonedaBean.getById(new Long(monedaArchivo));
					
					Date fecha = format.parse(fechaPago);
					GregorianCalendar gregorianCalendarFechaPago = new GregorianCalendar();
					gregorianCalendarFechaPago.setTime(fecha);
					
					Double importe = new Double(importeAbonado) * 0.01;
					
					// Procesamiento de recargos
					if (gregorianCalendarFechaPago.get(GregorianCalendar.DAY_OF_MONTH) > diaDelMesVencimiento) {
						CobranzaMovimiento cobranzaMovimientoRecargo = new CobranzaMovimiento();
						cobranzaMovimientoRecargo.setCliente(cliente);
						cobranzaMovimientoRecargo.setCobranzaTipoDocumento(
							cobranzaTipoDocumentoRecargoDeudaParkingABITAB
						);
						cobranzaMovimientoRecargo.setFecha(fecha);
						
						Double importeRecargo = importe * (porcentajeRecargo / (1 + porcentajeRecargo));
						cobranzaMovimientoRecargo.setImporte(
							importeRecargo * cobranzaTipoDocumentoRecargoDeudaParkingABITAB.getSigno()
						);
						
						cobranzaMovimientoRecargo.setMoneda(moneda);
						cobranzaMovimientoRecargo.setProceso(proceso);
						cobranzaMovimientoRecargo.setServicio(servicio);
						
						cobranzaMovimientoRecargo.setUact(new Long(1));
						cobranzaMovimientoRecargo.setFact(hoy);
						cobranzaMovimientoRecargo.setTerm(new Long(1));
						
						entityManager.persist(cobranzaMovimientoRecargo);
						
						importe = importe - importeRecargo;
					}
					
					CobranzaMovimiento cobranzaMovimiento = new CobranzaMovimiento();
					cobranzaMovimiento.setCliente(cliente);
					cobranzaMovimiento.setCobranzaTipoDocumento(cobranzaTipoDocumentoCobranzaParkingABITAB);
					cobranzaMovimiento.setFecha(fecha);
					
					cobranzaMovimiento.setImporte(importe * cobranzaTipoDocumentoCobranzaParkingABITAB.getSigno());
					
					cobranzaMovimiento.setMoneda(moneda);
					cobranzaMovimiento.setProceso(proceso);
					cobranzaMovimiento.setServicio(servicio);
					
					cobranzaMovimiento.setFact(hoy);
					cobranzaMovimiento.setTerm(new Long(1));
					cobranzaMovimiento.setUact(new Long(1));
					
					entityManager.persist(cobranzaMovimiento);
				}
			}
			
			proceso.setFechaFin(GregorianCalendar.getInstance().getTime());
			
			entityManager.merge(proceso);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String generarArchivoCobranzaAbitabByFecha(Date fecha) {
		String result = null;
		
		PrintWriter printWriter = null;
		
		try {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(fecha);
			gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gregorianCalendar.set(GregorianCalendar.MINUTE, 0);
			gregorianCalendar.set(GregorianCalendar.SECOND, 0);
			gregorianCalendar.set(GregorianCalendar.MILLISECOND, 0);
			
			// Creación de CobranzaProcesoExportacion
			Query queryCobranzaProcesoExportacion = 
				entityManager.createQuery(
					"SELECT MAX(cpe.numeroLote) FROM CobranzaProcesoExportacion cpe"
				);

			Long maxNumeroLote = (Long) queryCobranzaProcesoExportacion.getSingleResult();

			Long numeroLote = null;
			if (maxNumeroLote != null) {
				numeroLote = maxNumeroLote + 1;
			} else {
				numeroLote = new Long(1);
			}
			
			Date hoy = GregorianCalendar.getInstance().getTime();
			
			SimpleDateFormat formatNombreArchivo = new SimpleDateFormat("ddMMyy");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat formatMesAno = new SimpleDateFormat("MM/yy");
			DecimalFormat decimalFormat = new DecimalFormat("############");
			
			// Cálculo de nombre de archivo
			String nombreArchivo = 
				Configuration.getInstance().getProperty("archivoONL.identificadorEmpresa")
				+ __SEPARADOR_NOMBRE_ARCHIVO
				+ formatNombreArchivo.format(hoy)
				+ __SEPARADOR_NOMBRE_ARCHIVO
				+ numeroLote
				+ "."
				+ Configuration.getInstance().getProperty("archivoONL.extension");
			
			CobranzaProcesoExportacion cobranzaProcesoExportacion = new CobranzaProcesoExportacion();
			cobranzaProcesoExportacion.setFecha(hoy);
			cobranzaProcesoExportacion.setNombreArchivo(nombreArchivo);
			cobranzaProcesoExportacion.setNumeroLote(numeroLote);
			
			cobranzaProcesoExportacion.setUact(new Long(1));
			cobranzaProcesoExportacion.setFact(hoy);
			cobranzaProcesoExportacion.setTerm(new Long(1));
			
			entityManager.persist(cobranzaProcesoExportacion);
			
			// Cálculo de cabezal
			String posicionCampoImporte = 
				Configuration.getInstance().getProperty("archivoONL.posicionCampoImporte");
			String posicionCampoMoneda = 
				Configuration.getInstance().getProperty("archivoONL.posicionCampoMoneda");
			String cantidadMonedas = "1";
			
			String cabezal = 
				posicionCampoImporte + __SEPARADOR_CAMPO 
				+ posicionCampoMoneda + __SEPARADOR_CAMPO 
				+ cantidadMonedas;
			
			printWriter = 
				new PrintWriter(
					new FileWriter(
						Configuration.getInstance().getProperty("exportacion.carpeta") + nombreArchivo
					)
				);
			
			printWriter.println(cabezal);
			
			Map<Long, Integer> lineasMoneda = new HashMap<Long, Integer>();
			Map<Long, Double> importesMoneda = new HashMap<Long, Double>();
			
			// Campos invariables de cada línea
			String tipoDeGestion = Configuration.getInstance().getProperty("archivoONL.tipoDeGestion");
			String empresaId = Configuration.getInstance().getProperty("archivoONL.empresaId");;
			String nroEmpresa = Configuration.getInstance().getProperty("archivoONL.nroEmpresa");;
			String subEmpresa = Configuration.getInstance().getProperty("archivoONL.subEmpresa");;
			
			// 10 de cada mes
			gregorianCalendar.set(GregorianCalendar.DAY_OF_MONTH, 10);
			
			String fechaVencimiento = format.format(gregorianCalendar.getTime());
			
			// Ultimo día del mes
			gregorianCalendar.roll(GregorianCalendar.MONTH, true);
			gregorianCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
			gregorianCalendar.roll(GregorianCalendar.DAY_OF_YEAR, false);
			
			String fechaCorte = format.format(gregorianCalendar.getTime());
			
			// Primer día del mes
			gregorianCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
			
			String fechaEmision = format.format(gregorianCalendar.getTime());
			
			String importeMaximo = __CAMPO_VACIO;
			String importeMinimo = __CAMPO_VACIO;
			String bonificacion = __CAMPO_VACIO;
			String descuento = __CAMPO_VACIO;
			String diasGracia = __CAMPO_VACIO;
			String mora = __CAMPO_VACIO;
			String cotizacion = __CAMPO_VACIO;
			String cuota = __CAMPO_VACIO;
			String remitente = __CAMPO_VACIO;
			String RUC = __CAMPO_VACIO;
			String razonSocial = __CAMPO_VACIO;
			String direccion = __CAMPO_VACIO;
			String codigoPostal = __CAMPO_VACIO;
			String concepto1 = 
				Configuration.getInstance().getProperty("archivoONL.prefijoConcepto1") 
				+ " " + formatMesAno.format(hoy);
			String concepto2 = __CAMPO_VACIO;
			String concepto3 = __CAMPO_VACIO;
			String concepto4 = __CAMPO_VACIO;
			String observacion1 = __CAMPO_VACIO;
			String observacion2 = __CAMPO_VACIO;
			
			// 1 - Controla | 2 - No controla
			String controlCuotas = 
				Configuration.getInstance().getProperty("archivoONL.controlCuotas");
			
			for (CobranzaMovimiento cobranzaMovimiento : this.listDeudas()) {
				if (cobranzaMovimiento.getImporte() < 0) {
					if (lineasMoneda.containsKey(cobranzaMovimiento.getMoneda().getId())) {
						lineasMoneda.put(
							cobranzaMovimiento.getMoneda().getId(),
							lineasMoneda.get(cobranzaMovimiento.getMoneda().getId()) + 1
						);
						
						importesMoneda.put(
							cobranzaMovimiento.getMoneda().getId(), 
							importesMoneda.get(cobranzaMovimiento.getMoneda().getId()) 
								+ (-1 * cobranzaMovimiento.getImporte())
						);
					} else {
						lineasMoneda.put(cobranzaMovimiento.getMoneda().getId(), 1);
						
						importesMoneda.put(
							cobranzaMovimiento.getMoneda().getId(), 
							(-1 * cobranzaMovimiento.getImporte())
						);
					}
						
					String clienteDocumento = cobranzaMovimiento.getCliente().getDocumento();
					String clienteId = __CAMPO_VACIO;
					String clienteNombre = cobranzaMovimiento.getCliente().getNombre();
					String clienteApellido = cobranzaMovimiento.getCliente().getNombre();
					if (cobranzaMovimiento.getCliente().getApellido() != null) {
						clienteApellido = cobranzaMovimiento.getCliente().getApellido();
					}
					String clienteDocuemnto = __CAMPO_VACIO;
					
					// 1 - Factura | 2 - Nota de crédito
					String tipoDocumento = Configuration.getInstance().getProperty("archivoONL.tipoDocumento");
					
					String importe = decimalFormat.format(-1 * cobranzaMovimiento.getImporte() * 100);
					
					// 1 - Pesos | 2 - Dólares
					String tipoMoneda = cobranzaMovimiento.getMoneda().getId().toString();
					
					String linea =
						tipoDeGestion + __SEPARADOR_CAMPO
						+ empresaId + __SEPARADOR_CAMPO
						+  nroEmpresa + __SEPARADOR_CAMPO
						+  subEmpresa + __SEPARADOR_CAMPO
						+  numeroLote + __SEPARADOR_CAMPO
						+  clienteDocumento + __SEPARADOR_CAMPO
						+  clienteId + __SEPARADOR_CAMPO
						+  clienteNombre + __SEPARADOR_CAMPO
						+  clienteApellido + __SEPARADOR_CAMPO
						+  clienteDocuemnto + __SEPARADOR_CAMPO
						+  tipoDocumento + __SEPARADOR_CAMPO
						+  fechaVencimiento + __SEPARADOR_CAMPO
						+  fechaCorte + __SEPARADOR_CAMPO
						+  fechaEmision + __SEPARADOR_CAMPO
						+  importe + __SEPARADOR_CAMPO
						+  importeMaximo + __SEPARADOR_CAMPO
						+  importeMinimo + __SEPARADOR_CAMPO
						+  tipoMoneda + __SEPARADOR_CAMPO
						+  bonificacion + __SEPARADOR_CAMPO
						+  descuento + __SEPARADOR_CAMPO
						+  diasGracia + __SEPARADOR_CAMPO
						+  mora + __SEPARADOR_CAMPO
						+  cotizacion + __SEPARADOR_CAMPO
						+  cuota + __SEPARADOR_CAMPO
						+  remitente + __SEPARADOR_CAMPO
						+  RUC + __SEPARADOR_CAMPO
						+  razonSocial + __SEPARADOR_CAMPO
						+  direccion + __SEPARADOR_CAMPO
						+  codigoPostal + __SEPARADOR_CAMPO
						+  concepto1 + __SEPARADOR_CAMPO
						+  concepto2 + __SEPARADOR_CAMPO
						+  concepto3 + __SEPARADOR_CAMPO
						+  concepto4 + __SEPARADOR_CAMPO
						+  observacion1 + __SEPARADOR_CAMPO
						+  observacion2 + __SEPARADOR_CAMPO
						+  controlCuotas;
					
					printWriter.println(linea);
				}
			}
			
			// Cálculo de totales
			for (Long monedaId : lineasMoneda.keySet()) {
				String totales = "#" + __SEPARADOR_CAMPO 
					+ monedaId + __SEPARADOR_CAMPO
					+ lineasMoneda.get(monedaId) + __SEPARADOR_CAMPO 
					+ decimalFormat.format(importesMoneda.get(monedaId) * 100);
				
				printWriter.println(totales);
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
	
	public void generarCobranzaMovimientosByFecha(Date fecha) {
		try {
			// Obtencion de cilentes
			TypedQuery<Cliente> queryClientes = entityManager.createQuery(
				"SELECT c"
				+ " FROM Cliente c"
				+ " WHERE c.fechaBaja IS NULL", 
				Cliente.class
			);
			
			Collection<Cliente> clientes = queryClientes.getResultList();
			
			// Obtención de Precios de "Estacionamiento Mensual" por cliente 
			TypedQuery<VehiculoServicioPrecio> query = entityManager.createQuery(
				"SELECT vsp"
				+ " FROM VehiculoServicioPrecio vsp"
				+ " WHERE vsp.validoHasta IS NULL"
				+ " AND vsp.servicio.id = :servicioId",
				VehiculoServicioPrecio.class
			);
			query.setParameter(
				"servicioId", 
				new Long(Configuration.getInstance().getProperty("Servicio.ParkingMensual"))
			);
			
			// Obtencion de Precio de "Estacionamiento Mensual"
			TypedQuery<ServicioPrecio> queryServicioPrecio = entityManager.createQuery(
				"SELECT sp"
				+ " FROM ServicioPrecio sp"
				+ " WHERE sp.validoHasta IS NULL"
				+ " AND sp.servicio.id = :servicioId",
				ServicioPrecio.class
			);
			queryServicioPrecio.setParameter(
				"servicioId", 
				new Long(Configuration.getInstance().getProperty("Servicio.ParkingMensual"))
			);
			
//			ServicioPrecio servicioPrecio = queryServicioPrecio.getSingleResult();
			
			// Obtencion de tipo de documento "Deuda parking ABITAB"
			CobranzaTipoDocumento cobranzaTipoDocumento = 
				entityManager.find(
					CobranzaTipoDocumento.class, 
					new Long(
						Configuration.getInstance().getProperty("CobranzaTipoDocumento.deudaParkingABITAB")
					)
				);
			
			// Obtención de clientes ya procesados el día de hoy
			TypedQuery<Cliente> queryCliente = entityManager.createQuery(
				"SELECT cm.cliente"
				+ " FROM CobranzaMovimiento cm"
				+ " WHERE cm.cobranzaProcesoExportacion.fecha > :fechaDesde"
				+ " AND cm.cobranzaProcesoExportacion.fecha < :fechaHasta",
				Cliente.class
			);
			
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(fecha);
			gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gregorianCalendar.set(GregorianCalendar.MINUTE, 0);
			gregorianCalendar.set(GregorianCalendar.SECOND, 0);
			gregorianCalendar.set(GregorianCalendar.MILLISECOND, 0);
			
			queryCliente.setParameter(
				"fechaDesde", gregorianCalendar.getTime()
			);
			
			gregorianCalendar.roll(GregorianCalendar.DAY_OF_YEAR, true);
			
			queryCliente.setParameter(
				"fechaHasta", gregorianCalendar.getTime()
			);
			
			Collection<Cliente> clientesProcesados = queryCliente.getResultList();
			
			List<VehiculoServicioPrecio> resultList = query.getResultList();
			
			Date hoy = GregorianCalendar.getInstance().getTime();
			
			// Clientes con precio particular
			for (VehiculoServicioPrecio vehiculoServicioPrecio : resultList) {
				for (Cliente cliente : vehiculoServicioPrecio.getVehiculo().getClientes()) {
					if (cliente.getFechaBaja() == null) {
						if (!clientesProcesados.contains(cliente)) {
							// Creación de CobranzaMovimiento por cada Cliente
							CobranzaMovimiento cobranzaMovimiento = new CobranzaMovimiento();
							cobranzaMovimiento.setCliente(cliente);
							cobranzaMovimiento.setCobranzaTipoDocumento(cobranzaTipoDocumento);
							cobranzaMovimiento.setFecha(hoy);
							cobranzaMovimiento.setImporte(
								vehiculoServicioPrecio.getPrecio() * cobranzaTipoDocumento.getSigno()
							);
							cobranzaMovimiento.setMoneda(vehiculoServicioPrecio.getMoneda());
							cobranzaMovimiento.setServicio(vehiculoServicioPrecio.getServicio());
							
							cobranzaMovimiento.setUact(new Long(1));
							cobranzaMovimiento.setFact(hoy);
							cobranzaMovimiento.setTerm(new Long(1));
							
							entityManager.persist(cobranzaMovimiento);
						}
						
						clientes.remove(cliente);
					}
				}
			}
			
//			// Clientes sin precio particular
//			for (Cliente cliente : clientes) {
//				if (!clientesProcesados.contains(cliente)) {
//					// Creación de CobranzaMovimiento por cada Cliente
//					CobranzaMovimiento cobranzaMovimiento = new CobranzaMovimiento();
//					cobranzaMovimiento.setCliente(cliente);
//					cobranzaMovimiento.setCobranzaTipoDocumento(cobranzaTipoDocumento);
//					cobranzaMovimiento.setFecha(hoy);
//					cobranzaMovimiento.setImporte(
//						servicioPrecio.getPrecio() * cobranzaTipoDocumento.getSigno()
//					);
//					cobranzaMovimiento.setMoneda(servicioPrecio.getMoneda());
//					cobranzaMovimiento.setServicio(servicioPrecio.getServicio());
//					
//					cobranzaMovimiento.setUact(new Long(1));
//					cobranzaMovimiento.setFact(hoy);
//					cobranzaMovimiento.setTerm(new Long(1));
//					
//					entityManager.persist(cobranzaMovimiento);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deshacerCobranzaMovimientosByFecha(Date fecha) {
		try {
			TypedQuery<Date> queryFact =
				entityManager.createQuery(
					"SELECT MAX(cm.fact) FROM CobranzaMovimiento cm"
					+ " WHERE cm.cobranzaTipoDocumento.id = :cobranzaMovimientoId"
					+ " AND cm.cobranzaProcesoExportacion IS NULL"
					+ " AND cm.factura IS NULL"
					, Date.class
				);
			queryFact.setParameter(
				"cobranzaMovimientoId", new Long(Configuration.getInstance().getProperty("CobranzaTipoDocumento.deudaParkingABITAB"))
			);
			
			Collection<Date> resultListFact = queryFact.getResultList();
			
			if (!resultListFact.isEmpty()) {
				TypedQuery<CobranzaMovimiento> query = 
					entityManager.createQuery(
						"SELECT cm FROM CobranzaMovimiento cm"
						+ " WHERE cm.cobranzaTipoDocumento.id = :cobranzaMovimientoId"
						+ " AND cm.cobranzaProcesoExportacion IS NULL"
						+ " AND cm.factura IS NULL"
						+ " AND cm.fact >= :cobranzaMovimientoFact"
						, CobranzaMovimiento.class
					);
				query.setParameter(
					"cobranzaMovimientoId", new Long(Configuration.getInstance().getProperty("CobranzaTipoDocumento.deudaParkingABITAB"))
				);
				query.setParameter("cobranzaMovimientoFact", resultListFact.toArray(new Date[]{})[0]);
				
				for (CobranzaMovimiento cobranzaMovimiento : query.getResultList()) {
					entityManager.remove(cobranzaMovimiento);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deshacerUltimaImportacion() {
		try {
			TypedQuery<Proceso> query = entityManager.createQuery(
				"SELECT p"
				+ " FROM Proceso p"
				+ " ORDER BY p.fact DESC",
				Proceso.class
			);
			query.setMaxResults(1);
			
			Collection<Proceso> procesos = query.getResultList();
			if (procesos.size() > 0) {
				Proceso proceso = procesos.toArray(new Proceso[]{})[0];
				
				TypedQuery<CobranzaMovimiento> queryCobranzaMovimientos = entityManager.createQuery(
					"SELECT cm"
					+ " FROM CobranzaMovimiento cm"
					+ " WHERE cm.proceso.id = :procesoId"
					, CobranzaMovimiento.class
				);
				queryCobranzaMovimientos.setParameter("procesoId", proceso.getId());
				
				for (CobranzaMovimiento cobranzaMovimiento : queryCobranzaMovimientos.getResultList()) {
					entityManager.remove(cobranzaMovimiento);
				}
				
				File file = new File(proceso.getNombreArchivo());
				
				file.delete();
				
				entityManager.remove(proceso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Collection<Archivo> listArchivos() {
		Collection<Archivo> result = new LinkedList<Archivo>();
		
		try {
			File folder = new File(Configuration.getInstance().getProperty("importacion.carpeta"));
			
			File[] files = folder.listFiles(
				new FilenameFilter() {
					public boolean accept(File arg0, String arg1) {
						return !(new File(arg0.getAbsolutePath() + File.separator + arg1).isDirectory());
					}
				}
			);
			
			Arrays.sort(files, new Comparator<File>(){
				public int compare(File arg0, File arg1) {
					return - new Long(arg0.lastModified()).compareTo(new Long(arg1.lastModified()));
				}
			});
			
			for (File file : files) {
				Archivo archivo = new Archivo();
				archivo.setNombre(file.getName());
				
				result.add(archivo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}