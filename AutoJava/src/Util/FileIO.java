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
		setFileName("/Users/Justin/git/Automobile/Autoproject/bin/newCarOptions.txt");
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
			Automobile[] autoArr = new Automobile[fileSize];
			ArrayList <Automobile>  autoList = new ArrayList<>();
			ArrayList <String> lineSplits = new ArrayList<>();
			int indexObj = 0;
			String line; 
			int k = 0;
				//Runs for the # of lines in the file.
				for(int i = 0; i < fileSize; i++) 
				{
					//Contains line data for each read.
					 
					line = bookWorm.readLine();
					System.out.println(line);
					
					//creating a array to read the objects of each file as separated by ,
					//String[] parts = new String[fileIndex[i]];, INSTEAD OF THIS, I used an arrayList which is much more efficient.
					//easy to add and delete without worrying that you will get a nullPointerE.
					for(String part : line.split(","))
					{
						lineSplits.add(part);
						
						System.out.println(lineSplits.get(k));
						k++;
					}
					
					opsetArr[indexObj] = new OptionSet(lineSplits.get(3),Integer.valueOf(lineSplits.get(4)), lineSplits.get(5), lineSplits.get(6), lineSplits.get(7));
					autoArr[indexObj] = new Automobile(lineSplits.get(0), lineSplits.get(1), Integer.valueOf(lineSplits.get(2)), opsetArr[indexObj]);
					indexObj++;
					lineSplits.clear();
					k = 0;
				}
				//adding the prior created AutoObjects to an ArrayList
					for(int j = 0; j< indexObj; j++)
					{
						autoList.add(autoArr[j]);
						autoArr[j].printResults();
					}
		myFile.close();
		}
		catch (IOException|NumberFormatException ex)
		{
			throw ex;
		}
	}
	
}
	
		
	