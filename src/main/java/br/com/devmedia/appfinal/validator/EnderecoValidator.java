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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.logradouro", "error.logradouro", "O campo Logradouro é obrigatório!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro", "error.bairro", "O campo Bairro é obrigatório!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cidade", "error.cidade", "O campo Cidade é obrigatório!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.estado", "error.estado", "O campo Estado é obrigatório!");
        
        Endereco endereco = (Endereco) target;
        if(endereco.getNumero() != null) {
            if(endereco.getNumero() < 0) {
                errors.rejectValue("endereco.numero", "error.numero", "O campo Número não pode ser negativo!");
            }
        } else {
            errors.rejectValue("endereco.numero", "error.numero", "O campo Número é obrigatório!");
        }
    }
}