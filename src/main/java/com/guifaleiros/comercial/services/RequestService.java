package com.guifaleiros.comercial.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guifaleiros.comercial.models.PaymentSlip;
import com.guifaleiros.comercial.models.Request;
import com.guifaleiros.comercial.models.RequestItem;
import com.guifaleiros.comercial.models.enums.ETypePaymentState;
import com.guifaleiros.comercial.repositories.PaymentRepository;
import com.guifaleiros.comercial.repositories.ProductRepository;
import com.guifaleiros.comercial.repositories.RequestItemRepository;
import com.guifaleiros.comercial.repositories.RequestRepository;
import com.guifaleiros.comercial.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private SlipService slipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RequestItemRepository requestItemRepository;
	
	public Request find(Integer id){
		Optional<Request> response = this.requestRepository.findById(id);
		return response.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Request.class.getName()));
	}
	
	public Request insert(Request obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setState(ETypePaymentState.PENDING);
		obj.getPayment().setRequest(obj);
		if(obj.getPayment() instanceof PaymentSlip) {
			PaymentSlip pay = (PaymentSlip) obj.getPayment();
			slipService.fillPaymentSlip(pay, obj.getInstant());
		}
		
		obj = this.requestRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		
		for(RequestItem ip : obj.getItens()) {
			ip.setDiscount(0.0);
			ip.setPrice(productRepository.findById(ip.getProduct().getId()).get().getPrice());
			ip.setRequest(obj);
		}
		this.requestItemRepository.saveAll(obj.getItens());
		return obj;
		
	}
}
