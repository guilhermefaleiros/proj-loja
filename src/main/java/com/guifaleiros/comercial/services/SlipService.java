package com.guifaleiros.comercial.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.guifaleiros.comercial.models.PaymentSlip;

@Service
public class SlipService {
	
	public void fillPaymentSlip(PaymentSlip pay, Date instant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(cal.getTime());
	}
}
