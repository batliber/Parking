package uy.com.parking.bean;

import java.util.Collection;

import javax.ejb.Remote;

import uy.com.parking.entities.Departamento;

@Remote
public interface IDepartamentoBean {

	public Collection<Departamento> list();
	
	public Departamento getById(Long id);
	
	public Departamento getByMatricula(String matricula);
	
	public void save(Departamento departamento);
	
	public void remove(Departamento departamento);
	
	public void update(Departamento departamento);
}