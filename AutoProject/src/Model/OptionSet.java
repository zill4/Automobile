package Model;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class OptionSet implements Serializable
{

	//VARIABLES
		
		Option opt = new Option();
		private String color;
		private String transmission;
		private String airBags;
		private String moonroof;
		private int abs;
		private float basePrice;
	//OPTIONSET CONSTRUCTORS
	OptionSet()
	{
		color = null;
		transmission = null;
		airBags = null;
		moonroof = null;
		abs = 0;
	}

	public OptionSet(String color, int abs, String transmission, String airBags, String moonroof)
	{
		setAll(color,abs,transmission,airBags,moonroof);
		//System.out.println(color);
		setOption();
	}
	//GETTERS//SETTERS*****************************************
	public void setAll(String c, int abs, String t, String a, String m)
	{
		color = c;
		this.abs = abs;
		transmission = t;
		airBags = a;
		moonroof = m;
	}
	
	public void setOption()
	{
		opt = new Option(color,abs,transmission,airBags,moonroof,basePrice);
	}

	//END:GETTERS//SETTERS*************************************
	
	
	//OPTION CLASS String,int,String,String,String
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
		//GETTERS//SETTERS*****************************************
		public void setColor(String colorChoice)
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
		public String getColor()
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
		protected void printOption()
		{
			System.out.println("********************************************");
			System.out.println("|              OPTION REPORT              |");
			System.out.println("Color:" + getColor());
			System.out.println("ABS: " + getAbs() + "   " + absCost);
			System.out.println("Transmission: " + getTransmission()+ "   " + transCost);
			System.out.println("Air Bags: " + getAirBags()+ "    " + airBagCost);
			System.out.println("Moonroof: " + getMoonroof()+ "   " + moonroofCost);
			System.out.println("Total Price: " + getUpdatedPrice());
		}
	}
}
