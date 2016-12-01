package Util;

import Model.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class FileStartup extends FileOperator implements FileOperations
{
	/*  AutoAdmin 
	 * -Access to admin data file(s)
	 * -Reads admin file, storing data in ArrayList, puts(ArrayList) in a HashMap
	 * -Overlays HashMaps, creates a linkedList of HashMaps that points to the objects that instantiate each other.
	 * -I.E. Ford instantiates Year, Year instantiates Model, Model > OptionSet(s), OptionSet(s) > Options.
	 * Lets Begin.
	 *
	 */
	//Inherited variables/not locally specific variables
	private String fileSelect;
	private int fileSize;
	//File Startup Specific variables
	private Automobile autoMobi = new Automobile();
	protected Hashtable<Integer, Automobile> autoCheckList = new Hashtable<>();
	//****
	private BufferedReader readFile;
	
	FileStartup() throws Exception{
		setPrivilege(true);
		referenceFile("AutoAdmin.txt");
		this.fileSelect = getFileSelect();
	}
	public void openFile() throws Exception{
			//read character stream, from File
			ArrayList<String>tempArrayList = new ArrayList<>();
			String tempMake = null;
			String tempModel = null;
			String tempYear = null;
			String tempPrice = null;
			String line = null;
		try(FileReader adminFile = new FileReader(fileSelect)){ 
			
			readFile = new BufferedReader(adminFile);
			for(int i = 0; i < fileSize; i++){
				line = readFile.readLine();
				for(String part : line.split(",")){		
					tempArrayList.add(part);
					}
					for(int k = 0; k < tempArrayList.size(); k++){
					switch(k){
					case 0: tempMake = tempArrayList.get(k);
							//System.out.print(tempMake);
					break;
					case 1:	tempModel = tempArrayList.get(k);
							//System.out.print(tempModel);
					break;
					case 2: tempYear = tempArrayList.get(k);
							//System.out.print(tempYear);
					break;
					case 3: tempYear += tempArrayList.get(k);
							//System.out.print(tempYear);
					break;
					case 4: tempPrice = tempArrayList.get(k);
							//System.out.print(tempPrice);
					break;
							//System.out.print(tempMake); 
					}
				}
					autoMobi = new Automobile(tempMake,tempModel,tempYear,tempPrice);
					autoCheckList.put(i, autoMobi);
					tempArrayList.clear();
			}
		}
		catch(Exception e){
			System.out.println("Caught Exception: " + e);
		}
		
	}
	public boolean checkAuto(Automobile obj){
		boolean checked = false;
		for(int i = 0; i < autoCheckList.size(); i++){
			if(obj.equals(autoCheckList.get(i))){
				System.out.println("TRUE");
				checked = true;
				break;
			}
		}
		return checked;
	}
	public void traverseMap(){
		System.out.println(autoCheckList.toString());
	}
	public int[] numLines() throws IOException{
		try(InputStream fin = new BufferedInputStream(new FileInputStream(fileSelect))) {//Try with resources reads the byte-stream and counts the chars.{
		byte[] b = new byte[1024]; 					//reads through list of all chars,to find \n's and count them	 
		int numLine = 0;							//counter for read
		int count[] = new int[1024];	
		int readChar = 0;	
		boolean empty = true; 						//t/f empty
			while((readChar = fin.read(b)) != -1){	//reading till EOF signature
				empty = false;						//read through line, counts each \n char
				for(int i = 0; i< readChar; i++){
					if(b[i]==',')count[numLine]++; //line 1: 5, line 2: 3, and so on.
					if(b[i]=='\n')numLine++;
				}
			}	
			fileSize = numLine;
		return (count[0] == 0 && !empty) ? null : count;//returns either null if empty or count if not
		} 
		//NOTE: a try with resources block auto closes the stream. so no finally statement is necessary.
	} 

	public static void main(String[] args) throws Exception{
		System.out.println("Don't worry this is only a test");
		FileStartup auto = new FileStartup();
		auto.numLines();
		auto.openFile();
		auto.traverseMap();
	}
	
}
