package br.com.devmedia.appfinal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.devmedia.appfinal.dao.CargoDao;
import br.com.devmedia.appfinal.dao.DepartamentoDao;
import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.entity.Departamento;

@Repository(value = "cargoDao")
public class CargoDaoImpl implements CargoDao {
    private BaseDao<Cargo, Integer> dao;
    
    @Autowired
    private DepartamentoDao departamentoDao;
    
    @Autowired
    public CargoDaoImpl(DataSource dataSource) {
        this.dao = new BaseDao<>(dataSource);
    }

    @Override
    public SqlParameterSource parameterSource(Cargo cargo) {
        return new MapSqlParameterSource()
                .addValue("cargo", cargo.getCargo())
                .addValue("id_departamento", cargo.getDepartamento().getId())
                .addValue("id", cargo.getId());
    }

    @Override
    public RowMapper<Cargo> rowMapper() {
        return new RowMapper<Cargo>() {

            @Override
            public Cargo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Departamento departamento = CargoDaoImpl.this.departamentoDao.findById(rs.getInt("id_departamento"));
                
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setCargo(rs.getString("cargo"));
                cargo.setDepartamento(departamento);
                
                return cargo;
            }
        };
    }
    
    @Override
    public Cargo save(Cargo cargo) {
        Number key = this.dao.save("cargo", "id", this.parameterSource(cargo));
        cargo.setId(key.intValue());
        return cargo;
    }
    
    @Override
    public int update(Cargo cargo) {
        String sql = "UPDATE cargo SET cargo = :cargo, id_departamento = :id_departamento WHERE id = :id";
        return this.dao.update(sql, this.parameterSource(cargo));
    }
    
    @Override
    public int remove(Integer id) {
        String sql = "DELETE FROM cargo WHERE id = ?";
        return this.dao.remove(sql, id);
    }
    
    @Override
    public Cargo findById(Integer id) {
        String sql = "SELECT * FROM cargo WHERE id = ?";
        return this.dao.findById(sql, id, this.rowMapper());
    }
    
    @Override
    public List<Cargo> findAll() {
        String sql = "SELECT * FROM cargo";
        return this.dao.findAll(sql, this.rowMapper());
    }
}