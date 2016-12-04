package com.bai.ps.model;

public enum StatusValue {

	addMessage("Dodaj"),
	editMessage("EdytujWiadomosc"),
	removeMessage("Usun"),
	addPermission("Uprawnienia"),
	removePermisson("usunUprawnienia"),
	lock("blokuj");
	
	private final String name;
	
	StatusValue(final String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

}
