package AIProjectFive;

import java.util.ArrayList;

public class Instance {
	
	private ArrayList<String> attributes;
	private ArrayList<Integer> values;
	
	public Instance( ArrayList<String> attributes, ArrayList<Integer> values )
	{
		this.attributes = attributes;
		this.values = values;
	}
	
	public void setAttributes( ArrayList<String> attributes )
	{
		this.attributes = attributes;
	}
	
	public ArrayList<String> getAttributes( )
	{
		return attributes;
	}
	
	public String getAttribute( int i )
	{
		return attributes.get(i);
	}
	
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
	

	public void setValues( ArrayList<Integer> values )
	{
		this.values = values;
	}
	
	public ArrayList<Integer> getValues( )
	{
		return values;
	}
	
	public int getValue( int i )
	{
		return values.get(i);
	}
	
	public int getClassification( )
	{
		if(values.size() > 0)
			return values.get(values.size()-1);
		return -1;
	}
	
	public void removeAttributeValue( int index )
	{
		System.out.println("INDEX = " + index);
		values.remove( index );
	}
}
