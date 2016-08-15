package br.com.devmedia.appfinal.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

final class BaseDao<T, PK> extends JdbcDaoSupport implements Serializable {
    private static final long serialVersionUID = 1L;

    public BaseDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    public NamedParameterJdbcTemplate namedQuery() {
        return new NamedParameterJdbcTemplate(this.getDataSource());
    }
    
    public Number save(String tableName, String key, SqlParameterSource parameterSource) {
        return new SimpleJdbcInsert(this.getDataSource())
                .withTableName(tableName)
                .usingGeneratedKeyColumns(key)
                .executeAndReturnKey(parameterSource);
    }
    
    public int update(String sql, SqlParameterSource parameterSource) {
        return this.namedQuery().update(sql, parameterSource);
    }
    
    public int remove(String sql, PK id) {
        return this.getJdbcTemplate().update(sql, id);
    }
    
    public T findById(String sql, PK id, RowMapper<T> rowMapper) {
        return this.getJdbcTemplate().queryForObject(sql, rowMapper, id);
    }
    
    public List<T> findAll(String sql, RowMapper<T> rowMapper) {
        return this.getJdbcTemplate().query(sql, rowMapper);
    }
    
    public int count(String tableName, Class<Integer> requiredType) {
        return this.getJdbcTemplate().queryForObject("SELECT COUNT(0) FROM " + tableName, requiredType);
    }
}