package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.GrupoBean;
import uy.com.parking.bean.IGrupoBean;
import uy.com.parking.entities.Grupo;
import uy.com.parking.transferObjects.GrupoTO;

@RemoteProxy
public class GrupoDWR {

	private IGrupoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = GrupoBean.class.getSimpleName();
		String remoteInterfaceName = IGrupoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IGrupoBean) context.lookup(lookupName);
	}
	
	public Collection<GrupoTO> list() {
		Collection<GrupoTO> result = new LinkedList<GrupoTO>();
		
		try {
			IGrupoBean iGrupoBean = lookupBean();
			
			for (Grupo grupo : iGrupoBean.list()) {
				result.add(transform(grupo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static GrupoTO transform(Grupo grupo) {
		GrupoTO grupoTO = new GrupoTO();
		
		grupoTO.setDescripcion(grupo.getDescripcion());
		grupoTO.setNivel(grupo.getNivel());
		
		grupoTO.setId(grupo.getId());
		grupoTO.setUact(grupo.getUact());
		grupoTO.setFact(grupo.getFact());
		grupoTO.setTerm(grupo.getTerm());
		
		return grupoTO;
	}

	public static Grupo transform(GrupoTO grupoTO) {
		Grupo grupo = new Grupo();
		
		grupo.setDescripcion(grupoTO.getDescripcion());
		grupo.setNivel(grupoTO.getNivel());
		
		grupo.setId(grupoTO.getId());
		grupo.setUact(grupoTO.getUact());
		grupo.setFact(grupoTO.getFact());
		grupo.setTerm(grupoTO.getTerm());
		
		return grupo;
	}
}