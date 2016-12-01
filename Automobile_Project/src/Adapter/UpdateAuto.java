package Adapter;

public interface UpdateAuto 
{
	//This function searches the Model for a given OptionSet and sets the name of OptionSet to 
	//newName.
	public void updateOptionSetName(int modelID, String optionSetName) throws Exception;
	
	//This function searches the Model for a given OptionSet and Option name, and sets the price to 
	//newPrice.
	public void updateOptionPrice(int modelID, float newprice) throws Exception;
}

