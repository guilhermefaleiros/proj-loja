package com.guifaleiros.comercial.models;

import javax.persistence.Entity;

import com.guifaleiros.comercial.models.enums.ETypePaymentState;

@Entity
public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer plotsNumber;

	public PaymentCard() {

	}

	public PaymentCard(Integer id, ETypePaymentState state, Request request, Integer plotsNumber) {
		super(id, state, request);
		this.plotsNumber = plotsNumber;
	}

	public Integer getPlotsNumber() {
		return plotsNumber;
	}

	public void setPlotsNumber(Integer plotsNumber) {
		this.plotsNumber = plotsNumber;
	}
}
