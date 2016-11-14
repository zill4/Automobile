package driver;




import Util.*;
public class Driver 
{

	
		public static void main(String[] args) throws Exception
		{
			System.out.println("*******************************************");
			System.out.println("* Starting Application: Crisp Car Catalog *"); //CCC
			System.out.println("*******************************************");
			FileIO runIt = new FileIO();
			runIt.selectFile();
		//Ask which file I should run?
			runIt.openFile();
		}
}
