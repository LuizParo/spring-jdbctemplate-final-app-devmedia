package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmedia.appfinal.dao.DepartamentoDao;
import br.com.devmedia.appfinal.entity.Departamento;

@Service
public class DepartamentoService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private DepartamentoDao dao;
    
    public Departamento save(Departamento departamento) {
        return this.dao.save(departamento);
    }
    
    public void update(Departamento departamento) {
        this.dao.update(departamento);
    }
    
    public void saveOrUpdate(Departamento departamento) {
        if(departamento.getId() == null) {
            this.dao.save(departamento);
        } else {
            this.dao.update(departamento);
        }
    }
    
    public void remove(Integer id) {
        this.dao.remove(id);
    }
    
    public Departamento findById(Integer id) {
        return this.dao.findById(id);
    }
    
    public List<Departamento> findAll() {
        return this.dao.findAll();
    }
}