package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.appfinal.dao.CargoDao;
import br.com.devmedia.appfinal.entity.Cargo;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CargoService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CargoDao dao;
    
    @Transactional(readOnly = false)
    public void saveOrUpdate(Cargo cargo) {
        if(cargo.getId() == null) {
            this.dao.save(cargo);
            return;
        }
        this.dao.update(cargo);
    }
    
    @Transactional(readOnly = false)
    public void remove(Integer id) {
        this.dao.remove(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Cargo findById(Integer id) {
        return this.dao.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Cargo> findAll() {
        return this.dao.findAll();
    }
}