package uy.com.parking.bean;

import javax.ejb.Remote;

@Remote
public interface IGeneracionArchivoABITABBean {

	public void generarArchivoABITAB();
}