package com.enumeration;

public enum GrpSanguin 
{
	A("A"), B("B"), AB("AB"), O("O");

    private String value;

    GrpSanguin(String value) { this.value = value; }    

    public String getValue() { return value; }

}
