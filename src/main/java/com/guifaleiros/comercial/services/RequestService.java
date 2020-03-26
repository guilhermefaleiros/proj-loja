package com.guifaleiros.comercial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guifaleiros.comercial.models.Request;
import com.guifaleiros.comercial.repositories.RequestRepository;
import com.guifaleiros.comercial.services.exceptions.ObjectNotFoundException;



@Service
public class RequestService {
	
	@Autowired
	RequestRepository requestRepository;
	
	public Request find(Integer id){
		Optional<Request> response = this.requestRepository.findById(id);
		return response.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Request.class.getName()));
	}
}
