package AIProjectFive;

import java.util.ArrayList;

/*
 * Node.java
 * The Node Java file contains the code for the Node object.
 * A node object represents a single node in the decision
 * tree. 
 * 
 * by Devon Mensching & Nick Polanco 
 * 
 */

public class Node {

	private Node parent;
	private String attribute;
	private String label;
	private int classification;
	private ArrayList<Node> children = new ArrayList<Node>();
	
	public Node( ) {}
	
	public Node( Node parent, int  classification )
	{
		this.parent = parent;
		this.classification = classification;
		this.label = "none";
	}
	
	public Node( Node parent, int  classification, String label )
	{
		this.parent = parent;
		this.classification = classification;
		this.label = label;
	}
	
	public Node( Node parent, String attribute, String label )
	{
		this.parent = parent;
		this.attribute = attribute;
		this.label = label;
		this.classification = -1;
	}
	
	// setParent( ) - sets the parent of the Node
	public void setParent( Node parent )
	{
		this.parent = parent;
	}
	
	// getParent( ) - returns the parent of the Node
	public Node getParent( )
	{
		return parent;
	}
	
	// isParent( ) - returns true if node is parent of Node
	public boolean isParent( Node node )
	{
		return (parent == node);
	}
	
	// setChildren( ) - sets the children of the Node
	public void setChildren( ArrayList<Node> children )
	{
		this.children = children;
	}
	
	// getChildren( ) - returns the children of the Node
	public ArrayList<Node> getChildren( )
	{
		return children;
	}
	
	// addChild( ) - adds a child to children of Node
	public void addChild( Node child )
	{
		children.add( child );
	}
	
	// getAttribute( ) - returns the attribute of the Node
	public String getAttribute( )
	{
		return attribute;
	}
	
	// getCalssification - returns the classficiation of the Node
	public int getClassification( )
	{
		return classification;
	}
	
	// getLabel( ) - returns the label of the Node
	public String getLabel( )
	{
		return label;
	}
}
