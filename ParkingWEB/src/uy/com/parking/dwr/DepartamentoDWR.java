package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.DepartamentoBean;
import uy.com.parking.bean.IDepartamentoBean;
import uy.com.parking.entities.Departamento;
import uy.com.parking.transferObjects.DepartamentoTO;

@RemoteProxy
public class DepartamentoDWR {

	private IDepartamentoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = DepartamentoBean.class.getSimpleName();
		String remoteInterfaceName = IDepartamentoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IDepartamentoBean) context.lookup(lookupName);
	}
	
	public Collection<DepartamentoTO> list() {
		Collection<DepartamentoTO> result = new LinkedList<DepartamentoTO>();
		
		try {
			IDepartamentoBean iDepartamentoBean = lookupBean();
			
			for (Departamento departamento : iDepartamentoBean.list()) {
				result.add(transform(departamento));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(DepartamentoTO departamentoTO) {
		this.update(departamentoTO);
	}

	public void remove(DepartamentoTO departamentoTO) {
		try {
			IDepartamentoBean iDepartamentoBean = lookupBean();
			
			Departamento departamento = new Departamento();
			departamento.setId(departamentoTO.getId());
			
			iDepartamentoBean.remove(departamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(DepartamentoTO departamentoTO) {
		try {
			IDepartamentoBean iDepartamentoBean = lookupBean();
			
			iDepartamentoBean.update(transform(departamentoTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DepartamentoTO transform(Departamento departamento) {
		DepartamentoTO departamentoTO = new DepartamentoTO();
		
		departamentoTO.setNombre(departamento.getNombre());
		departamentoTO.setPrefijo(departamento.getPrefijo());

		departamentoTO.setFact(departamento.getFact());
		departamentoTO.setId(departamento.getId());
		departamentoTO.setTerm(departamento.getTerm());
		departamentoTO.setUact(departamento.getUact());
		
		return departamentoTO;
	}
	
	public static Departamento transform(DepartamentoTO departamentoTO) {
		Departamento departamento = new Departamento();
		
		departamento.setNombre(departamentoTO.getNombre());
		departamento.setPrefijo(departamentoTO.getPrefijo());
		
		departamento.setId(departamentoTO.getId());
		departamento.setFact(departamentoTO.getFact());
		departamento.setTerm(departamentoTO.getTerm());
		departamento.setUact(departamentoTO.getUact());
		
		return departamento;
	}
}