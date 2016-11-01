package Adapter;


import Util.FileEdit;
import Util.FileIO;
public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto
{

	@Override
	public void updateOptionSetName(int modelID, String optionSetName) throws Exception 
	{
		//This simply calls the edit class to make the necessary changes to the Auto Object.
		FileEdit updateAuto = new FileEdit();
		updateAuto.setOptionSet(modelID, optionSetName);
	}
	@Override
	public void updateOptionPrice(int modelID, float newPrice) throws Exception
	{
		FileEdit updateAuto = new FileEdit();
		updateAuto.upgradeOptionSet(modelID, newPrice);
		//NOTE this isn't as much a upgrade your own price, as it is a request for an 
		//upgrade or downgrade based, depending on if you want to pay more or less.
		//This can be defined more. But is fine....for now.... 
	}

	@Override
	public void buildAuto(String filename) throws Exception 
	{
		FileIO userDefinedFile = new FileIO(filename);
		userDefinedFile.openFile();
	}
	
	@Override
	public void printAuto(int modelID) throws Exception
	{
		FileEdit printer = new FileEdit();
		String displayAuto = printer.printModel(modelID);
		System.out.println(displayAuto);
		
	}
	

	
}
