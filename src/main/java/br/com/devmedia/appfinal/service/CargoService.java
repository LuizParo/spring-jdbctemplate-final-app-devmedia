package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmedia.appfinal.dao.CargoDao;
import br.com.devmedia.appfinal.entity.Cargo;

@Service
public class CargoService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CargoDao dao;
    
    public void saveOrUpdate(Cargo cargo) {
        if(cargo.getId() == null) {
            this.dao.save(cargo);
            return;
        }
        this.dao.update(cargo);
    }
    
    public void remove(Integer id) {
        this.dao.remove(id);
    }

    public Cargo findById(Integer id) {
        return this.dao.findById(id);
    }

    public List<Cargo> findAll() {
        return this.dao.findAll();
    }
}