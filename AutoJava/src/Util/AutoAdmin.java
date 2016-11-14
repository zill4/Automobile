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
	private int fileSize;
	private HashMap <String, String> autoMap = new HashMap<>();
	private HashMap <String, String> modelYearMap = new HashMap<>();
	private HashMap <String, Float> modelPriceMap = new HashMap<>();
	AutoAdmin() throws Exception
	{
		fileName = "C:\\Users\\Justin\\workspace\\AutoJava\\bin\\AutoAdmin.txt";
		fileLines = numLines(fileName);
		openFile();
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
		
			FileReader adminFile = new FileReader(fileName);
			BufferedReader readFile = new BufferedReader(adminFile);
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
		AutoAdmin auto = new AutoAdmin();
	}
}
