package br.com.devmedia.appfinal.validator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.devmedia.appfinal.entity.Funcionario;

public class FuncionarioValidator implements Validator, Serializable {
    private static final long serialVersionUID = 1L;
    private EnderecoValidator enderecoValidator;

    public FuncionarioValidator(EnderecoValidator enderecoValidator) {
        this.enderecoValidator = enderecoValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Funcionario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "error.nome", "O campo nome é obrigatório!");
        
        Funcionario funcionario = (Funcionario) target;
        if(funcionario.getSalario() != null) {
            if(funcionario.getSalario().doubleValue() < BigDecimal.ZERO.doubleValue()) {
                errors.rejectValue("nome", "error.salario", "O salário não deve ser negativo!");
            }
        } else {
            errors.rejectValue("nome", "error.salario", "O campo Salário é obrigatório!");
        }
        
        if(funcionario.getDataEntrada() != null) {
            if(funcionario.getDataEntrada().isAfter(LocalDate.now())) {
                errors.rejectValue("dataEntrada", "error.dataEntrada", "A data entrada deve ser anterior ou igual à data atual!");
            }
        } else {
            errors.rejectValue("dataEntrada", "error.dataEntrada", "O campo Data Entrada é obrigatório!");
        }
        
        if(funcionario.getDataSaida() != null) {
            if(funcionario.getDataSaida().isBefore(funcionario.getDataEntrada())) {
                errors.rejectValue("dataSaida", "error.dataSaida", "A data Saída deve ser posterior ou igual à Data Entrada!");
            }
        }
        
        if(funcionario.getCargo() == null) {
            errors.rejectValue("cargo", "error.cargo", "O campo Cargo é obrigatório!");
        }
        
        ValidationUtils.invokeValidator(this.enderecoValidator, funcionario.getEndereco(), errors);
    }
}