package Util;

import Model.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FileIO implements Serializable
{
	//check for file size
	private int fileSize;
	private int fileIndex[];
	private String fileName;
	public FileIO() throws IOException
	{
		setFileName("C:\\Users\\Justin\\workspace\\AutoProject\\bin\\carOptions.txt");
		setFileIndex();
	}
	FileIO(String name) throws IOException
	{
		setFileName(name);
		setFileIndex();
		
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
	
	//SETTERS/GETTERS
	public void setFileIndex() throws IOException {fileIndex = numLines(fileName);}
	public void setFileName(String name){fileName = name;}
	
	public String getFileName(){return fileName;}
	public int[] getFileIndex(){return fileIndex;}
	public int getFileSize(){return fileSize;}
	
	//To do: complete harmony of Auto, throughout to OptionSet, create Auto objects NOT optionSets
	//Organize file accordingly
	//create refined Terminal input
	//upload to gitHub
	//add File Exception handling
	//add interface
	//add abstract class
	//add functionality methods....mine are better
	//Upload to teacher
	//
	//EndGame, CarMake, Total Cost, vehicle, Model, Selected Options/Total Options
	//			Jeep, 45,000 , Wrangler, Rubicon, [4/7] Options
	// public Automotive buildAutoObject(String filename) { ...
	public void openFile() throws Exception
	{
		try{
			
			FileReader myFile = new FileReader(fileName);
			String[] reader = new String [fileSize];
			BufferedReader bookWorm = new BufferedReader(myFile);
			OptionSet[] opsetArr = new OptionSet[fileSize];
			Automotive[] autoArr = new Automotive[fileSize];
			ArrayList <Automotive>  autoList = new ArrayList<>();
			int indexObj = 0;
			
				//Runs for the # of lines in the file.
				for(int i = 0; i < fileSize; i++) 
				{
					//Contains line data for each read.
					String line;  
					line = bookWorm.readLine();
					System.out.println(line);
					int k = 0;
					//creating a array to read the objects of each file as separated by ,
					String[] parts = new String[fileIndex[i]];
					for(String part : line.split(","))
					{
						parts[k] = part;
						k++;
					}
					opsetArr[indexObj] = new OptionSet(parts[3],Integer.valueOf(parts[4]), parts[5], parts[6], parts[7]);
					autoArr[indexObj] = new Automotive(parts[0], parts[1], Integer.valueOf(parts[2]), opsetArr[indexObj]);
					indexObj++;
				
				}
				//adding the prior created AutoObjects to an ArrayList
					for(int j = 0; j< indexObj; j++)
					{
						autoList.add(autoArr[j]);
					}
		myFile.close();
		}
		catch (IOException|NumberFormatException ex)
		{
			throw ex;
		}
	}
	
}
	
		
	