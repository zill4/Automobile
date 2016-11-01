package Model;



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
	public Automobile()
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
		//yearModifier(year); 10/31/16 : broken
	}
	
	
	//SETTERS / GETTERS
		public void setBasePrice(float price){basePrice = price;}
		
		public void setMake(String make){this.make = make;}
		public String getMake()	{return make;}
	
		public String getModel() {return model;}
		public void setModel(String model) {this.model = model.toLowerCase();}
		
		public void upgradeOptionSet(boolean upgrade)
		{
			optset.upgradeOption(upgrade);
		}
		public void setOptionSet(String name)
		{
			optset.setOption(name);
		}
		public int getYear() {return year;}
		public void setYear(int year) {this.year = year;}
		public String getOptionSetName()
		{
			String optionSetName = optset.getOptionSetName();
			return optionSetName;
		}
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
			return 10000;
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
		public String getReport()
		{
			
			String reportHeader = (  getMake() + "   " +  getModel() +"   " + getYear()+ "   " + getModelPrice(model)
			+ "   "+ optset.getOptionSetName());
			String reportSignature = ("Total Price: " + endPrice());
			StringBuilder bob = new StringBuilder(reportHeader); //Bob the StringBuilder
			String report = optset.getPrintOption();
			bob.append(report);
			bob.append(reportSignature);
			return bob.toString();
		}
		public float endPrice()
		{
			float total = optset.getUpdatedPrice();
			total = basePrice + total;
			return total;
		}
		
		//METHODS OF DESTRUCTION //NOTE:10/31/16 : THIS MODIFIER IS BROKE
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

		
}