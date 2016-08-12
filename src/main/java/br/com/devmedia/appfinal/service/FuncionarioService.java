package br.com.devmedia.appfinal.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmedia.appfinal.dao.FuncionarioDao;
import br.com.devmedia.appfinal.entity.Endereco;
import br.com.devmedia.appfinal.entity.Funcionario;

@Service
public class FuncionarioService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private FuncionarioDao dao;
    
    @Autowired
    private EnderecoService enderecoService;
    
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

    public int remove(Integer id) {
        return this.dao.remove(id);
    }

    public Funcionario findById(Integer id) {
        return this.dao.findById(id);
    }

    public List<Funcionario> findAll() {
        return this.dao.findAll();
    }
}