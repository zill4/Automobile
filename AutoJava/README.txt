AutoJava - ReadMe
by Justin L. Troutman

AutoJava Objective
AutoJava acts as a Car catalogue,
A file is given defining the Car objects.
File layout is defined through the following. 

make, model, year, color, ABS(1-3), transmission(True/False), Airbags(True/False), MoonRoof(True/False)

There is no limit to how many car's you can create.
Although the variables that are defined in the file are strict.
File Errors may occur with deviation from the layout.

Packages

Model
	As for the model package it contains 3 classes.
		Automobile, OptionSet, and Option
	Automobile
		The Automobile class acts as the first stage in classification
		of Auto Objects, it contains 15 methods that act to define the 
		commonalities between all Autos 
			Principally the base cost, 
			the make, the model, the year, and 
			the chosen OptionSet for the prior given.
		
	OptionSet 
		The OptionSet class defines basically the package that each car
		will contain. OptionSet contains 7 methods for manipulation of how the 
		package acts. This is based upon the concept that each Car will have a package,
		of options that are defined like sport, luxury, standard, or premium.
		The packages currently are place holders, they include: "Pleb" - as the lowest package, (cheapest)
		"Plus", "Pro", "Premium". Premium being the highest.
		
		Option
		Option is a subClass of OptionSet (meaning it is inside OptionSet)
		Option acts as the ground floor for the Car object, it defines each Option specificlly
		and arranges selected Options to their corresponding packages. This is important, as in the future, 
		with many more GENERIC Options determining cost, and other package OptionSet specifics 
		will be very crucial to the overall functionality of the Application.
		Option contains 16 methods that directly define the variables corresponding to each given option.
		
Util
	
	FileIO
		The FileIO class is fairly hefty, it brings a lot of functionality in that 
		given a setup file to the specifications marked prior, FileIO can read the file,
		contain it in an arrayList, and send that list to other Utilities to be interpreted.
		ArrayList in itself is a very functional tool, when containing different objects.
		There are 12 methods that used for handling Files, for example file reading, line checking, 
		transferring information into Auto and OptionSet Objects, pairing those objects, and sending that information.
		The two cornerstones of this class involve reading a file given the file path.
		And, creating a report file, that reports the output of this program.
		
	FileEdit
		The FileEdit class is specifically created to transfer information obtained from FileIO, and allow for 
		adapters to change specifically chosen information, and reform the results that were not prescribed in the file. 
		FileEdit contains 11 methods for handling these tasks. FileEdit is the first source of known bugs,
		these bugs include the inability to change price down, and subsequently downgrade packages correctly.
		FileIO Exception handling has not been implemented and will likely be its own class.
		Including reports given to the ending report file.
		
Adapter
		BuildAuto
			BuildAuto operates as the main class of methods that says what the user can do 
			how the user can operate with the code. Operating with 4 methods each method has a very specific task.
			Operating to print, update , or merely create an Auto Object.
			Further ambitions hope to synchronize each method such that they all operation with the exact same arrayLists.
		
		CreateAuto
		UpdateAuto
			These two Interfaces are used to specify exactly what must be contained in each class that wants
			to use these methods. In the future Exception handling should largely tie in to how Interfaces decide 
			what can be accessed by Users. Setting a strict guide for what can be accessed should be set here.
			
		ProxyAuto 
			ProxyAuto acts as the driver per say that users are allowed to mess with that gives, access to auto,
			but not quite directly. ProxyAuto I would like in the future to give less access to Automobile, but 
		
		
		CONCLUSION 
			Overall the application has a long way to go till it is solid. Right now it sits as a bit of a delicate flower,
			that requires special understanding of the code to be operated usefully. 
			I would ultimately like this code to be able to run in any condition, and convey information 
			in a much cleaner way.
			
***************************
	Justin L. Troutman
		
		
		I