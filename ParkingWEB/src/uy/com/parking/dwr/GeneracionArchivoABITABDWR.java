package uy.com.parking.dwr;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.directwebremoting.annotations.RemoteProxy;

import uy.com.parking.bean.GeneracionArchivoABITABBean;
import uy.com.parking.bean.IGeneracionArchivoABITABBean;

@RemoteProxy
public class GeneracionArchivoABITABDWR {

	private IGeneracionArchivoABITABBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = GeneracionArchivoABITABBean.class.getSimpleName();
		String remoteInterfaceName = IGeneracionArchivoABITABBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (IGeneracionArchivoABITABBean) context.lookup(lookupName);
	}
	
	public void generarArchivoABITAB() {
		try {
			IGeneracionArchivoABITABBean iGeneracionArchivoABITABBean = this.lookupBean();
			
			iGeneracionArchivoABITABBean.generarArchivoABITAB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}