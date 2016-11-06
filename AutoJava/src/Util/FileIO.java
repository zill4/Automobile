package Util;

import Model.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FileIO implements Serializable
{
	//****VARIABLES****
	private int fileSize;
	private int fileIndex[];
	private String fileName;
	protected ArrayList <Automobile>  autoList = new ArrayList<>();
	//****END VARIABLES****
	
	//****CONSTRUCTORS****
	public FileIO() throws IOException
	{
		setFileName("/Users/Justin/Documents/Workspace/AutoJava/bin/newCarOptions.txt");
		setFileIndex();

	}
	public FileIO(String name) throws IOException
	{
		setFileName(name);
		setFileIndex();
	}
	//****END CONSTRUCTORS****
	

	
	//****SET/GET***
	public void setFileIndex() throws IOException {fileIndex = numLines(fileName);}
	public void setFileName(String name){fileName = name;}
	public String getReportFile()
	{
		
			//Attempting to use current directory created from the original file path
			
			
			//it appends based on the given indexed points.
		
		
			//To add a a tool that given the user's input takes that and searches based on
			//the file name that is provided by the program.
			//Further, the file's should be organized, check for pre-existence
			
		StringBuilder reportFile = new StringBuilder(fileName);
		reportFile.delete(45, 63);
		reportFile.append("reportFile.txt");
		return reportFile.toString();
	}
	public String getFileName(){return fileName;}
	public int[] getFileIndex(){return fileIndex;}
	public int getFileSize(){return fileSize;}
	public ArrayList<Automobile> getAutoList()
	{
		return autoList;
	}
	//****END SET/GET****
	//****FILE OPS****
	public void openFile() throws Exception
	{
		try{
			FileReader myFile = new FileReader(fileName);
			BufferedReader bookWorm = new BufferedReader(myFile);
			OptionSet[] opsetArr = new OptionSet[fileSize];
			Automobile[] autoArr = new Automobile[fileSize];
			ArrayList <String> lineSplits = new ArrayList<>();
			int indexObj = 0;
			String line; 
			int k = 0;
				//Runs for the # of lines in the file.
				for(int i = 0; i < fileSize; i++) 
				{
					//Contains line data for each read.
					 
					line = bookWorm.readLine();
					
					
					//creating a array to read the objects of each file as separated by ,
					//String[] parts = new String[fileIndex[i]];, INSTEAD OF THIS, I used an arrayList which is much more efficient.
					//easy to add and delete without worrying that you will get a nullPointerE.
					for(String part : line.split(","))
					{
						lineSplits.add(part);
						
					
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
						reportFile(autoArr[j].getReport(), j);
					}
			
		myFile.close();
		}
		catch (IOException|NumberFormatException ex)
		{
			throw ex;
		}
	}
	
	public void reportFile(String reportText, int indexed) throws Exception 
	{
		String reportFileName = getReportFile();
		@SuppressWarnings("unused")
		File report = new File(reportFileName);

			 try(FileWriter shakespeareFW = new FileWriter(reportFileName, true);
				 BufferedWriter voltaireBW = new BufferedWriter(shakespeareFW);
				 PrintWriter out = new PrintWriter(voltaireBW))
					{
					    out.println(reportText);
					    
					    out.println("AutoID: " +(indexed+1));
					    out.println('\n');
					
					    
					} 
			 		catch (IOException e) 
			 		{
					
					}
			 
			
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
	
	//****END FILE OPS****
	}