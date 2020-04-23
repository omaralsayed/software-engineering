package test_mutation_analysis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import DAO.FileIO;
import business_logic.DistinguishZipCodeFactory;
import business_logic.District;
import business_logic.Selection;
import business_logic.Phaser;

class ResultDisplay {
	
	@Test
	/**
	 * Check that results and levels are correctly matched
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 */
	void A_test() throws IOException, CloneNotSupportedException {
		
		FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
		FileIO.PersonalAddress_InputPath = "input_sample3.txt";
				
		DistinguishZipCodeFactory DZ = new DistinguishZipCodeFactory();
		
//		Phaser valid = DZ.GetPhaser("valid");
//		Phaser invalid = DZ.GetPhaser("invalid");
		
		// Phaser for selection (Blue and red)
//		Selection selection = new Selection();
//		Selection blue = (Selection) selection.SelectionBluePhaser(); 
//		Selection red = (Selection) selection.SelectionRedPhaser();
		
		// Phaser for region and district info
//		District district = new District(); 
		
		// Blue and red group
		//assertNotEquals();
		
	}
	
	@Test
	/** 
	 * Check that the number of valid and invalid people 
	 * matches the number of their zipcodes
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 */
	
	void B_test() throws IOException, CloneNotSupportedException {
		
		FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
		FileIO.PersonalAddress_InputPath = "input_sample3.txt";
				
		DistinguishZipCodeFactory DZ = new DistinguishZipCodeFactory();
		
		Phaser valid = DZ.GetPhaser("valid");
		Phaser invalid = DZ.GetPhaser("invalid");
		
		int valSize = valid.GetSize();
		int invSize = invalid.GetSize();
		
		// Arithmetic mutation friendly
		int totSize = valSize + invSize;
		
		assertEquals(totSize, 30);
		
	}

}
