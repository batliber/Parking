package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Grupo;

@Remote
public interface IGrupoBean {

	public Collection<Grupo> list();
}