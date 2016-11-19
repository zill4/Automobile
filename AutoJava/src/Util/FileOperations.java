package Util;
import java.io.IOException;
public interface FileOperations 
{
	/*FileOperator
	 * File Operator is an interface used to organize and streamline Util File Operations.
	 * File Operations are a common occurance in any program.
	 * It is important to implement constraints on how files are handled.
	 * as well as implement commonalities between classes such that they operate in a synchronous fashion.
	 * 
	 * select file
	 * opens file
	 * creates objects
	 * closes file
	 * report file
	 * 
	 */
	public void listFiles();
	public void selectFile();
	public int[] numLines() throws IOException;
	public void openFile() throws Exception;
	
	
		
}
