package com.mercury.dataBase.connection;

public class QueryFormater
{
	public static String fun(String field_name, String data, String inp_str) 
	{
	    String ret_str = String.format("[{\"%s\":%s}]", field_name, data);
	    return inp_str+ret_str;
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		String abcAsVariable ="abc";
		String str="";
		
		str+="name:"+"raktim,";
		str+="id:"+"123,";
		
		System.out.println("str: "+str);
	}
}