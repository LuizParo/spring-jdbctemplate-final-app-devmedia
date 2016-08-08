package br.com.devmedia.appfinal.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface Dao<T> {

    public SqlParameterSource parameterSource(T entity);
    
    public RowMapper<T> rowMapper();
}