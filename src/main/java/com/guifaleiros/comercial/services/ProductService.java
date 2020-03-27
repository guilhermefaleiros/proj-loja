package com.guifaleiros.comercial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guifaleiros.comercial.models.Category;
import com.guifaleiros.comercial.models.Product;
import com.guifaleiros.comercial.repositories.CategoryRepository;
import com.guifaleiros.comercial.repositories.ProductRepository;
import com.guifaleiros.comercial.services.exceptions.ObjectNotFoundException;



@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Product find(Integer id){
		Optional<Product> response = this.productRepository.findById(id);
		return response.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		
		List<Category> categories = this.categoryRepository.findAllById(ids);
		return productRepository.search(name, categories, pageRequest);
	}
}
