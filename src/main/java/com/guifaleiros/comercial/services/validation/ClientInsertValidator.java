package com.guifaleiros.comercial.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.guifaleiros.comercial.controllers.exceptions.FieldMessageError;
import com.guifaleiros.comercial.dto.NewClientDTO;
import com.guifaleiros.comercial.models.Client;
import com.guifaleiros.comercial.models.enums.ETypeClient;
import com.guifaleiros.comercial.repositories.ClientRepository;
import com.guifaleiros.comercial.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientDTO> {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessageError> list = new ArrayList<>();
		
		if(objDto.getType().equals(ETypeClient.PESSOA_FISICA.getCod()) && !
				BR.isValidCPF(objDto.getCpfOrCnpj())) {
			System.out.println(objDto.getType());
			System.out.println(objDto.getCpfOrCnpj());
			list.add(new FieldMessageError("cpfOrCnpj", "Invalid CPF"));
		}
		
		if(objDto.getType().equals(ETypeClient.PESSOA_JURIDICA.getCod()) && !
				BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessageError("cpfOrCnpj", "Invalid CNPJ"));
		}
		
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessageError("email", "E-mail already exists"));
		}

		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessageError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}