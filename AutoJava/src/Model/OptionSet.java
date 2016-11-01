package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("serial")
public class OptionSet implements Serializable
{

	//VARIABLES
		HashMap<String,Option> optionMap = new HashMap<String,Option>();
		Option opt = new Option();
		private String name;
		private String color;
		private String transmission;
		private String airBags;
		private String moonroof;
		private int abs;
		private float basePrice;
	//OPTIONSET CONSTRUCTORS
	public OptionSet()
	{
		name = null;
		color = null;
		transmission = null;
		airBags = null;
		moonroof = null;
		abs = 0;
	}

	public OptionSet(String color, int abs, String transmission, String airBags, String moonroof)
	{
		name = null;
		setAll(color,abs,transmission,airBags,moonroof);
		setOption();
	}
	
	public OptionSet(String name, String color, int abs, String transmission, String airBags, String moonroof)
	{
		this.name = name;
		setAll(color,abs,transmission,airBags,moonroof);
		setOption(name);
	}
	//GETTERS//SETTERS*****************************************
	protected void setAll(String c, int abs, String t, String a, String m)
	{
		color = c;
		this.abs = abs;
		transmission = t;
		airBags = a;
		moonroof = m;
	}
	
	protected void setOption()
	{
		opt = new Option(color,abs,transmission,airBags,moonroof,basePrice);
		
	}
	protected void setOption(String name)
	{
		this.name = name;
		//add Hashmap for this.
		//Name defines a package. 
			//Name -> pleb = Option(Color, ABS(0), True,False,False
			//Name -> plus = Option(Color, ABS(1), True,False,False
			//Name -> Pro = Option(Color ABS(1), True,True,True
			//Name -> Premium = Option(color ABS(2), False,True,True
			optionMap.put("Pleb", new Option(color, 0, "True", "False", "False", basePrice));
			optionMap.put("Plus", new Option(color, 1, "True", "False", "False", basePrice));
			optionMap.put("Pro",  new Option(color,1,"True", "True", "True", basePrice));
			optionMap.put("Premium", new Option(color,2,"False", "True", "True", basePrice));
			if(optionMap.get(name) == null)
			{
				System.out.println("ERROR OPTION DNE");
				System.out.println("Set Option to Default");
				optionMap.get("Pleb");
			}
	}

	
	protected void upgradeOption(boolean upgrade)
	{
		//insert low level machine learning here.
		// shame == 'true'
		if(upgrade)
		{
			if(name.equals("Pleb")){name = "Plus";}
			else if(name.equals("Plus")){name = "Pro";}
			else if(name.equals("Pro")){name = "Premium";}
			else{name = "Premium";}
		}
		else
		{
			
			if(name.equals("Premium")){name = "Pro";}
			else if(name.equals("Pro")){name = "Plus";}
			else if(name.equals("Plus")){name = "Pleb";}
			else{name = "Pleb";}
		}
		System.out.println(name);
	}
	
	//10/26/16 changed from numOfOptions -> to return package name
	protected String getOptionSetName()
	{
		String selectedOptionSet;
		if(name == null){
		int num = 0;
		num = opt.selectedFeatures();
		switch (num)
		{
		case 0: selectedOptionSet = "Pleb";
		break;
		case 1: selectedOptionSet = "Plus";
		break;
		case 2: selectedOptionSet = "Pro";
		break;
		case 3: selectedOptionSet = "Pro";
		break; 
		case 4: selectedOptionSet = "Premium";
		break;
		default: selectedOptionSet = "Custom";
		}
		return selectedOptionSet;
		}else{
			return name;
		}
	}

	protected String getPrintOption()
	{
		String report = opt.printOption();
		return report;
	}
	
	protected float getUpdatedPrice()
	{
		return opt.getUpdatedPrice();
	}
	//END:GETTERS//SETTERS*************************************
	
	
	//****OPTION CLASS****
	protected class Option
	{
		//VARIABLES
			private String[] colors = {"red" , "yellow", "orange", "blue", "black", "white", "purple", "gray"} ; //temp 8:20, 8:56
			private String color; //The chosen color
			//private String name; 	//IDK why I need this in Option
			private float price;
			private int abs; 	//1:standard $0 2:ABS $400 3:ABS/AdvTrac $1625
			private boolean transmission;	//0: $0 1:$-815
			private boolean airBags;	//0: $0 1:$350
			private boolean moonroof;	//0: $0 1:$595
			private float absCost;
			private float transCost;
			private float airBagCost;
			private float moonroofCost;
			private float basePrice;
				//OBJECTS
				Scanner userIn = new Scanner(System.in); //should be added in an interface + abstract class
		//CONSTRUCTORS
		Option()
		{
			color = null;
			price = 0;
			abs = 1;
			transmission = false;
			airBags = false;
			moonroof = false;
		}
		Option(String color, int abs, String transmission, String airBags, String moonroof, float basePrice)
		{
			this.basePrice = basePrice;
			setColor(color);
			setAbs(abs);
			setTransmission(transmission);
			setAirBags(airBags);
			setMoonroof(moonroof);
			//printOption();
		}
		//METHODS********************
		protected String colorNotFound()
		{
			String defaultColor = null;
			boolean set = false;
			while(set==false)
			{
				System.out.print("Color: " );
				defaultColor = userIn.next();
				
				for(int j = 0; j < colors.length; j++)
				{
					if(defaultColor.equals("esc")){defaultColor = colors[5]; set = true;break;}
					if(colors[j].equals(defaultColor)){set = true; break;}
					
				}
				
			}
			System.out.println("Selected Color: " + defaultColor);
			return defaultColor;
		}
		protected boolean bullString(String boolChoice) //can be interfaced 
		{
			boolean choice;
			if(boolChoice.toLowerCase().equals("false") || boolChoice.toLowerCase().equals("true"))
			{
				choice = Boolean.valueOf(boolChoice);
			}
			else
			{
				System.out.println("Invalid Input, set to default");
				choice = false;
			}
			return choice;
		}
		protected void updatePrice(float cost)
		{
			price = basePrice + cost;
		}
		
		protected int  selectedFeatures()
		{
			int total = 0;
			if(getAbs()> 1){ total++;}
			if(!getTransmission()){total++;} //negative option
			if(getAirBags()){total++;}
			if(getMoonroof()){total++;}
			return total;
		}
		//GETTERS//SETTERS*****************************************
		protected void setColor(String colorChoice)
		{
			boolean tf = false;
			for(int j = 0; j < colors.length; j++)
			{
				if(colorChoice.equals(colors[j])){color = colorChoice; tf = true;}
			}
			if(!tf)
			{
				System.out.println("Color was not found. \n" + "Enter again or type esc to set default");
				color = colorNotFound();
			}
		}
		protected String getColor()
		{
			return color;
		}		
		
		//COMMENTED OUT PRINTLINES
		protected void setAbs(int absChoice)
		{
			absCost = 0;
			switch(absChoice)
			{
			case 1: //System.out.println("Standard - "+ absCost);
			break;
			case 2: absCost = 400;
					updatePrice(absCost);
					//System.out.println("ABS - +"+ absCost);
			break;
			case 3: absCost = 1625;
					updatePrice(absCost);
					//System.out.println("ABS/AdvTrac - +" + absCost);
			break;
			default: //System.out.println("Default: Standard - " + absCost);
			}
			abs = absChoice;
	
		}
		protected int getAbs()
		{
			return abs;
		}
		
	
		protected void setTransmission(String transChoice)
		{
			transCost = 0;
			transmission = bullString(transChoice);
			if(transmission){transCost = -815;updatePrice(transCost); }
		}
		protected boolean getTransmission()
		{
			return transmission;
		}
		
		protected void setAirBags(String airBagChoice)
		{
			airBagCost = 0;
			airBags = bullString(airBagChoice);
			if(airBags){airBagCost = 350;updatePrice(airBagCost);}
		}
		protected boolean getAirBags()
		{
			return airBags;
		}

		protected void setMoonroof(String moonroofChoice)
		{
			moonroofCost = 0;
			moonroof = bullString(moonroofChoice);
			if(moonroof){moonroofCost = 595; updatePrice(moonroofCost);}
		}
		protected boolean getMoonroof()
		{
			return moonroof;
		}
		
		protected float  getUpdatedPrice()
		{
			return price;
		}
		//END:GETTERS//SETTERS*************************************
		protected String printOption()
		{
			String report = ('\n' + "********************************************" + '\n'+
							"|              OPTION REPORT              |" + '\n' + 
							"Color:" + getColor() + '\n' +
							"ABS: " + getAbs() + "   " + absCost + '\n' +
							"Transmission: " + getTransmission()+ "   " + transCost + '\n' +
							"Air Bags: " + getAirBags()+ "    " + airBagCost + '\n' +
							"Moonroof: " + getMoonroof()+ "   " + moonroofCost + '\n' );
			return report;
		}
	}
}
