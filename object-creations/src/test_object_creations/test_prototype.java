package test_object_creations; 

import static org.junit.jupiter.api.Assertions.*;  
import org.junit.jupiter.api.Test;

import java.io.IOException;

import prototype.FindResult;
import prototype.Midwest;
import prototype.West;
import prototype.South; 
import prototype.Northeast;
import prototype.Person;

class test_prototype {
	
	@Test
	// Check that FindResult.java is correctly matching results
	void A_checkResults() throws IOException {
		
		FindResult result = new FindResult("region.txt");
		
		// Checking if the results are matching correctly
		assertEquals(result.match("CT"), "Northeast", "CT is in the Northeast.");
		assertEquals(result.match("IL"), "Midwest", "IL is in the Midwest.");
		assertEquals(result.match("DC"), "South", "DC is in the South.");
		assertEquals(result.match("OR"), "West", "OR is in the West.");
		
		assertNotEquals(result.match("OH"), "West", "OH is not in the West.");
		assertNotEquals(result.match("OH"), "South", "OH is not in the South.");
		assertNotEquals(result.match("DC"), "Midwest", "DC is not in the Midwest.");
		assertNotEquals(result.match("AK"), "Northeast", "AK is not in the Northeast.");
		
		// If state does not exist
		assertEquals(result.match("AA"), "", "State does not exist.");
		assertEquals(result.match("ZZ"), "", "State does not exist.");
	}
	
	@Test
	// Check that the Prototype pattern is working
	void B_checkClones() {
		Midwest mTemp = new Midwest(); 
		West wTemp = new West();
		Northeast nTemp = new Northeast();
		South sTemp = new South();
		
		
		// Check the class of clones
		assertNotEquals(mTemp.getClass(), wTemp.getClass(), "Midwest and West are from different classes.");
		assertEquals(wTemp.getClass(), wTemp.clone().getClass(), "West and West.clone() are from the same class.");
		
		// Check what objects mTemp, wTemp, nTemp, and sTemp are
		assertTrue(mTemp instanceof Person);
		assertTrue(wTemp instanceof Person);
		assertTrue(nTemp instanceof Person);
		assertTrue(sTemp instanceof Person);
		
		// Check that clones are coming from Person
		assertEquals(mTemp.clone() instanceof Person, mTemp instanceof Person, "Midwest clone and Midwest are of the same type, Person.");
		assertEquals(nTemp.getClass(), nTemp.clone().getClass(), "North and North clone have the same class.");
		
		// Check that clones are created properly
		assertNotEquals(wTemp, wTemp.clone(), "West and West clone are not equal.");
		assertNotEquals(mTemp, mTemp.clone(), "Midwest and Midwest clone are not equal.");
		assertNotEquals(sTemp, sTemp.clone(), "South and South clone are not equal.");
		assertNotEquals(nTemp, nTemp.clone(), "North and North clone are not equal.");
	}
}