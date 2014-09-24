package uy.com.parking.bean;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
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

import uy.com.parking.entities.Cliente;
import uy.com.parking.entities.Moneda;
import uy.com.parking.entities.Servicio;
import uy.com.parking.entities.Vehiculo;
import uy.com.parking.entities.VehiculoServicioPrecio;
import uy.com.parking.util.Configuration;
import uy.com.parking.util.Constantes;

@Stateless
public class ClienteBean implements IClienteBean {

	@PersistenceContext(unitName = "uy.com.parking.persistenceUnit")
	private EntityManager entityManager;
	
	@EJB
	private IServicioBean iServicioBean;
	
	@EJB
	private IMonedaBean iMonedaBean;
	
	@EJB
	private IVehiculoServicioPrecioBean iVehiculoServicioPrecioBean;
	
	public Collection<Cliente> list() {
		Collection<Cliente> result = new LinkedList<Cliente>();
		
		try {
			Query query = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.fechaBaja IS NULL ORDER BY c.apellido ASC"
			);
			
			for (Object object : query.getResultList()) {
				result.add((Cliente) object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Cliente getById(Long id) {
		Cliente result = null;
		
		try {
			result = entityManager.find(Cliente.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Cliente getByDocumento(String documento) {
		Cliente result = null;
		
		try {
			TypedQuery<Cliente> query = 
				entityManager.createQuery(
					"SELECT c FROM Cliente c WHERE c.documento = :documento AND c.fechaBaja IS NULL", Cliente.class
				);
			query.setParameter("documento", documento);
			
			List<Cliente> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String exportarAExcel() {
		String result = null;
		
		PrintWriter printWriter = null;
		
		try {
			Collection<Cliente> clientes = this.list();
			
			Servicio servicioParkingMensual = iServicioBean.getById(
				new Long(Configuration.getInstance().getProperty("Servicio.ParkingMensual"))
			);
			
			Moneda monedaPesosUruguayos = iMonedaBean.getById(
				new Long(Configuration.getInstance().getProperty("Moneda.PesosUruguayos"))
			);
			
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
				Constantes.__PREFIJO_ARCHIVO_CLIENTES
				+ formatNombreArchivo.format(hoy)
				+ "."
				+ "csv";
			
			printWriter = 
				new PrintWriter(
					new FileWriter(
						Configuration.getInstance().getProperty("exportacion.carpeta") + nombreArchivo
					)
				);
			
			String cabezal =
				"Documento"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Nombre"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Apellido"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Domicilio"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Telefono"
				+ Constantes.__SEPARADOR_CAMPO
				+ "Ingreso";
			
			printWriter.println(cabezal);
			
			for (Cliente cliente : clientes) {
				String linea =
					cliente.getDocumento()
					+ Constantes.__SEPARADOR_CAMPO
					+ cliente.getNombre()
					+ Constantes.__SEPARADOR_CAMPO
					+ cliente.getApellido()
					+ Constantes.__SEPARADOR_CAMPO
					+ cliente.getDomicilio()
					+ Constantes.__SEPARADOR_CAMPO
					+ cliente.getTelefono()
					+ Constantes.__SEPARADOR_CAMPO
					+ format.format(cliente.getFechaAlta());
				
				printWriter.println(linea);
				
				for (Vehiculo vehiculo : cliente.getVehiculos()) {
					VehiculoServicioPrecio vehiculoServicioPrecioVigente = 
						iVehiculoServicioPrecioBean.getPrecioVigenteByVehiculoServicioMoneda(
							vehiculo, servicioParkingMensual, monedaPesosUruguayos
						);
					
					if (vehiculoServicioPrecioVigente != null) {
						linea =
							vehiculo.getMatricula()
							+ Constantes.__SEPARADOR_CAMPO
							+ vehiculo.getDescripcion()
							+ Constantes.__SEPARADOR_CAMPO
							+ servicioParkingMensual.getDescripcion()
							+ Constantes.__SEPARADOR_CAMPO
							+ decimalFormat.format(vehiculoServicioPrecioVigente.getPrecio());
						
						printWriter.println(linea);
					}
				}
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
	
	public void save(Cliente cliente) {
		try {
			Collection<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
			for (Vehiculo vehiculo : cliente.getVehiculos()) {
				vehiculos.add(entityManager.find(Vehiculo.class, vehiculo.getId()));
			}
			
			cliente.setVehiculos(vehiculos);
			
			entityManager.persist(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Cliente cliente) {
		try {
			Cliente managedCliente = entityManager.find(Cliente.class, cliente.getId());
			
			managedCliente.setFechaBaja(GregorianCalendar.getInstance().getTime());
			
			entityManager.merge(managedCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Cliente cliente) {
		try {
			Collection<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
			for (Vehiculo vehiculo : cliente.getVehiculos()) {
				vehiculos.add(entityManager.find(Vehiculo.class, vehiculo.getId()));
			}
			
			cliente.setVehiculos(vehiculos);
			
			entityManager.merge(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateConVehiculoServicioPrecios(
		Cliente cliente, Collection<VehiculoServicioPrecio> vehiculoServicioPrecios) {
		try {
			Date hoy = GregorianCalendar.getInstance().getTime();
			
			if (cliente.getId() != null) {
				TypedQuery<VehiculoServicioPrecio> query = 
					entityManager.createQuery(
						"SELECT vsp FROM VehiculoServicioPrecio vsp WHERE vsp.vehiculo.id = :vehiculoId AND vsp.validoHasta IS NULL",
						VehiculoServicioPrecio.class
					);
				
				Cliente clienteAnterior = entityManager.find(Cliente.class, cliente.getId());
				
				for (Vehiculo vehiculo : clienteAnterior.getVehiculos()) {
					query.setParameter("vehiculoId", vehiculo.getId());
					
					for (VehiculoServicioPrecio vehiculoServicioPrecio : query.getResultList()) {
						vehiculoServicioPrecio.setValidoHasta(hoy);
						
						vehiculoServicioPrecio.setUact(new Long(1));
						vehiculoServicioPrecio.setFact(hoy);
						vehiculoServicioPrecio.setTerm(new Long(1));
						
						entityManager.merge(vehiculoServicioPrecio);
					}
				}
			}

			Cliente managedCliente = entityManager.merge(cliente);
			
			Map<String, Vehiculo> mapVehiculos = new HashMap<String, Vehiculo>();
			for (Vehiculo vehiculo : managedCliente.getVehiculos()) {
				mapVehiculos.put(vehiculo.getMatricula(), vehiculo);
			}
			
			for (VehiculoServicioPrecio vehiculoServicioPrecio : vehiculoServicioPrecios) {
				vehiculoServicioPrecio.setVehiculo(mapVehiculos.get(vehiculoServicioPrecio.getVehiculo().getMatricula()));
				
				vehiculoServicioPrecio.setUact(new Long(1));
				vehiculoServicioPrecio.setFact(hoy);
				vehiculoServicioPrecio.setTerm(new Long(1));
				
				entityManager.merge(vehiculoServicioPrecio);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}