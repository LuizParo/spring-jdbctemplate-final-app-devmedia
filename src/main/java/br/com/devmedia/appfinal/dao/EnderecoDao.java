package br.com.devmedia.appfinal.dao;

import java.util.List;

import br.com.devmedia.appfinal.entity.Endereco;

public interface EnderecoDao extends Dao<Endereco> {

    public Endereco save(Endereco endereco);

    public int update(Endereco endereco);

    public int remove(Integer id);

    public Endereco findById(Integer id);

    public List<Endereco> findAll();
}