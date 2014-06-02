package uy.com.parking.dwr;

import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.IUsuarioBean;
import uy.com.parking.bean.UsuarioBean;
import uy.com.parking.entities.Grupo;
import uy.com.parking.entities.Usuario;
import uy.com.parking.transferObjects.GrupoTO;
import uy.com.parking.transferObjects.UsuarioTO;
import uy.com.parking.util.MD5Utils;

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
				result.add(transform(usuario));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public UsuarioTO getById(Long id) {
		UsuarioTO result = null;
		
		try {
			IUsuarioBean iUsuarioBean = lookupBean();
			
			result = transform(iUsuarioBean.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public UsuarioTO getByLogin(String login) {
		UsuarioTO result = null;
		
		try {
			IUsuarioBean iUsuarioBean = lookupBean();
			
			result = transform(iUsuarioBean.getByLogin(login));
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
			
			usuario.setContrasena(MD5Utils.stringToMD5(usuarioTO.getContrasena()));
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

	public static UsuarioTO transform(Usuario usuario) {
		UsuarioTO usuarioTO = new UsuarioTO();
		
		Collection<GrupoTO> grupos = new LinkedList<GrupoTO>();
		for (Grupo grupo : usuario.getGrupos()) {
			grupos.add(GrupoDWR.transform(grupo));
		}
		
		usuarioTO.setGrupos(grupos);
		
		usuarioTO.setContrasena(usuario.getContrasena());
		usuarioTO.setLogin(usuario.getLogin());
		usuarioTO.setNombre(usuario.getNombre());

		usuarioTO.setId(usuario.getId());
		usuarioTO.setUact(usuario.getUact());
		usuarioTO.setFact(usuario.getFact());
		usuarioTO.setTerm(usuario.getTerm());
		
		return usuarioTO;
	}
}