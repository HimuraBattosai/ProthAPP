package com.enumeration;

public enum TypeProthese 
{
	INF("inf"), SUP("sup");

    private String value;

    TypeProthese(String value) { this.value = value; }    

    public String getValue() { return value; }
}
