package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Model.Automobile;
import Model.OptionSet;

public interface FileOperator 
{
	/*FileOperator
	 * File Operator is an interface used to organize and streamline Util File Operations.
	 * File Operations are a common occurance in any program.
	 * It is important to implement constraints on how files are handled.
	 * as well as implement commonalities between classes such that they operate in a synchronous fashion.
	 * 
	 */
	public int[] numLines(String fileName) throws IOException;
	
	public void openFile() throws Exception;
	//public void openFile() throws Exception;
	
	
		
}
