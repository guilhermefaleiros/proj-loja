package com.guifaleiros.comercial;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guifaleiros.comercial.models.Address;
import com.guifaleiros.comercial.models.Category;
import com.guifaleiros.comercial.models.City;
import com.guifaleiros.comercial.models.Client;
import com.guifaleiros.comercial.models.Payment;
import com.guifaleiros.comercial.models.PaymentCard;
import com.guifaleiros.comercial.models.PaymentSlip;
import com.guifaleiros.comercial.models.Product;
import com.guifaleiros.comercial.models.Request;
import com.guifaleiros.comercial.models.RequestItem;
import com.guifaleiros.comercial.models.State;
import com.guifaleiros.comercial.models.enums.ETypeClient;
import com.guifaleiros.comercial.models.enums.ETypePaymentState;
import com.guifaleiros.comercial.repositories.AddressRepository;
import com.guifaleiros.comercial.repositories.CategoryRepository;
import com.guifaleiros.comercial.repositories.CityRepository;
import com.guifaleiros.comercial.repositories.ClientRepository;
import com.guifaleiros.comercial.repositories.PaymentRepository;
import com.guifaleiros.comercial.repositories.ProductRepository;
import com.guifaleiros.comercial.repositories.RequestItemRepository;
import com.guifaleiros.comercial.repositories.RequestRepository;
import com.guifaleiros.comercial.repositories.StateRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
	}

}
