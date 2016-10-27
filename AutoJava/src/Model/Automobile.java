package Model;

import java.util.Scanner;

public class Automobile
{
	//VARIABLES
	private float basePrice;
	private OptionSet optset = new OptionSet();
	private String make; //name and base price will be linked soon.
	private String model;
	private int year;
	private String[] avMakes = {"ford", "toyota", "nissan", "honda", "porsche", "jeep"};
	private String[] avModels = {"mustang", "prius", "z", "civic", "carrera 911", "grand cherokee"};
	private float[] modelPrice = {90000, 30000, 500000, 15000, 100000, 40000};
	
	//CONSTRUCTOR 
	Automobile()
	{
		basePrice = 30000; //Base price set to $30,000
		make = null;
	}
	
	public Automobile(String make, String model, int year, OptionSet optset)
	{	
		
		setMake(make);
		setModel(model);
		setYear(year);
		basePrice = getModelPrice(model);
		this.optset = optset;
		yearModifier(year);
	}
	
	
	//SETTERS / GETTERS
		public void setBasePrice(float price){basePrice = price;}
		
		public void setMake(String make){this.make = make;}
		public String getMake()	{return make;}
	
		public String getModel() {return model;}
		public void setModel(String model) {this.model = model.toLowerCase();}
		
		public int getYear() {return year;}
		public void setYear(int year) {this.year = year;}

		public float  getModelPrice(String model)
		{
			for(int i = 0; i < avModels.length; i++)
			{
				if(model.equals(avModels[i]))
				{
					return modelPrice[i];
				}
			}
			
			//If model was not found
			return 0;
		} 
		public String getModel(String make)	
		{
			for(int i= 0; i < avMakes.length; i++)
			{
				//consider making a multidimensional array of makes[ford][mustang], where each make contains even more models.
				//right now it is just a parallel array
				if(avMakes[i].equals(make))
				{
				return avModels[i];
				}
			}
			//if the Make does not have that model, returns null
			return null;
		}
		
		//METHODS OF DESTRUCTION
		//fun-ction that adds to the price based on the difference between high and low price points for cars
		//these points are represented through high sales such as a new car or a collectors car
		//and low points such as cars that are not collectible year wise, and are not new.
		public void yearModifier(int year)
		{
			
			int lowYear = 2000;
			int highYear = 2017;
			int collectorYear = 1985;
			if(year < lowYear && year > collectorYear)
			{
				int x = lowYear - year;
				for(int i = 0; i < x; i++)
				{
					basePrice = (float) (basePrice*.95);
				}
			}
			if(year > lowYear && year <= highYear)
			{
				int x = highYear - year;
				for(int i = 0; i < x; i++)
				{
					basePrice = (float) (basePrice*1.10);
				}
			}
			if(year < collectorYear)
			{
				int x = collectorYear - year;
				for(int i = 0; i < x; i++)
				{
					basePrice = (float) (basePrice*1.75);
				}
			}
			
		}
		
		public void printResults()
		{
			System.out.println("**************************************************");
			System.out.println("* Results are as fallows, in the following order *");
			System.out.println("* Make, Model, Year, Total Price, Color, ft/Tft  *");
			System.out.println("* " + getMake() + "   " +  getModel() +"   " + getYear()+ "   " + getModelPrice(model)
			+ "   "+ optset.numOfOptions()+"/4 *");
		}
		
		
		
		//MAIN
		public static void main(String[] args)
		{
			
		Scanner userIn = new Scanner(System.in);
		System.out.println("Initialized");
		String namer = userIn.nextLine();
		System.out.println(namer);
		//Automotive car = new Automotive("Ford",2, 10000);
		System.out.println("Color: ");
		String color = userIn.next();
		System.out.println("ABS 1-3: " );
		int abs = userIn.nextInt();
		System.out.println("Transmission: "); 
		String transmission = userIn.next();
		System.out.println("Air Bags: ");
		String airBags = userIn.next();
		System.out.println("Moonroof: ");
		String moonroof = userIn.next();
		int index = 1;
		//car.fillOptions(color, abs, transmission,  airBags, moonroof, index);
		System.out.println("done");
		}
	
}




//To do: complete harmony of Auto, throughout to OptionSet, create Auto objects NOT optionSets
//Organize file accordingly
//create refined Terminal input
//Add take in input method to all classes, that allow for terminal control and abilitiy to traverse the program space
//upload to gitHub
//add File Exception handling
//add interface
//add abstract class
//add functionality methods....mine are better
//Upload to teacher
//
//EndGame, CarMake, Total Cost, vehicle, Model, Selected Options/Total Options
//			Jeep, 45,000 , Wrangler, Rubicon, [4/7] Options
// public Automotive buildAutoObject(String filename) { ...














//********CODE GRAVEYARD***********
//
/*
 * created an OptionSet object with raw input from main.
 * public void fillOptions(String color, int abs, String transmission, String airBags, String moonroof)
	{
		optset = new OptionSet(color,abs,transmission,airBags,moonroof, basePrice);
	
	
 * Instantiated through an OptionSet array
 * 	public void  instantiateOptionSet()
	{
		for(int i = 0; i < size; i++)
		{
			optset = new OptionSet();
		}
	}
 * 
 * 
 * 
 * 
 */

	
	
