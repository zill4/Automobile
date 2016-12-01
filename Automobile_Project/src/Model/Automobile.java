package Model;



public class Automobile
{
	//VARIABLES
	private float basePrice = 10;
	private OptionSet optset = new OptionSet();
	private String make; 
	private String model;
	private int year;
	private int price;
	private int startYear;
	private int endYear;
	//CONSTRUCTOR 
	public Automobile(){};
	public Automobile(String make, String model,String year, String price)
	{
		setMake(make);
		setModel(model);
		int yearStart = Integer.valueOf(year.substring(0,4));
		int yearEnd = Integer.valueOf(year.substring(4,8));
		setStartYearEndYear(yearStart, yearEnd);
		setPrice(Integer.parseInt(price));
	}
	public Automobile(String make, String model, int year, OptionSet optset){	
		setMake(make);
		setModel(model);
		setYear(year);
		setPrice(price);
		this.optset = optset;
		//yearModifier(year); 10/31/16 : broken
	}
	//SETTERS / GETTERS
		
		public void setStartYearEndYear(int sY, int eY){ //As with the two constructors, serves as a tool for checking Autos dates.
				this.startYear = sY;
				this.endYear = eY;
		}
		public int getEndYear(){return endYear;}
		public int getStartYear(){return startYear;}
		public void setBasePrice(float price){basePrice = price;}
		public void setMake(String make){this.make = make;}
		public String getMake()	{return make;}
		public String getModel() {return model;}
		public void setModel(String model) {this.model = model.toLowerCase();}
		public void upgradeOptionSet(boolean upgrade){optset.upgradeOption(upgrade);}
		public void setOptionSet(String name){optset.setOption(name);}
		public int getYear() {return year;}
		public void setYear(int year) {this.year = year;}
		public String getOptionSetName(){
			String optionSetName = optset.getOptionSetName();
			return optionSetName;
		}
		public void setPrice(int price){this.price = price;}
		public int getPrice(){return price;}
		
		public String getReport(){
			String reportHeader = (  getMake() + "   " +  getModel() +"   " + getYear()+ "   " + //getModelPrice(model)
			 "   "+ optset.getOptionSetName());
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
			total = price + total;
			return total;
		}
		
		public boolean equals(Automobile obj){//comparing two auto objects to check if they qualify.
			int myYear = getYear();
			String myMake = getMake();
			String myModel = getModel();
			boolean isEqual = true;
			int endY = obj.getEndYear();
			int startY = obj.getStartYear();
	
			if(myYear > endY || myYear < startY){
				isEqual = false;
			}
			if(myMake.equals(obj.getMake())){
				if(myModel.equals(obj.getModel()))
				{
					setPrice(obj.getPrice());
				}
				else
				{
					isEqual = false;
				}
			}else
			{isEqual = false;}	
			return isEqual;
		}
		//METHODS OF DESTRUCTION //NOTE:10/31/16 : THIS MODIFIER IS BROKE
		//function that adds to the price based on the difference between high and low price points for cars
		//these points are represented through high sales such as a new car or a collectors car
		//and low points such as cars that are not collectible year wise, and are not new.
		public void yearModifier(int year){
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
			if(year < collectorYear){
				int x = collectorYear - year;
				for(int i = 0; i < x; i++)
				{
					basePrice = (float) (basePrice*1.75);
				}
			}	
		}
}