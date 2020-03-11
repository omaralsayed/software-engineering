package test_object_creations;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import singleton.ReadExcelInfo;

class test_singleton {

	@Test // File Processing
	void A_allArrayListsShouldContainSameNumberOfElements() {
		ReadExcelInfo readFile = ReadExcelInfo.getInstance();
		readFile.save("zipCode_info.xlsx");

        assertEquals(readFile.getId().size(), readFile.getName().size(), "Number of elements must be the same");
        assertEquals(readFile.getName().size(), readFile.getSt().size(), "Number of elements must be the same");
        assertEquals(readFile.getSt().size(), readFile.getZipMin().size(), "Number of elements must be the same");
        assertEquals(readFile.getZipMin().size(), readFile.getZipMax().size(), "Number of elements must be the same");
	}
	
	@Test // Singleton
	void B_memoryAddressShouldBeSame() {
		ReadExcelInfo getInstanceOne = ReadExcelInfo.getInstance();
		ReadExcelInfo getInstanceTwo = ReadExcelInfo.getInstance();
		
		assertEquals(getInstanceOne, getInstanceTwo, "Memory address must be the same");
	}
	
	@Test // Singleton
	void C_secondGetterShouldReturnAnEmptyArrayList() {
		ReadExcelInfo getInstanceOne = ReadExcelInfo.getInstance();
		getInstanceOne.save("zipCode_info.xlsx");
		
		ReadExcelInfo getInstanceTwo = ReadExcelInfo.getInstance();
		getInstanceTwo.save("zipCode_info.xlsx");
		
		getInstanceOne.getId().clear();
		
		ArrayList<String> empty = new ArrayList<String>();
		
		assertEquals(getInstanceTwo.getId(), empty, "Data structures must be empty");
	}

}
