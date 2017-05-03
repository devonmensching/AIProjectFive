package AIProjectFive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	private static ArrayList<String> tableHeader;
	private static int[][] table;
	

	// main( String args[] ) - the driver for the program  
	public static void main( String args[] ) throws FileNotFoundException 
	{
		// Prompt the user to enter a file name
		String fileName = getFileName();
		
		// Read the information from the file 
		ArrayList<String> fileData = readFile( fileName );
		createTable( fileData );
		
		// Build the best decision tree that results from the training data
		buildDecisionTree( );
		
		// Display or print the decision tree
		displayTree( ); 
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
		tableHeader = new ArrayList<String>();
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
	public static void createTable( ArrayList<String> fileData)
	{
		// Create tableHeader ArrayList from file data
		tableHeader = new ArrayList<String>();
		int count = 0;
		while( !fileData.get( count ).equals("") )
		{
			tableHeader.add( fileData.get( count ) );
			count++;
		}
		
		// Skip over blank line
		count++;
		
		// Create int[][] table from file data
		table = new int[fileData.size()-count][tableHeader.size()];
		int tableRow = 0;
		while( count < fileData.size() )
		{
			String[] line = fileData.get( count ).split(",");
			for(int i = 0; i < line.length; i++)
			{
				table[tableRow][i] = Integer.parseInt( line[i] );
			}
			tableRow++;
			count++;
		}
	}
	
}
