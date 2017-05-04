package AIProjectFive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver {
	
	private static ArrayList<String> attributes;
	private static ArrayList<Node> tree = new ArrayList<Node>();

	// main( String args[] ) - the driver for the program  
	public static void main( String args[] ) throws FileNotFoundException 
	{
		// Prompt the user to enter a file name
		String fileName = getFileName();
		
		// Read the information from the file 
		ArrayList<String> fileData = readFile( fileName );
		ArrayList<Instance> examples = createExamples( fileData );
		
		// Build the best decision tree that results from the training data
		Node root = new Node();
		buildDecisionTree(examples, attributes, 0, root);
		
		// Display or print the decision tree
		//displayTree( ); 
		for(int i = 0; i < tree.size(); i++)
		{
			tree.get(i).printNode();
		}
	}
	
	// getFileName( ) - returns file name user has entered
	public static String getFileName( )
	{
		System.out.println("Welcome to the Decision Tree Maker program!");
		System.out.println("Please enter a file name: ");
		Scanner input = new java.util.Scanner(System.in);	
		String fileName = input.nextLine();
		input.close();
		return fileName;
	}
	
	// readFile - returns an ArrayList filled with file data
	public static ArrayList<String> readFile( String fileName ) throws FileNotFoundException
	{
		// Open the file
		File file = openFile( fileName );
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
		
		// Create an ArrayList<String> of Strings representing the table data
		ArrayList<String> fileLines = new ArrayList<String>();
		while( scanner.hasNext() )
		{
			fileLines.add( scanner.nextLine( ) );
		}
		scanner.close();
		return fileLines;
	}
	
	// buildDecisionTree( ) - builds the best decision tree from data
	public static Node buildDecisionTree(ArrayList<Instance> examples, ArrayList<String> attributes, int defualt, Node parent)
	{	
		if(examples.size() == 0)
		{
			return new Node( defualt ) ; 
		}
		else if( isSameClassification( examples ) )
		{
			return new Node( examples.get(0).getClassification( ) );
		}
		else if( attributes.size() == 0 )
		{
			return new Node ( majorityClassification( examples ) );
		}
		else
		{
			String best = bestAttribute(attributes, examples);
			Node root = new Node(parent, best, "");
			tree.add( root );
			int m = majorityClassification( examples );
			for(int i = 0; i < 2; i++)
			{
				ArrayList<Instance> examplesi = findExamples( attributeIndex(best), i, examples );
				Node subtree = buildDecisionTree(examplesi, newAttributes( attributes, best ), m, root);
				tree.add( subtree );
			}
		}
		return new Node();
	}
	
	public static int attributeIndex( String attribute )
	{
		for(int i = 0; i < attributes.size(); i++)
		{
			if(attributes.get(i).equals(attribute))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static boolean isSameClassification(ArrayList<Instance> examples)
	{
		int classification = examples.get(0).getClassification();
		for(int i = 1; i < examples.size(); i++)
		{
			if(examples.get(i).getClassification() != classification)
			{
				return false;
			}
		}
		return true;
	}
	
	public static int majorityClassification( ArrayList<Instance> examples)
	{
		int zero = 0;
		int one = 0;
		for(int i = 0; i < examples.size(); i++)
		{
			if(examples.get(i).getClassification() == 0)
			{
				zero++;
			}
			else
			{
				one++;
			}
		}
		if(zero >= one)
		{
			return 0;
		}
		return 1;
	}
	
	public static String bestAttribute( ArrayList<String> newAttributes, ArrayList<Instance> examples)
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i = 0; i < attributes.size()-1; i++)
		{
			for(int j = 0; j < newAttributes.size(); j++)
			{
				if(attributes.get(i).equals(newAttributes.get(j)))
				{
					indexes.add(i);
				}
			}
		}
		
		String best = "";
		double bestGain = -1;
		for(int i = 0; i < indexes.size(); i++)
		{
			double currentGain = calcGain(indexes.get(i), examples);
			if(currentGain > bestGain)
			{
				best = attributes.get(indexes.get(i));
				bestGain = currentGain;
			}
		}
		return best;
	}
	
	public static ArrayList<Instance> findExamples( int attribute, int value, ArrayList<Instance> examples)
	{
		ArrayList<Instance> newExamples = new ArrayList<Instance>();
		for(int i = 0; i < examples.size(); i++)
		{
			
			if(examples.get(i).getValue( attribute ) == value)
			{
				Instance instance = examples.get( i );
				newExamples.add( instance );
			}
		}
		return newExamples;
	}
	
	public static ArrayList<String> newAttributes(ArrayList<String> attributes, String best)
	{
		ArrayList<String> newAttributes = new ArrayList<String>();
		for(int i = 0; i < attributes.size(); i++)
		{
			String currAttribute = attributes.get(i);
			if( !currAttribute.equals( best ) )
			{
				newAttributes.add( currAttribute );
			}
		}
		return newAttributes;
	}
	
	// displayTree( ) - displays a decision tree on the monitor 
	public static void displayTree( )
	{
		System.out.println(tree.get(0).getAttribute());
		printChildren( tree.get(0));
	}
	
	public static void printChildren( Node node )
	{
		
	}
	// openFile( String fileName ) - opens a file 
	public static File openFile( String fileName )
	{
		File file = null;
		try
		{
			file = new File( fileName );
			if( !file.exists() )
			{
				System.out.println("This file does not exist.");
				System.exit(0);
			}
		}
		catch( Exception e ){ }	
			
		return file;
	}
	
	// createTable( ) - Creates the table from an ArrayList of file data
	public static ArrayList<Instance> createExamples( ArrayList<String> fileData)
	{
		// Create tableHeader ArrayList from file data
		attributes = new ArrayList<String>();
		int count = 0;
		while( !fileData.get( count ).equals("") )
		{
			attributes.add( fileData.get( count ) );
			count++;
		}
		
		// Skip over blank line
		count++;
		
		// Create ArrayList of examples 
		ArrayList<Instance> examples = new ArrayList<Instance>();
		while( count < fileData.size() )
		{
			String[] lineData = fileData.get( count ).split(",");
			ArrayList<Integer> line = new ArrayList<Integer>();
			for(int i = 0; i < lineData.length; i++)
			{
				line.add( Integer.parseInt( lineData[i] ));
			}
			examples.add( new Instance( attributes, line ) );
			count++;
		}
		return examples;
	}
	
	// calcI( ) - calculates the information 
	public static double calcI( ArrayList<Instance> examples )
	{
		double p = 0.0;
		double n = 0.0;
		for(int i = 0; i < examples.size(); i++)
		{
			if(examples.get( i ).getClassification() == 1)
			{
				p++;
			}
			else
			{
				n++;
			}
			
		}
		double total = p + n;
		return -(p/total)*(Math.log(p/total)/Math.log(2))-(n/total)*(Math.log(n/total)/Math.log(2));
	}
	
	public static double calcIa( int attribute, ArrayList<Instance> examples)
	{
		double p = 0.0;
		double pTrue = 0.0;
		double pFalse = 0.0;
		double n = 0.0;
		double nTrue = 0.0;
		double nFalse = 0.0;
		for(int i = 0; i < examples.size(); i++)
		{	
			if(examples.get( i ).getValue( attribute ) == 1)
			{
				
				p++;
				if(examples.get( i ).getClassification() == 1)
				{
					pTrue++;
				}
				else
				{
					pFalse++;
				}
			}
			else
			{
				n++;
				if(examples.get( i ).getClassification() == 1)
				{
					nTrue++;
				}
				else
				{
					nFalse++;
				}
			}
			
		}
		if(p == 0 || n == 0)
		{
			return 0;
		}
		double total = p + n;
		double first;
		if(pTrue == 0 )
		{
			first = -(pFalse/p)*(Math.log(pFalse/p)/Math.log(2));
		}
		else if(pFalse == 0)
		{
			first = -(pTrue/p)*(Math.log(pTrue/p)/Math.log(2));
		}
		else
		{
			first = -(pTrue/p)*(Math.log(pTrue/p)/Math.log(2))-(pFalse/p)*(Math.log(pFalse/p)/Math.log(2));
		}
		
		double second; 
		if(nTrue == 0)
		{
			second = -(nFalse/n)*(Math.log(nFalse/n)/Math.log(2));
		}
		else if( nFalse == 0 )
		{
			second = -(nTrue/n)*(Math.log(nTrue/n)/Math.log(2));
		}
		else
		{
			second = -(nTrue/n)*(Math.log(nTrue/n)/Math.log(2))-(nFalse/n)*(Math.log(nFalse/n)/Math.log(2));
		}
		
		return (p/total)* first + (n/total) * second;
	}
	
	public static double calcGain( int attribute, ArrayList<Instance> examples)
	{
		return calcI( examples ) - calcIa( attribute, examples);
	}
}
