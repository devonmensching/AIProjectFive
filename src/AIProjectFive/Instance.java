package AIProjectFive;

import java.util.ArrayList;

public class Instance {
	
	private ArrayList<String> attributes;
	private int[] values;
	
	public Instance( ArrayList<String> attributes, int[] values )
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
	

	public void setValues( int[] values )
	{
		this.values = values;
	}
	
	public int[] getValues( )
	{
		return values;
	}
	
	public int getValue( int i )
	{
		return values[i];
	}
	
	public int getClassification( )
	{
		return values[values.length-1];
	}
}
