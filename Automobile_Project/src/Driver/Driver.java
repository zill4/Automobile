package Driver;
import java.io.PrintWriter;

import Util.*;

public class Driver 
{
	public static PrintWriter speaker = new PrintWriter(System.out, true);	
	public static void main(String args[])
	{
		speaker.println("Test Driver for the Automobile Project");
		speaker.println("The following Tests will be done in order, \n" + 
						"Open File" + 
						"\nEdit File" +
						"\nCreate new file");
	}
	
}
