package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.appfinal.dao.FuncionarioDao;
import br.com.devmedia.appfinal.entity.Endereco;
import br.com.devmedia.appfinal.entity.Funcionario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private FuncionarioDao dao;
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Transactional(readOnly = false)
    public Funcionario saveOrUpdate(Funcionario funcionario) {
        Endereco endereco = this.enderecoService.saveOrUpdate(funcionario.getEndereco());
        funcionario.setEndereco(endereco);
        
        if(funcionario.getId() == null) {
            this.dao.save(funcionario);
            return funcionario;
        }
        this.dao.update(funcionario);
        return funcionario;
    }

    @Transactional(readOnly = false)
    public int remove(Integer id) {
        return this.dao.remove(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Funcionario findById(Integer id) {
        return this.dao.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Funcionario> findAll() {
        return this.dao.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Funcionario> findByCargo(Integer idCargo) {
        return this.dao.findByCargo(idCargo);
    }
}