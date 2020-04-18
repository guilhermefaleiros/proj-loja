package com.guifaleiros.comercial.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.guifaleiros.comercial.models.enums.ETypeProfile;
import com.guifaleiros.comercial.repositories.AddressRepository;
import com.guifaleiros.comercial.repositories.CategoryRepository;
import com.guifaleiros.comercial.repositories.CityRepository;
import com.guifaleiros.comercial.repositories.ClientRepository;
import com.guifaleiros.comercial.repositories.PaymentRepository;
import com.guifaleiros.comercial.repositories.ProductRepository;
import com.guifaleiros.comercial.repositories.RequestItemRepository;
import com.guifaleiros.comercial.repositories.RequestRepository;
import com.guifaleiros.comercial.repositories.StateRepository;

@Service
public class DBService {
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
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Jogos");
		Category cat4 = new Category(null, "Cama, mesa e banho");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 8000.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de Escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour",100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
	
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p4, p3));
		
		p1.getCategories().add(cat1);
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().add(cat1);
		p4.getCategories().addAll(Arrays.asList(cat2, cat3));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCities().add(c1);
		est2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "Maria@gmail.com", "70876660103", ETypeClient.PESSOA_FISICA, pe.encode("123"));
		Client cli2 = new Client(null, "Ana Silva", "ana@gmail.com", "70876660103", ETypeClient.PESSOA_FISICA, pe.encode("123"));
		cli2.addProfile(ETypeProfile.ADMIN);
		cli1.getPhones().addAll(Arrays.asList("982498044", "982018328"));
		cli2.getPhones().addAll(Arrays.asList("982498044", "982018328"));
		
		Address a1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "74360060", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "74360061", cli1, c2);
		Address a3 = new Address(null, "Avenida Matos News", "101", "Sala 800", "Centro", "74360061", cli2, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(a1, a2));
		cli2.getAddresses().addAll(Arrays.asList(a3));

		
		this.clientRepository.saveAll(Arrays.asList(cli1, cli2));
		this.addressRepository.saveAll(Arrays.asList(a1, a2, a3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Request ped1 = new Request(null, sdf.parse("30/09/2019 10:32"), cli1, a1);
		Request ped2 = new Request(null, sdf.parse("10/10/2019 19:35"), cli1, a2 );
		
		Payment pgto1 = new PaymentCard(null, ETypePaymentState.SETTLED, ped1, 6);
		ped1.setPayment(pgto1);
		
		Payment pgto2 = new PaymentSlip(null, ETypePaymentState.PENDING, ped2, sdf.parse("20/10/2019 00:00"), null);
		ped2.setPayment(pgto2);
		
		cli1.getRequests().addAll(Arrays.asList(ped1, ped2));
		
		this.paymentRepository.saveAll(Arrays.asList(pgto1, pgto2));
		this.requestRepository.saveAll(Arrays.asList(ped1, ped2));
		
		RequestItem ip1 = new RequestItem(ped1, p1, 0.00, 1, 2000.00);
		RequestItem ip2 = new RequestItem(ped1, p3, 0.00, 2, 80.00);
		RequestItem ip3 = new RequestItem(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		this.requestItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}
}
