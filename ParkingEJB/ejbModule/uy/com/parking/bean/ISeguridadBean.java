package uy.com.parking.bean;

import javax.ejb.Remote;

@Remote
public interface ISeguridadBean {

	public void login();
	
	public void logout();
}