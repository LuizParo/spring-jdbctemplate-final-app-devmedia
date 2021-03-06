package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.appfinal.dao.DepartamentoDao;
import br.com.devmedia.appfinal.entity.Departamento;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DepartamentoService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DepartamentoService.class);

    @Autowired
    private DepartamentoDao dao;

    @Transactional(readOnly = false)
    public Departamento saveOrUpdate(Departamento departamento) {
        if(departamento.getId() == null) {
            LOGGER.info("Salvando um Departamento!");
            return this.dao.save(departamento);
        }
        LOGGER.info("Alterando um Departamento!");
        this.dao.update(departamento);
        return departamento;
    }
    
    @Transactional(readOnly = false)
    public void remove(Integer id) {
        this.dao.remove(id);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Departamento findById(Integer id) {
        return this.dao.findById(id);
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Departamento> findAll() {
        return this.dao.findAll();
    }
}