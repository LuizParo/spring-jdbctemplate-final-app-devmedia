package br.com.devmedia.appfinal.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
import br.com.devmedia.appfinal.dao.EnderecoDao;
import br.com.devmedia.appfinal.dao.FuncionarioDao;
import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.entity.Endereco;
import br.com.devmedia.appfinal.entity.Funcionario;

@Repository
public class FuncionarioDaoImpl implements FuncionarioDao, Serializable {
    private static final long serialVersionUID = 1L;
    
    private BaseDao<Funcionario, Integer> dao;
    private CargoDao cargoDao;
    private EnderecoDao enderecoDao;
    
    @Autowired
    public FuncionarioDaoImpl(DataSource dataSource, CargoDao cargoDao, EnderecoDao enderecoDao) {
        this.dao = new BaseDao<>(dataSource);
        this.cargoDao = cargoDao;
        this.enderecoDao = enderecoDao;
    }

    @Override
    public SqlParameterSource parameterSource(Funcionario funcionario) {
        return new MapSqlParameterSource()
                .addValue("nome", funcionario.getNome())
                .addValue("salario", funcionario.getSalario())
                .addValue("dataEntrada", Date.valueOf(funcionario.getDataEntrada()))
                .addValue("dataSaida", funcionario.getDataSaida() != null ? Date.valueOf(funcionario.getDataSaida()) : null)
                .addValue("id", funcionario.getId())
                .addValue("idCargo", funcionario.getCargo().getId())
                .addValue("idEndereco", funcionario.getEndereco().getId());
    }

    @Override
    public RowMapper<Funcionario> rowMapper() {
        return new RowMapper<Funcionario>() {

            @Override
            public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cargo cargo = FuncionarioDaoImpl.this.cargoDao.findById(rs.getInt("id_cargo"));
                Endereco endereco = FuncionarioDaoImpl.this.enderecoDao.findById(rs.getInt("id_endereco"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
                funcionario.setSalario(BigDecimal.valueOf(rs.getDouble("salario")));
                funcionario.setCargo(cargo);
                funcionario.setEndereco(endereco);
                
                Date dataSaida = rs.getDate("data_saida");
                if(dataSaida != null) {
                    funcionario.setDataSaida(dataSaida.toLocalDate());
                }
                
                return funcionario;
            }
        };
    }
    
    @Override
    public Funcionario save(Funcionario funcionario) {
        Number key = this.dao.save("funcionario", "id", this.parameterSource(funcionario));
        funcionario.setId(key.intValue());
        return funcionario;
    }
    
    @Override
    public int update(Funcionario funcionario) {
        StringBuilder sql = new StringBuilder("UPDATE funcionario ");
        sql.append("SET nome = :nome, salario = :salario, data_entrada = :dataEntrada, data_saida = :dataSaida, ");
        sql.append("id_endereco = :idEndereco, id_cargo = :idCargo ");
        sql.append("WHERE id = :id");
        
        return this.dao.update(sql.toString(), this.parameterSource(funcionario));
    }
    
    @Override
    public int remove(Integer id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";
        return this.dao.remove(sql , id);
    }
    
    @Override
    public Funcionario findById(Integer id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        return this.dao.findById(sql, id, this.rowMapper());
    }
    
    @Override
    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionario";
        return this.dao.findAll(sql, this.rowMapper());
    }
    
    @Override
    public List<Funcionario> findByCargo(Integer idCargo) {
        String sql = "SELECT * FROM funcionario WHERE id_cargo = :idCargo";
        return this.dao.namedQuery().query(sql, new MapSqlParameterSource("idCargo", idCargo), this.rowMapper());
    }
}