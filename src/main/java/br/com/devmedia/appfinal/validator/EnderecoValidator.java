package br.com.devmedia.appfinal.validator;

import java.io.Serializable;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.devmedia.appfinal.entity.Endereco;

public class EnderecoValidator implements Validator, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean supports(Class<?> clazz) {
        return Endereco.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.logradouro", "error.logradouro");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro", "error.bairro");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cidade", "error.cidade");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.estado", "error.estado");
        
        Endereco endereco = (Endereco) target;
        if(endereco.getNumero() != null) {
            if(endereco.getNumero() < 0) {
                errors.rejectValue("endereco.numero", "error.numero.negativo");
            }
        } else {
            errors.rejectValue("endereco.numero", "error.numero");
        }
    }
}