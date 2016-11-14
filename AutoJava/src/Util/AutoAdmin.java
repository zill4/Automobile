package Util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoAdmin implements FileOperator
{
	/*  AutoAdmin 
	 * -Access to admin data file(s)
	 * -Reads admin file, storing data in ArrayList, puts(ArrayList) in a HashMap
	 * -Overlays HashMaps, creates a linkedList of HashMaps that points to the objects that instantiate each other.
	 * -I.E. Ford instantiates Year, Year instantiates Model, Model > OptionSet(s), OptionSet(s) > Options.
	 * Lets Begin.
	 */
	private String fileName;
	private int fileLines[];
	private int coutIndex;
	private int fileSize;
	//Make>Model>Package
	//Mode>Year>Year>Package>Options
	private ArrayList <String> makeList = new ArrayList<>();
	private ArrayList <String> modelList = new ArrayList<>();
	private ArrayList <String> packageList = new ArrayList<>();
	private HashMap <String, String> autoMap = new HashMap<>();
	AutoAdmin() throws IOException
	{
		fileName = "/Users/Justin/documents/workspace/AutoJava/bin/AutoAdmin.txt";
		fileLines = numLines(fileName);
		traverseMap();
	}
	public int[] numLines(String fileName) throws IOException
	{
	InputStream fin = new BufferedInputStream(new FileInputStream(fileName)); //read in file
		
		try
		{
		//reads through list of all chars,to find \n's and count them
		byte[] b = new byte[1024]; 	
		 //counter for read
		int numLine = 0;
		int count[] = new int[1024];	
		int readChar = 0;
		//t/f empty
		boolean empty = true; 
		//reading till EOF signature,
			while((readChar = fin.read(b)) != -1)
			{
				empty = false;
		//read through line, counts each \n char
				for(int i = 0; i< readChar; i++)
				{
					if(b[i]==',')count[numLine]++; //line 1: 5, line 2: 3, and so on.
					if(b[i]=='\n')numLine++;
				}
			}	
			fileSize = numLine;
		//returns either null if empty or count if not
		return (count[0] == 0 && !empty) ? null : count;
		}
		finally
		{
			fin.close();
		}
	} 
	
	public void openFile() throws Exception
	{
		/*
			FileReader adminFile = new FileReader(fileName);
			BufferedReader readFile = new BufferedReader(adminFile);
		*/
		
		
	}
	public void traverseMap()
	{
		makeList.add("Ford");
		makeList.add("Tesla");
		makeList.add("Toyota");
		modelList.add("Model S");
		modelList.add("Model T");
		modelList.add("Prius");

		autoMap.put(makeList.get(1), modelList.get(1));
		autoMap.put(makeList.get(2), modelList.get(2));
		
		System.out.println(autoMap.toString());
	}
	public static void main(String[] args) throws IOException
	{
		System.out.println("Don't worry this is only a test");
		AutoAdmin auto = new AutoAdmin();
	}
}
