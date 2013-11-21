package uy.com.parking.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.CobranzaMovimiento;
import uy.com.parking.entities.CobranzaTipoDocumento;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Proceso;
import uy.com.parking.entities.Servicio;

@Stateless
public class CobranzaMovimientoBean implements ICobranzaMovimientoBean {
	
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
	
	public void procesarArchivoCobranza(String nombreArchivo) {
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(nombreArchivo));
			
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			
			Date hoy = gregorianCalendar.getTime();
			
			Proceso proceso = new Proceso();
			proceso.setFechaInicio(hoy);
			proceso.setNombreArchivo(nombreArchivo);
			
			proceso.setFact(hoy);
			proceso.setTerm(new Long(1));
			proceso.setUact(new Long(1));
			
			entityManager.persist(proceso);
			
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
			
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
				
				CobranzaMovimiento cobranzaMovimiento = new CobranzaMovimiento();
				
				Cliente cliente = iClienteBean.getByDocumento(clienteDocumento);
				
				cobranzaMovimiento.setCliente(cliente);
				
				CobranzaTipoDocumento cobranzaTipoDocumento = iCobranzaTipoDocumentoBean.getById(new Long(1));
				
				cobranzaMovimiento.setCobranzaTipoDocumento(cobranzaTipoDocumento);
				
				cobranzaMovimiento.setFecha(format.parse(fechaPago));
				
				Double importe = new Double(importeAbonado) * 0.01;
				cobranzaMovimiento.setImporte(importe * cobranzaTipoDocumento.getSigno());
				
				Moneda moneda = iMonedaBean.getById(new Long(monedaArchivo));
				
				cobranzaMovimiento.setMoneda(moneda);
				cobranzaMovimiento.setProceso(proceso);
				
				Servicio servicio = iServicioBean.getById(new Long(7));
				
				cobranzaMovimiento.setServicio(servicio);
				
				cobranzaMovimiento.setFact(hoy);
				cobranzaMovimiento.setTerm(new Long(1));
				cobranzaMovimiento.setUact(new Long(1));
				
				entityManager.persist(cobranzaMovimiento);
			}
			
			proceso.setFechaFin(gregorianCalendar.getTime());
			
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

	public Collection<CobranzaMovimiento> listSinFacturarByCliente(Cliente cliente) {
		Collection<CobranzaMovimiento> result = new LinkedList<CobranzaMovimiento>();
		
		try {
			TypedQuery<CobranzaMovimiento> query = entityManager.createQuery(
				"SELECT cm"
				+ " FROM CobranzaMovimiento cm"
				+ " WHERE cm.cliente.id = :clienteId"
				+ " AND cm.factura IS NULL",
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
}