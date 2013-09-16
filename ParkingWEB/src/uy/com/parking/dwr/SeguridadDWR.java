package uy.com.parking.dwr;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.ISeguridadBean;
import uy.com.parking.bean.SeguridadBean;

@RemoteProxy
public class SeguridadDWR {

	private ISeguridadBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = SeguridadBean.class.getSimpleName();
		String remoteInterfaceName = ISeguridadBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (ISeguridadBean) context.lookup(lookupName);
	}
	
	public String getActiveUserData() {
		String result = null;
		
		HttpSession httpSession = WebContextFactory.get().getSession(false);
		
		if ((httpSession != null) && (httpSession.getAttribute("sesion") != null)) {
			result = (String) httpSession.getAttribute("sesion");
		}
		
		return result;
	}
	
	public void login(String usuario, String contrasena) {
		try {
			ISeguridadBean iSeguridadBean = this.lookupBean();
			
			iSeguridadBean.login();
			
			HttpSession httpSession = WebContextFactory.get().getSession(true);
			
			if (httpSession.getAttribute("sesion") == null) {
				httpSession.setAttribute("sesion", usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {
		try {
			ISeguridadBean iSeguridadBean = this.lookupBean();
			
			iSeguridadBean.logout();
			
			HttpSession httpSession = WebContextFactory.get().getSession(false);
			
			if (httpSession.getAttribute("sesion") != null) {
				httpSession.removeAttribute("sesion");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}