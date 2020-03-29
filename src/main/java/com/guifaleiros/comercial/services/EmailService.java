package com.guifaleiros.comercial.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.guifaleiros.comercial.models.Request;

@Service
public interface EmailService {
	
	void sendOrderConfirmationEmail(Request request);
	
	void sendEmail(SimpleMailMessage msg);
}
