package br.com.devmedia.appfinal.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.devmedia.appfinal.dao.DepartamentoDao;
import br.com.devmedia.appfinal.entity.Departamento;

@Repository(value = "departamentoDao")
public class DepartamentoDaoImpl implements DepartamentoDao, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Class<Departamento> CLAZZ = Departamento.class;
    private final BaseDao<Departamento, Integer> dao;
    
    @Autowired
    public DepartamentoDaoImpl(DataSource dataSource) {
        this.dao = new BaseDao<>(dataSource);
    }

    @Override
    public SqlParameterSource parameterSource(Departamento entity) {
        return new BeanPropertySqlParameterSource(entity);
    }

    @Override
    public RowMapper<Departamento> rowMapper() {
        return new BeanPropertyRowMapper<>(CLAZZ);
    }

    @Override
    public Departamento save(Departamento departamento) {
        Number key = this.dao.save("departamento", "id", this.parameterSource(departamento));
        departamento.setId(key.intValue());
        
        return departamento;
    }

    @Override
    public int update(Departamento departamento) {
        String sql = "UPDATE departamento SET departamento = :departamento WHERE id = :id";
        return this.dao.update(sql, this.parameterSource(departamento));
    }

    @Override
    public int remove(Integer id) {
        String sql = "DELETE FROM departamento WHERE id = ?";
        return this.dao.remove(sql, id);
    }

    @Override
    public Departamento findById(Integer id) {
        String sql = "SELECT * FROM departamento WHERE id = ?";
        return this.dao.findById(sql, id, this.rowMapper());
    }

    @Override
    public List<Departamento> findAll() {
        String sql = "SELECT * FROM departamento";
        return this.dao.findAll(sql, this.rowMapper());
    }
}