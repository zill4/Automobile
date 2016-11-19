package Util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileAdmin implements FileOperations
{
	/*  AutoAdmin 
	 * -Access to admin data file(s)
	 * -Reads admin file, storing data in ArrayList, puts(ArrayList) in a HashMap
	 * -Overlays HashMaps, creates a linkedList of HashMaps that points to the objects that instantiate each other.
	 * -I.E. Ford instantiates Year, Year instantiates Model, Model > OptionSet(s), OptionSet(s) > Options.
	 * Lets Begin.
	 */
	File directory = new File("/Users/Justin/documents/workspace/Autojava/bin");
	File[] listOfFiles = directory.listFiles();
	private Scanner userInput;
	private String fileName;
	private int fileSize;
	private HashMap <String, String> autoMap = new HashMap<>();
	private HashMap <String, String> modelYearMap = new HashMap<>();
	private HashMap <String, Float> modelPriceMap = new HashMap<>();
	private BufferedReader readFile;
	FileAdmin() throws Exception
	{
		fileName = "/Users/Justin/documents/workspace/Autojava/bin/AutoAdmin.txt";
		openFile();
		traverseMap();
	}
	public int[] numLines() throws IOException
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
		
			FileReader adminFile = new FileReader(fileName);
			readFile = new BufferedReader(adminFile);
			ArrayList<String>tempArrayList = new ArrayList<>();
			String tempMake = null;
			String tempModel = null;
			String tempYear = null;
			String line = null;
			int index = 0;
			int j = 0;
			
			for(int i = 0; i < fileSize; i++)
			{
				line = readFile.readLine();
				for(String part : line.split(","))
				{
					tempArrayList.add(part);
				//Format Make > Model > Starting Year > Closing Year > Price > Package > Colors > Options
				// HashMap  Model.YearYear    model > Tesla. 20102016, read through the 0-3 place.
				// Ford.getYears()
				// modelYearMap.key(Ford.Fusion),19902005
				// for 0-3 get y1
				// for 3-7 get y2
					switch(index)
					{
					case 1: tempMake = tempArrayList.get(j);	
					break;
					case 2:	tempModel = tempArrayList.get(j);
					break;
					case 3: tempYear = tempArrayList.get(j);
					break;
					case 4: modelYearMap.put(tempModel, tempYear + tempArrayList.get(j));
					break;
					case 5: modelPriceMap.put(tempModel, Float.valueOf(tempArrayList.get(j)));
					break;
					default: System.out.println(tempArrayList.get(j));		
					}
					autoMap.put(tempModel,tempMake);
					index++;
					j++;
				}
				index = 1;
			}
		
		
	}
	public void traverseMap()
	{
		System.out.println(modelYearMap.toString());
		System.out.println(modelPriceMap.toString());
		System.out.println(autoMap.toString());
	}
	public static void main(String[] args) throws Exception
	{
		System.out.println("Don't worry this is only a test");
		FileAdmin auto = new FileAdmin();
	}
	@Override
	public void selectFile() 
	{
		userInput = new Scanner(System.in);
		System.out.println("Enter 'default' to use default file. Enter 'choose' to choose between files");
		String answer = userInput.next();
		if(answer.equals("default"))
		{
			System.out.println("User chose default, loading default file.");
		}
		else if(answer.equals("choose"))
		{
				listFiles();
				boolean loop = true;
				System.out.println("Enter one of the above listed file names including the type.");
				do{
					
				answer = userInput.next();
				for(int i = 0; i < listOfFiles.length; i++)
				{
					if(answer.equals(String.valueOf(listOfFiles[i].getName())))
					{
						System.out.println("User Chose: " + listOfFiles[i].getName());
						fileName = String.valueOf(listOfFiles[i]);
						loop = false;
						break;
					}
				}
				if(!loop){break;}
				System.out.println("The file entered was not correct. Enter again? Y/N");
				answer = userInput.next();
				answer.toLowerCase();
				if(answer.equals("y")){loop = true;}
				else if(answer.equals("n")){System.out.println("Using Default"); loop = false;}
				else{System.out.println("Looping again"); loop = true;} // consider throwing an Exception.
				}while(loop);
				
		}
		else
		{
			System.out.println("Answer was not understood, using default"); //THROW EXCEPTION HERE!!!!!!!  handle in exception loop******************
		}

		
	}
	@Override
	public void listFiles() 
	{
		for(int i = 0; i < listOfFiles.length; i++)
		{
			if(listOfFiles[i].isFile())
			{
				System.out.println("File: " + listOfFiles[i].getName());
				System.out.println("The File path is: " + listOfFiles[i].getPath());
			}
		}
	}
}
