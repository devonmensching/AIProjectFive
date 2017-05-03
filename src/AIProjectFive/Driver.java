package AIProjectFive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	private static ArrayList<String> attributes;
	private static ArrayList<Instance> examples;


	// main( String args[] ) - the driver for the program  
	public static void main( String args[] ) throws FileNotFoundException 
	{
		// Prompt the user to enter a file name
		String fileName = getFileName();
		
		// Read the information from the file 
		ArrayList<String> fileData = readFile( fileName );
		createExamples( fileData );
		
		// Build the best decision tree that results from the training data
		buildDecisionTree( );
		
		// Display or print the decision tree
		displayTree( ); 
		System.out.println(calcGain(0));
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
	public static void buildDecisionTree( )
	{
		
	}
	
	public static ArrayList<Node> decisionTreeLearning( int defaultValue )
	{
		ArrayList<Node> tree = new ArrayList<Node>();
		return tree;
	}
	
	// displayTree( ) - displays a decision tree on the monitor 
	public static void displayTree( )
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
				System.out.println("This fine does not exist.");
				System.exit(0);
			}
		}
		catch( Exception e ){ }	
			
		return file;
	}
	
	// createTable( ) - Creates the table from an ArrayList of file data
	public static void createExamples( ArrayList<String> fileData)
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
		examples = new ArrayList<Instance>();
		while( count < fileData.size() )
		{
			String[] lineData = fileData.get( count ).split(",");
			int[] line = new int[lineData.length];
			for(int i = 0; i < line.length; i++)
			{
				line[i] = Integer.parseInt( lineData[i] );
			}
			examples.add( new Instance( attributes, line ) );
			count++;
		}
	}
	
	public static double calcI(  )
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
	
	public static double calcIa( int attribute )
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
			return 0;
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
	
	public static double calcGain( int attribute )
	{
		return calcI() - calcIa( attribute );
	}
}
