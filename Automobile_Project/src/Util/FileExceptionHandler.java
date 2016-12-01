package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileExceptionHandler extends Exception
{
	
	/*	The Exception Handler
	 * 		The Exception Handler will retrieve arrayLists or HashMaps of Exceptions.
	 * 		That are coming form both FileEdit and FileIO, with FileIO, being the main source of all
	 * 		File Exceptions. 
	 * 		List of Exceptions: 
	 */
	private String fileName;
	FileExceptionHandler(String fileSelect){
		setFileName(fileSelect);
		
	}
	public void setFileName(String fileSelect){ fileName = fileSelect;}
	public void missingLine(String fileSelect) throws IOException
	{ 
		try{
			System.out.println(fileName);
			File checkFile = new File(fileName);
			File tempFile = new File("Exception_Fix.txt");
			BufferedReader reader = new BufferedReader(new FileReader(checkFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;
			while((currentLine = reader.readLine()) != null) {
			    if(!currentLine.isEmpty()){
			    writer.write(currentLine + System.getProperty("line.separator"));
			    }
			}
			    writer.close(); 
			    reader.close(); 
			    tempFile.renameTo(checkFile);
			
		}catch(IOException e){System.err.println("IOException " + e);}
					
		}


	public static void main(String args[]) throws IOException{
	String fileSelect = "/Users/Justin/documents/workspace/Automobile_Project/bin/carOptions.txt";
	FileExceptionHandler e = new FileExceptionHandler("/Users/Justin/documents/workspace/Automobile_Project/bin/carOptions.txt");
	e.missingLine(fileSelect);
	
	}
}