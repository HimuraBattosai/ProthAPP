package com.enumeration;

public enum Expedition 
{
	EXPRESS("EXPRESS"), AMANA("AMANA");

    private String value;

    Expedition(String value) { this.value = value; }    

    public String getValue() { return value; }

}
