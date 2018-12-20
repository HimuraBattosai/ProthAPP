package com.enumeration;

public enum Payement 
{
	CASH("CASH"), CHEQUE("CHEQUE"), VISA("VISA"), MASTERCARD("MASTERCARD");

    private String value;

    Payement(String value) { this.value = value; }    

    public String getValue() { return value; }

}
