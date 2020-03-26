package com.guifaleiros.comercial.models.enums;

public enum ETypeClient {
	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String description;
	
	private ETypeClient(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ETypeClient toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(ETypeClient c : ETypeClient.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
