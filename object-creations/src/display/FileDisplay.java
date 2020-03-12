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
	
	public static void main(String[] args) throws IOException {
		
		// Singleton
		ReadExcelInfo readFile = ReadExcelInfo.getInstance();
		readFile.save("zipCode_info.xlsx");
		System.out.print(readFile.getId());
		
		// Prototype
		FindRegions.findRegionsOfPeople();
		System.out.print("\n\n");
		FindRegions.loadPeople();
		
		// Factory
		ZipFactory factory = new ZipFactory("zipCode_info.xlsx");
		Computation data = new Computation("Jan27-Jan31-input-sample.txt");
		
		List<Person> people = data.getAllNameAndZipCode();
		ArrayList<ZipCode> zips = new ArrayList<ZipCode>();
		
		for (Person person : people) zips.add(factory.getZip(person));
		for (ZipCode zip : zips) {
			System.out.println("\n" + zip.getPerson().getName() + ":");
			zip.display();
		}
	}
	
}
