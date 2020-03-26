package com.guifaleiros.comercial.models;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guifaleiros.comercial.models.enums.ETypePaymentState;

@Entity
public class PaymentSlip extends Payment {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date payday;
	
	public PaymentSlip() {
		
	}

	public PaymentSlip(Integer id, ETypePaymentState state, Request request, Date dueDate, Date payday) {
		super(id, state, request);
		this.dueDate = dueDate;
		this.payday = payday;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayday() {
		return payday;
	}

	public void setPayday(Date payday) {
		this.payday = payday;
	}
	
}
