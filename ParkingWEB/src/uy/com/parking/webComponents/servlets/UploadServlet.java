package uy.com.parking.webComponents.servlets;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import uy.com.parking.bean.CobranzaMovimientoBean;
import uy.com.parking.bean.ICobranzaMovimientoBean;
import uy.com.parking.util.Configuration;

@MultipartConfig(
	fileSizeThreshold=1024*1024*10,	// 10 MB
	maxFileSize=1024*1024*50,		// 50 MB
	maxRequestSize=1024*1024*100	// 100 MB
)
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6189664872735800452L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		try {
			String fileName = null;
			for (Part part : request.getParts()) {
				String contentDisposition = part.getHeader("content-disposition");
	
				String[] tokens = contentDisposition.split(";");
		        for (String token : tokens) {
		        	if (token.trim().startsWith("filename")) {
	            		fileName = token.substring(token.indexOf("=") + 2, token.length()-1);
	            	}
		        }
		        
		        part.write(Configuration.getInstance().getProperty("importacion.carpeta") + fileName);
			}
			
			ICobranzaMovimientoBean iCobranzaMovimientoBean = lookupBean();
			
			iCobranzaMovimientoBean.procesarArchivoCobranza(
				Configuration.getInstance().getProperty("importacion.carpeta") + fileName
			);
			
			request.setAttribute("message", "El archivo se ha procesado correctamente.");
			request.getRequestDispatcher("/pages/abitab/abitab.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("message", "No se ha podido completar la operaci&oacute;n.");
			request.getRequestDispatcher("/pages/abitab/abitab.jsp").forward(request, response);
		}
	}
	
	private ICobranzaMovimientoBean lookupBean() throws NamingException {
		String EARName = "Parking";
		String beanName = CobranzaMovimientoBean.class.getSimpleName();
		String remoteInterfaceName = ICobranzaMovimientoBean.class.getName();
		String lookupName = EARName + "/" + beanName + "/remote-" + remoteInterfaceName;
		Context context = new InitialContext();
		
		return (ICobranzaMovimientoBean) context.lookup(lookupName);
	}
}