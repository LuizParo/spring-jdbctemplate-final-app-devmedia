package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.appfinal.dao.EnderecoDao;
import br.com.devmedia.appfinal.entity.Endereco;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EnderecoService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private EnderecoDao dao;

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public Endereco saveOrUpdate(Endereco endereco) {
        if(endereco.getId() == null) {
            return this.dao.save(endereco);
        }
        this.dao.update(endereco);
        return endereco;
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public int remove(Integer id) {
        return this.dao.remove(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Endereco findById(Integer id) {
        return this.dao.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Endereco> findAll() {
        return this.dao.findAll();
    }
}