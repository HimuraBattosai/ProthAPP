package com.enumeration;

public enum Sexe 
{
    MALE("male"), FEMALE("female");

    private String value;

    Sexe(String value) { this.value = value; }    

    public String getValue() { return value; }
}
