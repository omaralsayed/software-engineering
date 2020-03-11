package factory;

import java.util.ArrayList;
import singleton.ReadExcelInfo;

public class ZipFactory {
	
	private ArrayList<String> zipHigh;
	private ArrayList<String> zipLow;
	private ArrayList<String> stateData;
	
	public ZipFactory(String zipDataPath){
		
		//	Read ZIP data from file
		ReadExcelInfo zipInfo = ReadExcelInfo.getInstance();
		zipInfo.save(zipDataPath);
		
		//	Store requirements in lists
		zipHigh = zipInfo.getZipMax();
		zipLow = zipInfo.getZipMin();
		stateData = zipInfo.getSt();
		
	}
	
	public ZipCode getZip(String address, String name){
		
		//	If the ZIP is valid, return a valid ZIP object
		//	otherwise an invalid ZIP object
		if(isValidZip(address)){
			return new ValidZip(address, name);
		} else {
			return new InvalidZip(address, name);
		}
	}
	
	public ZipCode getZip(Person x){
			
		//	If the ZIP is valid, return a valid ZIP object
		//	otherwise an invalid ZIP object
		if(isValidZip(x.getAddress())){
			return new ValidZip(x.getAddress(), x.getName());
		} else {
			return new InvalidZip(x.getAddress(), x.getName());
		}
	}
	
	//	Checks if a ZIP code is valid for the given address
	//	(Compare address info to excel doc data for validation)
	private boolean isValidZip(String address){
		
		int index, zipInt, zipHighInt, zipLowInt;
		
		//	Break apart address
		String parts[] = address.split(",");
		String zip = parts[parts.length - 1].substring(1);
		String state = parts[parts.length - 2].substring(1);
		
		//	Check if state or ZIP are invalid length
		if(state.length() != 2 || zip.length() != 5){
			return false;
		}
		
		//	Get index of state (if it exists)
		if((index = stateData.indexOf(state)) == -1){
			return false;
		}
		
		//	Parse strings into INTs
		try{
			zipInt = Integer.parseInt(zip);
			zipHighInt = Integer.parseInt(zipHigh.get(index));
			zipLowInt = Integer.parseInt(zipLow.get(index));
		} catch(NumberFormatException e){
			return false;
		}
		
		//	Check if ZIP is in valid range for the state
		if(zipInt <= zipHighInt  && zipInt >= zipLowInt){
			return true;
		}
		else {
			return false;
		}
		
	}
}
