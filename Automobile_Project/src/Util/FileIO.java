package Util;

import Model.*;
import java.io.*;
import java.util.ArrayList;

public class FileIO extends FileOperator implements FileOperations 
{
	//****VARIABLES****
	private String fileSelect;
	private int fileSize;
	private int fileIndex[];
	private String reportFile;
	protected ArrayList <Automobile>  autoList = new ArrayList<>();
	private FileStartup fileStart = new FileStartup();
	//****END VARIABLES****

	
	//****CONSTRUCTORS****
	public FileIO() throws Exception, FileExceptionHandler
	{
		//referenceFile("newCarOptions.txt");
		selectFile();
		this.fileSelect = getFileSelect();
		fileStart.numLines();
		fileStart.openFile();
		numLines();
		openFile();
	}

	//****END CONSTRUCTORS****
	
	//****SET/GET***
	public void setFileIndex() throws IOException {fileIndex = numLines();}

	public String getReportFile()
	{	
		referenceFile("reportFile.txt");
		this.reportFile = getFileSelect();
		return reportFile;
	}

	public int[] getFileIndex(){return fileIndex;}
	public int getFileSize(){return fileSize;}
	public ArrayList<Automobile> getAutoList(){return autoList;}
	//****END SET/GET****
	
	public void openFile() throws FileExceptionHandler, Exception{
		try(FileReader myFile = new FileReader(fileSelect);){
			BufferedReader bookWorm = new BufferedReader(myFile);
			OptionSet[] opsetArr = new OptionSet[fileSize];
			Automobile[] autoArr = new Automobile[fileSize];
			ArrayList <String> lineSplits = new ArrayList<>();
			String line; 
			
				for(int i = 0; i < fileSize; i++) { //Runs for the # of lines in the file.
						
						line = bookWorm.readLine();//Contains line data for each read.
					System.out.println(fileSize);
					
				for(String part : line.split(",")){	lineSplits.add(part);}
					System.out.println(opsetArr.length + "  " + i);
					System.out.println(lineSplits.size());
					opsetArr[i] = new OptionSet(lineSplits.get(3),Integer.valueOf(lineSplits.get(4)), lineSplits.get(5), lineSplits.get(6), lineSplits.get(7));
					autoArr[i] = new Automobile(lineSplits.get(0), lineSplits.get(1), Integer.valueOf(lineSplits.get(2)), opsetArr[i]);
					System.out.println("Auto number: "+ i + " is " + fileStart.checkAuto(autoArr[i]));
					autoList.add(autoArr[i]);
					reportFile(autoArr[i].getReport(), i);
					lineSplits.clear();
					}
				
		}
		catch (IOException|NumberFormatException ex)
		{
				System.err.println("The Exception" + ex);
		}
		catch(NullPointerException ex){
				System.err.println("Unable to read: " + ex);
				System.err.println("Reselect File Please..." );
				selectFile();
				this.fileSelect = getFileSelect();
				System.err.println("New file: " + fileSelect);
				openFile();
		}
		catch(IndexOutOfBoundsException ex){
			FileExceptionHandler e = new FileExceptionHandler(fileSelect);
			System.err.println("calling Exception Hander to fix file: " + fileSelect);
			e.missingLine(fileSelect);
			referenceFile(fileSelect);
			numLines();
			openFile();
		}
	}
	/*_REPORTFILE_
	 * - reportFile(String containing report data from Automobile, int ID of the specific car) 
	 * - reportFile name is retrieved
	 * - A file is created to using FileWriter, and written to subsequently with bufferedWriter.
	 * - A brief report is printed.
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
			 			System.err.println("IOException " + e);
					}
	}
	/*_NUMLINES_
	 * - numlines(Your File) 
	 * - Opens File
	 * - while not at EOF
	 * - reads each line checks for each delimiter ',' is used for differentiate objects.
	 * - each '\n' signature is used to count the line.
	 * - an array is returned that holds the capacity of each line. thus [line1] = contains 5 objects.
	 */
	public int[] numLines() throws IOException
	{		
		byte[] b = new byte[1024]; 	//reads through list of all chars,to find \n's and count them
		int numLine = 0; //counter for read
		int count[] = new int[1024];	
		int readChar = 0;//t/f empty
		boolean empty = true; //reading till EOF signature,
		try(InputStream fin = new BufferedInputStream(new FileInputStream(fileSelect));)//read in file	
		{
			while((readChar = fin.read(b)) != -1){
				empty = false;//read through line, counts each \n char
				for(int i = 0; i< readChar; i++){
					
					if(b[i]==',')count[numLine]++; //line 1: 5, line 2: 3, and so on.
					if(b[i]=='\n')numLine++;
				}
			}	
			fileSize = numLine;
			fileIndex = count;
		//returns either null if empty or count if not
		return (count[0] == 0 && !empty) ? null : count;

		}catch(NullPointerException | IOException e){
			System.err.println("NUMLINES Exception");
			System.err.println("Unable to read: " + fileSelect);
			System.err.println("Reselect File Please..." );
			selectFile();
			this.fileSelect = getFileSelect();
			System.err.println("New file: " + fileSelect);
			numLines();
		}
		return (count[0] == 0 && !empty) ? null : count;
	} 
	
	
	//****END FILE OPS****
	}