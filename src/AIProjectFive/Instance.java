package AIProjectFive;

import java.util.ArrayList;

/*
 * Instance.java
 * The Instance Java file contains the code for an Instance object.
 * An instance object contains a list of attributes and correspond
 * values to the attributs. These values represent true and false
 * for each attribute. 
 * 
 * by Devon Mensching & Nick Polanco 
 * 
 */

public class Instance {
	
	private ArrayList<String> attributes;
	private ArrayList<Integer> values;
	
	public Instance( ArrayList<String> attributes, ArrayList<Integer> values )
	{
		this.attributes = attributes;
		this.values = values;
	}
	
	// setAttributes( ) - sets the list of attributes
	public void setAttributes( ArrayList<String> attributes )
	{
		this.attributes = attributes;
	}
	
	// getAttributes( ) - returns a list of attributes
	public ArrayList<String> getAttributes( )
	{
		return attributes;
	}
	
	// getAttribute( ) - returns an attribute at specific index
	public String getAttribute( int i )
	{
		return attributes.get(i);
	}
	
	// findAttributeValue( ) - finds the value of a specific attribute
	public int findAttributeValue( String attribute )
	{
		int value = -1;
		for(int i = 0; i < attributes.size()-1; i++)
		{
			if(attributes.get(i).equals(  attribute))
			{
				value = values.get(i);
			}
		}
		return value;
	}
	
	// setValues( ) - sets values 
	public void setValues( ArrayList<Integer> values )
	{
		this.values = values;
	}
	
	// getValues( ) - returns teh ArrayList of values
	public ArrayList<Integer> getValues( )
	{
		return values;
	}
	
	// getValue( ) - returns a value at a specific index
	public int getValue( int i )
	{
		return values.get(i);
	}
	
	// getCalssification( ) - returns the classification 
	public int getClassification( )
	{
		if(values.size() > 0)
			return values.get(values.size()-1);
		return -1;
	}
	
	// getLabel( ) - returns the label for the instance
	public String getLabel( String attribute )
	{
		for(int i = 0; i < attributes.size(); i++)
		{
			if(attributes.get(i).equals( attribute ))
			{
				if( values.get(i) == 1)
				{
					return "Yes";
				}
				return "No";
			}
		}
		return "none";
	}
	
}
