package com.bai.ps.model;

public enum StatusValue {

	addMessage("Dodaj"),
	editMessage("Edytuj"),
	removeMessage("Usun"),
	addPermission("Uprawnienia");
	
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
