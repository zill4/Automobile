package Util;


public abstract class FileOperator 
{
/*	This serves as point where Util classes may be combined to create
 * 	an end to end package.
 */
	public void userSelectFile() throws Exception
	{
		FileUser userOp = new FileUser();
		userOp.selectFile();
		userOp.numLines();
		userOp.openFile();
	}
	
	public void adminSelectFile() throws Exception
	{
		FileAdmin adminOp = new FileAdmin();
		adminOp.selectFile();
		adminOp.numLines();
		adminOp.openFile();
	}
	
	public void editFile() throws Exception
	{
		/*Consider 
		 * EditFile, should be expaneded to include options for access
		 * to AdminFile, and newOptionsFile
		 * Depending on the given permissions of the user.
		 * 
		 * editFile can also play a role in the handling of exceptions,
		 * giving it completely unique access to files can allow for changes to be made, 
		 * conventially through the use of this tool
		 * 
		 * Consider creating a file Exception Interface that can handle
		 * common exceptions that are to be expected. 
		 */
	}
	
}
