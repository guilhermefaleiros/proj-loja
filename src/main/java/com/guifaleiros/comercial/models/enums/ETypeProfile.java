package com.guifaleiros.comercial.models.enums;

public enum ETypeProfile {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private int cod;
	private String description;
	
	private ETypeProfile(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ETypeProfile toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(ETypeProfile c : ETypeProfile.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
