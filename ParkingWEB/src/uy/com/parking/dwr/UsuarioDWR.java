package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IUsuarioBean;
import uy.com.parking.bean.UsuarioBean;
import uy.com.parking.entities.Usuario;
import uy.com.parking.transferObjects.UsuarioTO;

@RemoteProxy
public class UsuarioDWR {

	private IUsuarioBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = UsuarioBean.class.getSimpleName();
		String remoteInterfaceName = IUsuarioBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IUsuarioBean) context.lookup(lookupName);
	}
	
	public Collection<UsuarioTO> list() {
		Collection<UsuarioTO> result = new LinkedList<UsuarioTO>();
		
		try {
			IUsuarioBean iUsuarioBean = lookupBean();
			
			for (Usuario usuario : iUsuarioBean.list()) {
				UsuarioTO usuarioTO = new UsuarioTO();
				
				usuarioTO.setLogin(usuario.getLogin());
				usuarioTO.setContrasena(usuario.getContrasena());
				usuarioTO.setNombre(usuario.getNombre());

				usuarioTO.setFact(usuario.getFact());
				usuarioTO.setId(usuario.getId());
				usuarioTO.setTerm(usuario.getTerm());
				usuarioTO.setUact(usuario.getUact());
				
				result.add(usuarioTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void add(UsuarioTO usuarioTO) {
		this.update(usuarioTO);
	}

	public void remove(UsuarioTO usuarioTO) {
		try {
			IUsuarioBean iUsuarioBean = lookupBean();
			
			Usuario usuario = new Usuario();
			usuario.setId(usuarioTO.getId());
			
			iUsuarioBean.remove(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(UsuarioTO usuarioTO) {
		try {
			IUsuarioBean iUsuarioBean = lookupBean();
			
			Usuario usuario = new Usuario();
			
			usuario.setLogin(usuarioTO.getLogin());
			usuario.setContrasena(usuarioTO.getContrasena());
			usuario.setNombre(usuarioTO.getNombre());
			
			usuario.setId(usuarioTO.getId());
			usuario.setFact(usuarioTO.getFact());
			usuario.setTerm(usuarioTO.getTerm());
			usuario.setUact(usuarioTO.getUact());
			
			iUsuarioBean.update(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}