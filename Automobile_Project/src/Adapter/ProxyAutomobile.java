package Adapter;

import java.util.Scanner;

import Util.FileOperator;

public abstract class ProxyAutomobile 
{
	FileOperator fileOp;
	
	public static void main(String[] args) throws Exception
	{
		BuildAuto autoBuilder = new BuildAuto();
		System.out.println("Welcome to Auto Catalog 2.7.5");
		System.out.println("Please Enter the corrasponding code for a function");
		System.out.println("BuildAuto: 1 " + "  " + "PrintAuto: 2 " + "  " + 
						  "UpdatePrice: 3" + "  " + "updateOptionName: 4 " + " " );
		Scanner userInput = new Scanner(System.in);
		int userChoice = userInput.nextInt();
		String answer;
		switch(userChoice)
		{
		case 1: System.out.println('\n' + "BuildAuto Creates an Auto Object given a textFile and its path");
				autoBuilder.buildAuto();;
		break;
		
		case 2: System.out.println('\n' + "PrintAuto will print any specified auto object, given the ModelID");
				System.out.println("Enter the ModelID that you want to print");
				answer = userInput.next();
				autoBuilder.printAuto(Integer.valueOf(answer));
		break;
		
		case 3: System.out.println('\n' + "UpdateOptionPrice will update the cost of an auto object given any float");
				System.out.println("Provide the ModelID and the price. If the price > totalPrice, the auto will be upgraded,");
				System.out.println("if price < totalPrice, auto will be downgrade (optionSet).");
				System.out.print("ModelID: ");
				answer = userInput.next();
				System.out.print("New Price: ");
				float priceAnswer = userInput.nextFloat();
				autoBuilder.updateOptionPrice(Integer.valueOf(answer), priceAnswer);
		break;
		
		case 4: System.out.println('\n' + "UpdateOptionSetName will update the selected models optionSet package. Subsequently updating the price as well");
				System.out.println("Please Enter the ModelID: ");
				int modelID = userInput.nextInt();
				System.out.println("Please Enter one of the following packages...");
				System.out.println("Pleb - Plus - Pro - Premium");
				answer = userInput.next();
				autoBuilder.updateOptionSetName(modelID, answer);
		break;
		
		}
	}
}
