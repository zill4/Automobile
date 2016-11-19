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
import java.util.ArrayList;
import java.util.Scanner;

public class FileUser implements FileOperations
{
	//****VARIABLES****
	private Scanner userInput;
	File directory = new File("/Users/Justin/documents/workspace/Autojava/bin");
	File[] listOfFiles = directory.listFiles();
	private int fileSize;
	private int fileIndex[];
	private String fileName;
	protected ArrayList <Automobile>  autoList = new ArrayList<>();
	//****END VARIABLES****

	
	//****CONSTRUCTORS****
	public FileUser() throws IOException
	{
		setFileName("/Users/Justin/documents/workspace/AutoJava/bin/newCarOptions.txt");
		setFileIndex();

	}
	public FileUser(String name) throws IOException
	{
		setFileName(name);
		setFileIndex();
	}
	//****END CONSTRUCTORS****
	
	//****SET/GET***
	public void setFileIndex() throws IOException {fileIndex = numLines();}
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
	public ArrayList<Automobile> getAutoList(){return autoList;}
	//****END SET/GET****
	
	//****FILE OPS****
	/*_seleceteFile_
	 * select File takes user input. 
	 * allows for the user to choose which file they want to open.
	 * if they enter a file that exists then that file is opened.
	 */
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
						if(String.valueOf(listOfFiles[i].getName()).equals("AutoAdmin.txt"))
						{
							System.out.println("Access Denied, Admin only");
							break;
							//EXCEPTION POSSIBLE HERE, NEED TO REROUTE AGAIN.
						}
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
	
	/*_LISTFILES_
	 * listFiles was created to notify the user what file they wanted to execute.
	 */
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
	/*_OPENFILE_
	 * OpenFile() 
	 * -given numlines, as well as number of objects per line.
	 * -given file is opened.
	 * -iterated through, creates one main list 
	 * -These are compiled together to create one large Arraylist that represents an array of Options.
	 * -ArrayList contains OptionSet objects as well as AutoObjects
	 * -The List can be sent from here to any location that needs AutoObjects.
	 * 
	 * 
	 */
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
					}
					
					opsetArr[indexObj] = new OptionSet(lineSplits.get(3),Integer.valueOf(lineSplits.get(4)), lineSplits.get(5), lineSplits.get(6), lineSplits.get(7));
					autoArr[indexObj] = new Automobile(lineSplits.get(0), lineSplits.get(1), Integer.valueOf(lineSplits.get(2)), opsetArr[indexObj]);
					indexObj++;
					lineSplits.clear();
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
	/*_REPORTFILE_
	 * - reportFile(String containing report data from Automobile, int ID of the specific car) 
	 * - reportFile name is retrieved
	 * - A file is created to using FileWriter, and written to subsequently with bufferedWriter.
	 * - A brief report is printed.
	 * 
	 */
	public void reportFile(String reportText, int indexed) throws Exception 
	{
		String reportFileName = getReportFile();

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
	/*_NUMLINES_
	 * numlines(Your File) 
	 * - Opens File
	 * - while not at EOF
	 * - reads each line checks for each deliminator ',' is used for differentiate objects.
	 * - each '\n' signature is used to count the line.
	 * - an array is returned that holds the capacity of each line. thus [line1] = contains 5 objects.
	 */
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
	
	//****END FILE OPS****
	}