package br.com.devmedia.appfinal.dao;

import java.util.List;

import br.com.devmedia.appfinal.entity.Departamento;

public interface DepartamentoDao extends Dao<Departamento> {

    public Departamento save(Departamento departamento);
    
    public int update(Departamento departamento);
    
    public int remove(Integer id);
    
    public Departamento findById(Integer id);
    
    public List<Departamento> findAll();
}