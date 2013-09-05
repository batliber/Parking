package uy.com.parking.transferObjects;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class FacturaTO extends BaseTO {

	private Long numero;
	private Date fecha;
	private Double importe;
	private ClienteTO cliente;
	private MonedaTO moneda;
	private Collection<FacturaLineaTO> facturaLineas;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public ClienteTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTO cliente) {
		this.cliente = cliente;
	}

	public MonedaTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaTO moneda) {
		this.moneda = moneda;
	}

	public Collection<FacturaLineaTO> getFacturaLineas() {
		return facturaLineas;
	}

	public void setFacturaLineas(Collection<FacturaLineaTO> facturaLineas) {
		this.facturaLineas = facturaLineas;
	}
}