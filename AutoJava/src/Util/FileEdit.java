package Util;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Automobile;

public class FileEdit
{
	//check for file size
	//HashMap currently only works for 1 run of the program, soon to add functionality beyond using 
	//report ID retrieval.
	private FileIO fin = new FileIO();
	protected static HashMap<Integer,Automobile> autoID = new HashMap<Integer,Automobile>();
	private ArrayList<Automobile> autos = new ArrayList<Automobile>();
	private Automobile auto = new Automobile();
	//private int autoIndex;
	public String model;
	public FileEdit() throws Exception 
	{
		fin.openFile();
		autos = fin.getAutoList();
	}


	//****GET METHODS****
	//Here the get methods standAlone, this is because they are relativley 
	//unimportant, the setMethods take the most care.
	//NOTE: You may ask why include an index for each
	//why not construct only one model per FileEdit
	//This is because I would rather have the reach
	//of the entire arrayList. rather than just one,
	// auto object.
	public String getListModel(int index)
	{
		auto = autos.get(index);
		String model = auto.getModel();
		return model;
	}
	public String getListMake(int index)
	{
		auto = autos.get(index);
		String make = auto.getMake();
		return make;
	}
	public String getListOptionSet(int index)
	{
		auto = autos.get(index);
		String optionSetName = auto.getOptionSetName();
		return optionSetName;
	}
	public String getYear(int index)
	{
		auto = autos.get(index);
		String year = String.valueOf(auto.getYear());
		return year;
	}
	public String printModel(int index)
	{
		String modelPrint;
		auto = autos.get(index);
		modelPrint = auto.getReport();
		return modelPrint;
	}
	//****END GET****
	//****SET METHODS****
	//methods to be used for the Adapter Package.
	public void setModel(int index, String model)
	{
		auto = autos.get(index);
		auto.setModel(model);
	}
	public void setMake(int index, String make)
	{
		auto = autos.get(index);
		auto.setMake(make);
	}
	public void setYear(int index, int year)
	{
		auto = autos.get(index);
		auto.setYear(year);
	}
	public void setOptionSet(int index, String optionSet)
	{
		auto = autos.get(index);
		auto.setOptionSet(optionSet);
	}
	public void upgradeOptionSet(int index, float newPrice)

	{
		boolean upgrade;
		auto = autos.get(index);
		if(newPrice < auto.endPrice())
		{
			upgrade = false;
		}
		else
		{
			upgrade = true;
		}
		auto.upgradeOptionSet(upgrade);
	}
	public static void main(String[] args) throws Exception
	{
	
		FileEdit fimo = new FileEdit();
		fimo.setOptionSet(2, "Pro");
		fimo.upgradeOptionSet(2, 100);
		System.out.println(fimo.getListModel(2));
	}
}
