package test_mutation_analysis;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import business_logic.*;
import DAO.*;
import display.*;

import org.junit.jupiter.api.Test;

class ResultDisplay {

	@Test
	// check that results and levels are correctly matched
	void test() {
		
		// Path
		FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlxs";
		FileIO.PersonalAddress_InputPath = "input_sample3.txt";
		
		// Phaser for valid and invalid group
		DistinguishZipCodeFactory dz = new DistinguishZipCodeFactory();
		Phaser valid = dz.GetPhaser("valid");
		Phaser invalid = dz.GetPhaser("invalid");
		
		// Phaser for selection (Blue and red)
		Selection selection = new Selection();
		Selection blue = (Selection) selection.SelectionBluePhaser(); 
		Selection red = (Selection) selection.SelectionRedPhaser();
		
		// Phaser for region and district info
		District district = new District(); 
		
		// Blue and red group
		//assertNotEquals();
		
		// Division 
		
		
		// Region
		
		// Valid and invalid zipcode 
		
	}

}
