package br.com.devmedia.appfinal.dao;

import java.util.List;

import br.com.devmedia.appfinal.entity.Cargo;

public interface CargoDao extends Dao<Cargo> {

    public Cargo save(Cargo cargo);

    public int update(Cargo cargo);

    public int remove(Integer id);

    public Cargo findById(Integer id);

    public List<Cargo> findAll();

    public List<Cargo> findByPage(int page, int size);

    public int count();
}