package com.guifaleiros.comercial.models.enums;

public enum ETypePaymentState {
	
	PENDING(1, "Pendente"),
	SETTLED(2, "Quitado"),
	CANCELED(3, "Cancelado");
	
	private int cod;
	private String description;
	
	private ETypePaymentState(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ETypePaymentState toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(ETypePaymentState c : ETypePaymentState.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
