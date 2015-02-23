package com.UndefinedParameter.app.core;
import com.fasterxml.jackson.annotation.JsonProperty;


public class OrganizationType {

	public String typeName;
	
	public OrganizationType()
	{
		
	}
	
	public void setTypeName(String name)
	{
		typeName = name;
	}
	
	public String getTypeName()
	{
		return typeName;
	}
}
