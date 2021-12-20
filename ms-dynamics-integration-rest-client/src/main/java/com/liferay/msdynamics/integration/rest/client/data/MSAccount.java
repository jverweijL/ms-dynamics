package com.liferay.msdynamics.integration.rest.client.data;

import com.google.gson.JsonElement;

public class MSAccount {

	private String name;
	private String telephone1;
	private String address1_city;
	private String emailaddress1;

	public MSAccount() {
		super();
	}

	public MSAccount(String name, String phone, String city, String mail) {
		super();
		this.name = name;
		this.telephone1 = phone;
		this.address1_city = city;
		this.emailaddress1 = mail;
	}

	public MSAccount(JsonElement account) {
		super();
		
		this.name = getJsonElementField(account, "name");
		this.telephone1 = getJsonElementField(account, "telephone1");
		this.address1_city = getJsonElementField(account, "address1_city");
		this.emailaddress1 = getJsonElementField(account, "emailaddress1");

		
	}
	
	private String getJsonElementField(JsonElement account, String field) {
		JsonElement jsonElement = account.getAsJsonObject().get(field);
		
		String result = "-";
		
		if (!jsonElement.isJsonNull()) {
			result = jsonElement.getAsString();
		} 
		
		return result;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getAddress1_city() {
		return address1_city;
	}

	public void setAddress1_city(String address1_city) {
		this.address1_city = address1_city;
	}

	public String getEmailaddress1() {
		return emailaddress1;
	}

	public void setEmailaddress1(String emailaddress1) {
		this.emailaddress1 = emailaddress1;
	}

	@Override
	public String toString() {
		return "MSAccount [name=" + name + ", phone=" + telephone1 + ", city=" + address1_city + ", mail=" + emailaddress1 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address1_city == null) ? 0 : address1_city.hashCode());
		result = prime * result + ((emailaddress1 == null) ? 0 : emailaddress1.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((telephone1 == null) ? 0 : telephone1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MSAccount other = (MSAccount) obj;
		if (address1_city == null) {
			if (other.address1_city != null)
				return false;
		} else if (!address1_city.equals(other.address1_city))
			return false;
		if (emailaddress1 == null) {
			if (other.emailaddress1 != null)
				return false;
		} else if (!emailaddress1.equals(other.emailaddress1))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (telephone1 == null) {
			if (other.telephone1 != null)
				return false;
		} else if (!telephone1.equals(other.telephone1))
			return false;
		return true;
	}

}
