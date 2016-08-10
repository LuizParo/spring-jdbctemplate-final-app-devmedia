package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmedia.appfinal.dao.EnderecoDao;
import br.com.devmedia.appfinal.entity.Endereco;

@Service
public class EnderecoService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private EnderecoDao dao;

    public Endereco saveOrUpdate(Endereco endereco) {
        if(endereco.getId() == null) {
            return this.dao.save(endereco);
        }
        this.dao.update(endereco);
        return endereco;
    }

    public int remove(Integer id) {
        return this.dao.remove(id);
    }

    public Endereco findById(Integer id) {
        return this.dao.findById(id);
    }

    public List<Endereco> findAll() {
        return this.dao.findAll();
    }
}