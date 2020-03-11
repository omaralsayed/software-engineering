package display;

import java.io.IOException;

import prototype.FindRegions;
import singleton.ReadExcelInfo;
import java.util.ArrayList;
import java.util.List;
import business_logic.Computation;
import factory.Person;
import factory.ZipCode;
import factory.ZipFactory;

public class FileDisplay {
//	public static void main(String[] args) throws IOException {
//		ReadExcelInfo readFile = ReadExcelInfo.getInstance();
//		readFile.save("zipCode_info.xlsx");
//		System.out.print(readFile.getId());
//		
//		// Prototype
//		FindRegions.findRegionsOfPeople();
//		System.out.print("\n\n");
//		FindRegions.loadPeople();
//	}
	
	
	public static void main(String[] args) throws IOException {
		ZipFactory factory = new ZipFactory("zipCode_info.xlsx");
		Computation data = new Computation("Jan27-Jan31-input-sample.txt");
		
		List<Person> people = data.getAllNameAndZipCode();
		ArrayList<ZipCode> zips = new ArrayList<ZipCode>();
		
		for(Person x : people){
			zips.add(factory.getZip(x));
		}
		
		for(ZipCode y : zips){
			System.out.println(y.isValid() + " | " + y.getAddress().getName());
		}
	}
	
}
