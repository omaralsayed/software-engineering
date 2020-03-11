package test_object_creations;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import singleton.ReadExcelInfo;

class test_singleton {
	
	/*
	 * Alphabetical prefix added to control the order of testing
	 */

	@Test
	void A_allArrayListsShouldContainSameNmberOfElements() {
		ReadExcelInfo readFile = ReadExcelInfo.getInstance();
		readFile.save("zipCode_info.xlsx");

        assertEquals(readFile.getId().size(), readFile.getName().size(), "Size must be equal");
        assertEquals(readFile.getName().size(), readFile.getSt().size(), "Size must be equal");
        assertEquals(readFile.getSt().size(), readFile.getZipMin().size(), "Size must be equal");
        assertEquals(readFile.getZipMin().size(), readFile.getZipMax().size(), "Size must be equal");
	}
	
	@Test
	void B_memoryAddressShouldBeSame() {
		ReadExcelInfo getInstanceOne = ReadExcelInfo.getInstance();
		ReadExcelInfo getInstanceTwo = ReadExcelInfo.getInstance();
		
		assertEquals(getInstanceOne, getInstanceTwo, "Memory address should be equal");
	}
	
	@Test
	void C_secondGetterShouldReturnAnEmptyArrayList() {
		ReadExcelInfo getInstanceOne = ReadExcelInfo.getInstance();
		getInstanceOne.save("zipCode_info.xlsx");
		
		ReadExcelInfo getInstanceTwo = ReadExcelInfo.getInstance();
		getInstanceTwo.save("zipCode_info.xlsx");
		
		getInstanceOne.getId().clear();
		
		ArrayList<String> empty = new ArrayList<String>();
		
		assertEquals(getInstanceTwo.getId(), empty, "Must be true due to singleton pattern");
	}

}
