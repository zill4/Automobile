package driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import Model.*;
import Util.*;
public class Driver 
{

	
		public static void main(String[] args) throws Exception
		{
			
			System.out.println("*******************************************");
			System.out.println("* Starting Application: Crisp Car Catalog *"); //CCC
			System.out.println("*******************************************");
			//FileIO theFile = new FileIO();
			//System.out.printf("%nWould you like to access: [REPORT]    [SEARCH CAR]    [CATALOG]");
			FileIO runIt = new FileIO();
			runIt.openFile();
		}
}
