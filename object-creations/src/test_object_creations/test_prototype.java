package test_object_creations; 

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import prototype.FindRegions;
import prototype.Midwest;
import prototype.West;
import prototype.South; 
import prototype.Northeast;
import prototype.Person;

class test_prototype {
	
	@Test
	void A_checkNotTheSame() {
		Midwest mTemp = new Midwest(); 
		West wTemp = new West();
		
		assertNotEquals(mTemp, wTemp, "Midwest and West are not the same.");
		assertNotEquals(mTemp, mTemp.clone(), "Midwest and Midwest.clone() are not the same.");
		assertNotEquals(wTemp, wTemp.clone(), "West and West.clone() are not the same.");
		
	}
	
	@Test
	void B_checkClones() {
		Midwest mTemp = new Midwest(); 
		West wTemp = new West();
		Northeast nTemp = new Northeast();
		South sTemp = new South();
		
		
		assertNotEquals(mTemp.getClass(), wTemp.getClass(), "Midwest and West are from different classes.");
		assertEquals(wTemp.getClass(), wTemp.clone().getClass(), "West and West.clone() are from the same class.");
	}
}