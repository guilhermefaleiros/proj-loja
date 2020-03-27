package com.guifaleiros.comercial.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guifaleiros.comercial.controllers.utils.URL;
import com.guifaleiros.comercial.dto.CategoryDTO;
import com.guifaleiros.comercial.dto.ProductDTO;
import com.guifaleiros.comercial.models.Category;
import com.guifaleiros.comercial.models.Product;
import com.guifaleiros.comercial.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product response = productService.find(id);
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		System.out.println(name);
		Page<Product> response = productService.search(URL.decodeParam(name), URL.decodeIntList(categories), 
				page, linesPerPage, orderBy, direction);
		
		Page<ProductDTO> listDto = response.map(obj -> new ProductDTO(obj));							
		return ResponseEntity.ok().body(listDto);
	}
	
	public static List<Integer> decodeIntList(String string){
		String[] s = string.split(",");
		return Arrays.asList(s).stream().map(obj->  Integer.parseInt(obj)).collect(Collectors.toList());
	}
}
