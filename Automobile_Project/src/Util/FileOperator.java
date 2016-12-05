package Util;


import java.io.File;
import java.util.Scanner;

public class FileOperator 
{
/*	FileOperator
 * 	-----------
 * 1. Checks for user privilege.
 * 2. given privilege selects chosen file to be operated.
 * 
 *  Operation Order
 * 1.userPrivlege() Check if the user is  User or Admin. Admin can change more files, that effect AutoMobile Reference Objects.
 * 2.selectFile() Given Privilege saves the filePath of the file, allows it to be accessed by inherited classes for Opening.
 * 2.5.referFiles(String fileName) Checks directory given the fileName e.x. AutoAdmin.txt, for the file, saves the path in a string and reports a true or false if it was found.
 * 
 *  VARIABLES
 */
	private Scanner userInput = new Scanner(System.in);
	private String fileName;   		//the version of fileSelect that is provided for requesting a simple name of a doc, just the name.
	private String fileSelect;	//Serves as the full path for opening files.
	private String dir = (System.getProperty("user.dir") + "/bin"); //This may not work on windows because of the \\bin
	private boolean privilege = false;
	private File directory = new File(dir);
	private File[] listOfFiles = directory.listFiles();
	//************
	
	public FileOperator() throws FileExceptionHandler, Exception
	{
		selectFile();
		FileIO filo = new FileIO();
	}
	
	public boolean referenceFile(String fileName){ //referFiles given a fileName, will check if the file exists, then set it to that.
		int temp = 0;
		boolean found = false;
		for(int i = 0; i < listOfFiles.length; i++){ //Searches through the list of files that exist in the given directory.
			if(fileName.equals("AutoAdmin.txt")||fileName.equals("colors.txt")){  //These files can not be opened unless privileges are met.
																				//By opened I mean that the file cannot be edited, our sorted through.//Since AutoAdmin.txt is opened at the start of every program to Map the Model Package.
				if(!privilege){System.out.println("access is denied");break;}
			}
			if(fileName.equals(listOfFiles[i].getName())){ //If the requested name is found, the entire name is saved to the FileSelect String which contains the entire path for opening.
				fileSelect = listOfFiles[i].getPath(); //set fileSelect to the selected fileNames full path.
				i = temp;
				found = true;
				break;
			}else if(i==listOfFiles.length){
				System.out.println("Default File Selected.");
				fileName = "newOptionSet.txt";				//the default select loop, this will have to change given that the default file may not exits.
				for(int j = 0; j < listOfFiles.length; j++){
					if(fileName.equals(listOfFiles[j].getName())){fileSelect = listOfFiles[j].getPath();}
					}		
			}
		}
		return found;
	}
	public void selectFile() {
		userInput = new Scanner(System.in);
		privilege = userPrivilege();
		System.out.println("Enter 'default' to use default file. Enter 'choose' to choose between files");
		String answer = userInput.next();	
		boolean loop = true;	
		String defaultFile = "newCarOptions.txt";
		if(answer.equals("default"))
		{
			System.out.println("User chose default, loading default file.");
			//calling method for looping through files.
			referenceFile(defaultFile);
		}
		else if(answer.equals("choose"))
		{
			
			for(int i = 0; i < listOfFiles.length; i++){			//This loop prints and lists the files
			
				if(listOfFiles[i].isFile()){						//Prints out all files that could potentially be opened.
					System.out.println("File: " + listOfFiles[i].getName());
					System.out.println("The File path is: " + listOfFiles[i].getPath());
				}
			}
				do{													//while this goes down, the loop will exit as soon a qualifying file is Chosen.
				System.out.println("Enter one of the above listed file names including the type.");
				answer = userInput.next();
				if(referenceFile(answer)){loop = false;}
				else{
					System.out.println("The file entered was not correct. Enter again? Y/N");
					answer = userInput.next();
					answer.toLowerCase();
					if(answer.equals("y")){loop = true;}
					else if(answer.equals("n")){System.out.println("Using Default"); loop = false;}
					else{System.out.println("Looping again"); loop = true;}}// consider throwing an Exception.
				}while(loop);
		}
		else
		{
			System.out.println("Answer was not understood, using default"); 
			referenceFile(defaultFile);
		}

		
	}
	public boolean userPrivilege()
	{								//userPrivlege is basically a logic loop, requests an input via System.out
		boolean userPriv = false; //false is = to standard user.
		boolean loop = true; //set to false when as a break; from loop.
		String answer = null; //answer to be used in method for system.in. requests.
		int pass = 0; //pass is set to 0, answer is 1 by default.
		do{
			
		System.out.println("Enter 'user' or 'admin'");
		answer = userInput.next();
		answer.toLowerCase(); //weird can't seem to recognize, my input.
						     //ForExample Input Is set to USER. to lower case = user. if I am checking for that then why will it not pass?
		if(answer.equals("user")){
			System.out.println("User Privleges applied");
			loop = false;
		}
		else if(answer.equals("admin")){
			System.out.println("Enter Admin password: "); //password is 1 by default
			System.out.println("pass is set to 1 be default"); //explains that to the user since this is a test.
			pass = userInput.nextInt();						//checks for pass.
			if(pass==1){									//if true...
				System.out.println("Admin Privleges applied"); //sets the boolean userPriv to true. This is what is returned after the startup call.
				userPriv = true;
				loop = false;									//exit loop
			}
				else{
					System.out.println("no dice.");				//answer was not recognized.
					System.out.println("Enter again Y/N");		//Asking for input. Same toLowerCase problem should except both answers not case-sensitive.
					answer = userInput.next();					//may consider using nextLine instead*************************************************************
					answer.toLowerCase();
						if(answer.equals("y")){
							System.out.println("looping again");	//This basically catches the loop and restarts the entire process till an answer is given.
						}
						else if (answer.equals('n')){
							System.out.println("Exiting loop");	
							loop = false;
						}
					}												//end of userPrivlege.
		}
		}
			while(loop);
		return userPriv;
	}

	/* Accessor's and Modifiers 
	 * Operator will grab assets that manage file operations.
	 */
	//Accessor's (grammar is not right, but that read line is annoying.)
	public String getFileSelect(){return this.fileSelect;}
	public String getFileName(){return this.fileName;}
	public File[] getListOfFiles(){return this.listOfFiles;}
	public File getDirectory(){return this.directory;}
	public boolean getPrivilege(){return this.privilege;}
	//Modifiers
	public void setFileSelect(String newFileSelect){this.fileSelect = newFileSelect;}
	public void setFileName(String newFileName){this.fileName = newFileName;}
	public void setFileDirectory(String newDirectory){this.directory = new File(newDirectory);}
	public void setPrivilege(boolean privilege){this.privilege = privilege;}
	
	public static void main(String args[]) throws Exception
	{
		
		System.out.println("Testing File Ops");
		FileIO filo = new FileIO();	
	
		//FileEdit fillTheEdit = new FileEdit()
		//fileStart.traverseMap();
		//fillTheEdit.getListModel(3);
		
	}
	
}
