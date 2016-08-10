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

import br.com.devmedia.appfinal.dao.EnderecoDao;
import br.com.devmedia.appfinal.entity.Endereco;

@Repository
public class EnderecoDaoImpl implements EnderecoDao, Serializable {
    private static final long serialVersionUID = 1L;
    
    private BaseDao<Endereco, Integer> dao;
    
    @Autowired
    public EnderecoDaoImpl(DataSource dataSource) {
        this.dao = new BaseDao<>(dataSource);
    }

    @Override
    public SqlParameterSource parameterSource(Endereco endereco) {
        return new BeanPropertySqlParameterSource(endereco);
    }

    @Override
    public RowMapper<Endereco> rowMapper() {
        return new BeanPropertyRowMapper<>(Endereco.class);
    }
    
    @Override
    public Endereco save(Endereco endereco) {
        Number key = this.dao.save("endereco", "id", this.parameterSource(endereco));
        endereco.setId(key.intValue());
        return endereco;
    }
    
    @Override
    public int update(Endereco endereco) {
        StringBuilder sql = new StringBuilder("UPDATE endereco ");
        sql.append("SET logradouro = :logradouro, numero = :numero, complemento = :complemento, ");
        sql.append("estado = :estado, bairro = :bairro, cidade = :cidade ");
        sql.append("WHERE id = :id");
        
        return this.dao.update(sql.toString(), this.parameterSource(endereco));
    }
    
    @Override
    public int remove(Integer id) {
        String sql = "DELETE FROM endereco WHERE id = ?";
        return this.dao.remove(sql, id);
    }
    
    @Override
    public Endereco findById(Integer id) {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        return this.dao.findById(sql, id, this.rowMapper());
    }
    
    @Override
    public List<Endereco> findAll() {
        String sql = "SELECT * FROM endereco";
        return this.dao.findAll(sql, this.rowMapper());
    }
}