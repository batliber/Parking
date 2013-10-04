package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IRegistroBean;
import uy.com.parking.bean.RegistroBean;
import uy.com.parking.entities.Registro;
import uy.com.parking.entities.RegistroTipo;
import uy.com.parking.transferObjects.RegistroTO;
import uy.com.parking.transferObjects.RegistroTipoTO;

@RemoteProxy
public class RegistroDWR {

	private IRegistroBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = RegistroBean.class.getSimpleName();
		String remoteInterfaceName = IRegistroBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IRegistroBean) context.lookup(lookupName);
	}
	
	public Collection<RegistroTO> list() {
		Collection<RegistroTO> result = new LinkedList<RegistroTO>();
		
		try {
			IRegistroBean iRegistroBean = lookupBean();
			
			for (Registro registro : iRegistroBean.list()) {
				result.add(this.transform(registro));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Collection<RegistroTO> listSinSalida() {
		Collection<RegistroTO> result = new LinkedList<RegistroTO>();
		
		try {
			IRegistroBean iRegistroBean = lookupBean();
			
			for (Registro registro : iRegistroBean.listSinSalida()) {
				result.add(this.transform(registro));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public RegistroTO getLastByMatricula(String matricula) {
		RegistroTO result = null;
		
		try {
			IRegistroBean iRegistroBean = lookupBean();
			
			Registro registro = iRegistroBean.getLastByMatricula(matricula);
			
			if (registro != null) {
				result = this.transform(registro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(RegistroTO registroTO) {
		try {
			IRegistroBean iRegistroBean = lookupBean();
			
			Registro registro = new Registro();
			
			registro.setFecha(registroTO.getFecha());
			registro.setMatricula(registroTO.getMatricula());
			
			RegistroTipo registroTipo = new RegistroTipo();
			registroTipo.setId(registroTO.getRegistroTipo().getId());
			
			registro.setRegistroTipo(registroTipo);
			
			registro.setFact(registroTO.getFact());
			registro.setTerm(registroTO.getTerm());
			registro.setUact(registroTO.getUact());
			
			iRegistroBean.save(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(RegistroTO registroTO) {
		try {
			IRegistroBean iRegistroBean = lookupBean();
			
			Registro registro = new Registro();
			registro.setId(registroTO.getId());
			
			iRegistroBean.remove(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(RegistroTO registroTO) {
		try {
			IRegistroBean iRegistroBean = lookupBean();
			
			Registro registro = new Registro();
			
			registro.setFecha(registroTO.getFecha());
			registro.setMatricula(registroTO.getMatricula());
			
			RegistroTipo registroTipo = new RegistroTipo();
			registroTipo.setId(registroTO.getRegistroTipo().getId());
			
			registro.setRegistroTipo(registroTipo);
			
			registro.setId(registroTO.getId());
			registro.setFact(registroTO.getFact());
			registro.setTerm(registroTO.getTerm());
			registro.setUact(registroTO.getUact());
			
			iRegistroBean.update(registro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private RegistroTO transform(Registro registro) {
		RegistroTO registroTO = new RegistroTO();
		
		registroTO.setFecha(registro.getFecha());
		registroTO.setMatricula(registro.getMatricula());
		
		RegistroTipoTO registroTipoTO = new RegistroTipoTO();
		
		registroTipoTO.setDescripcion(registro.getRegistroTipo().getDescripcion());
		
		registroTipoTO.setFact(registro.getRegistroTipo().getFact());
		registroTipoTO.setId(registro.getRegistroTipo().getId());
		registroTipoTO.setTerm(registro.getRegistroTipo().getTerm());
		registroTipoTO.setUact(registro.getRegistroTipo().getUact());
		
		registroTO.setRegistroTipo(registroTipoTO);
		
		registroTO.setFact(registro.getFact());
		registroTO.setId(registro.getId());
		registroTO.setTerm(registro.getTerm());
		registroTO.setUact(registro.getUact());
		
		return registroTO;
	}
}