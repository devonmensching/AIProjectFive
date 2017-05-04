package AIProjectFive;

import java.util.ArrayList;

public class Node {

	private Node parent;
	private String attribute;
	private String label;
	private int classification;
	private ArrayList<Node> children;
	
	public Node( ) {}
	public Node( int  classification )
	{
		this.classification = classification;
	}
	
	public Node( Node parent, String attribute, String label )
	{
		this.parent = parent;
		this.attribute = attribute;
		this.label = label;
		this.classification = -1;
	}
	
	public void setParent( Node parent )
	{
		this.parent = parent;
	}
	
	public Node getParent( )
	{
		return parent;
	}
	
	public boolean isParent( Node node )
	{
		return (parent == node);
	}
	
	public void setChildren( ArrayList<Node> children )
	{
		this.children = children;
	}
	
	public ArrayList<Node> getChildren( )
	{
		return children;
	}
	
	public String getAttribute( )
	{
		return attribute;
	}
	
	public void printNode()
	{
		System.out.println("Node Details:" );
		if(attribute != null )
			System.out.println("Attribute: " + attribute);
		if(parent != null)
			System.out.println("Parent : " + parent.getAttribute());
		if(label != null)
			System.out.println("Label : " + label);
		System.out.println("Classification : " + classification);
		System.out.println("-----------------------------------");
	}
}
