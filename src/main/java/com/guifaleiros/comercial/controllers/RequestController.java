package com.guifaleiros.comercial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guifaleiros.comercial.models.Request;
import com.guifaleiros.comercial.services.RequestService;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
	
	@Autowired
	RequestService requestService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Request> find(@PathVariable Integer id) {
		Request response = requestService.find(id);
		return ResponseEntity.ok().body(response);
	}
}
