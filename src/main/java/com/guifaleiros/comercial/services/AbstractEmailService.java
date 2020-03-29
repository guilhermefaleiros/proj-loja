package com.guifaleiros.comercial.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.guifaleiros.comercial.models.Request;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Request request) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromRequest(request);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request request) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(request.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + request.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;
	}
}
