package display;

import java.io.IOException;

import prototype.FindRegions;
import singleton.ReadExcelInfo;

public class FileDisplay {
	public static void main(String[] args) throws IOException {
		ReadExcelInfo readFile = ReadExcelInfo.getInstance();
		readFile.save("zipCode_info.xlsx");
		System.out.print(readFile.getId());
		
		// Prototype
		FindRegions.findRegionsOfPeople();
		System.out.print("\n\n");
		FindRegions.loadPeople();
	}
}
