package test_mutation_analysis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import DAO.FileIO;
import business_logic.DistinguishZipCodeFactory;
import business_logic.District;
import business_logic.InValidZipCode;
import business_logic.Selection;
import business_logic.ValidZipCode;
import business_logic.Phaser;

class ResultDisplay {
	
	@Test
	/**
	 * Check that results and levels are correctly matched
	 * @throws IOException
	 */
	void A_test() throws IOException, CloneNotSupportedException {
		
		// FileIO setup
		FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
		FileIO.PersonalAddress_InputPath = "input_sample3.txt";
				
		DistinguishZipCodeFactory DZ = new DistinguishZipCodeFactory();
		
		Phaser valid = DZ.GetPhaser("valid");
		Phaser invalid = DZ.GetPhaser("invalid");
		
		// Blue and red group make sure not modified
		assertEquals(Selection.SelectionBlueStateList.size(), 22);
		assertEquals(Selection.SelectionRedStateList.size(), 30);
		
		// Region
		
		
		// District
		
	}
	
	@Test
	/** 
	 * Check that the number of valid and invalid people 
	 * matches the number of their zipcodes
	 * @throws IOException
	 */
	
	void B_test() throws IOException {
		
		// FileIO setup
		FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
		FileIO.PersonalAddress_InputPath = "input_sample3.txt";
				
		DistinguishZipCodeFactory DZ = new DistinguishZipCodeFactory();
		
		Phaser valid = DZ.GetPhaser("valid");
		Phaser invalid = DZ.GetPhaser("invalid");
		
		int valSize = valid.GetSize();
		int invSize = invalid.GetSize();
		
		int totSize = valSize + invSize;
		
		assertEquals(totSize, 30);
		
	}
	
	@Test
	void C_test() throws IOException {
		FileIO.ZipCodeInfo_InputPath="zipCode_info.xlsx";
		FileIO.PersonalAddress_InputPath="input_sample3.txt";
		FileIO instance = FileIO.getInstance();
		
		// Set to a valid zip
		Map<Integer, String> temp = new HashMap<Integer, String>();
		temp.put(1, "name: Rachel Sanders,address: 777 Story Rd, San Jose, CA, 95122, 2");
		instance.InputPeopleInfo = temp;
		
		//	Determine if valid or invalid
		DistinguishZipCodeFactory factory = new DistinguishZipCodeFactory();
		Phaser output = factory.GetPhaser("Valid");
		
		//Check if output is correct class type
		assertTrue(output instanceof ValidZipCode);
		
		
		// Set to a invalid zip (incorrect ZIP code)
		temp.put(1, "name: Rachel Sanders,address: 777 Story Rd, San Jose, CA, 45122, 2");
		instance.InputPeopleInfo = temp;
		
		// Determine if valid or invalid
		output = factory.GetPhaser("inValid");
		
		//Check if output is correct class type
		assertTrue(output instanceof InValidZipCode);
		
	}

}
