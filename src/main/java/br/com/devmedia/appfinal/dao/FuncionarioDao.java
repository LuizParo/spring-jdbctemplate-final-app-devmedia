package br.com.devmedia.appfinal.dao;

import java.util.List;

import br.com.devmedia.appfinal.entity.Funcionario;

public interface FuncionarioDao extends Dao<Funcionario> {

    public Funcionario save(Funcionario funcionario);

    public int update(Funcionario funcionario);

    public int remove(Integer id);

    public Funcionario findById(Integer id);

    public List<Funcionario> findAll();
}