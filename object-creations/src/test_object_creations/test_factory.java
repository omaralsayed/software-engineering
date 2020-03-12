package test_object_creations;

import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*;

import factory.InvalidZip;
import factory.Person;
import factory.ValidZip;
import factory.ZipCode;
import factory.ZipFactory;

class test_factory {

	// Check factory method design
	@Test
	void A_checkIfIsFactory() {
		
		// ZipFactory uses the singleton class
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
		
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 41094"));
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 541094"));
		
		// Check if a is different object than b (the factory successfully returned 2 separate new objects)
		assertNotEquals(a, b);
		
		// Check that the code properly checked the validity of a and b
		assertTrue(a.isValid());
		assertFalse(b.isValid());
		
		// Check if a and b are of the proper object types
		assertTrue(a instanceof ValidZip);
		assertTrue(b instanceof InvalidZip);
		assertFalse(a instanceof InvalidZip);
		assertFalse(b instanceof ValidZip);
		assertTrue(a instanceof ZipCode);
		assertTrue(b instanceof ZipCode);
	}
	
	
	// Check ZIPs that don't exist / are not 5 char
	@Test
	void C_checkStateInvalid() {
		
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
		
		// 2 that do not exist
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, AA, 41094"));	// Invalid State
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, 55, 41094"));	// Invalid State
		
		assertFalse(a.isValid());
		assertFalse(b.isValid());
		
		// 2 of incorrect length (which include a real state as a substring)
		ZipCode c = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KYY, 41094"));	// Invalid State (which include a real state as a substring, KY)
		ZipCode d = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, YKY, 41094"));	// Invalid State (which include a real state as a substring, KY)
		
		assertFalse(c.isValid());
		assertFalse(d.isValid());
		
		// 2 of incorrect length (which are a substring of a real state)
		ZipCode e = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, Y, 41094"));	// Invalid State (substring of a real state, KY)
		ZipCode f = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, K, 41094"));	// Invalid State (substring of a real state, KY)
		
		assertFalse(e.isValid());
		assertFalse(f.isValid());
		
		// 2 that exist
		ZipCode g = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 41094"));	// Valid State
		ZipCode h = testFact.getZip(new Person("Jane Smith", "375 Dixmyth Ave, Cincinnati, OH, 45220"));	// Valid State
		
		assertTrue(g.isValid());
		assertTrue(h.isValid());
		
		// Empty
		ZipCode i = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton,, 41094"));	// Invalid State
		ZipCode j = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, , 41094"));	// Invalid State
		
		assertFalse(i.isValid());
		assertFalse(j.isValid());
	}
	
	
	// Check invalid ZIPs and range bounds
	@Test
	void D_checkZipInvalid() {
			
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
			
		// 2 that do not exist
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 00000"));	// Invalid ZIP
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 99999"));	// Invalid ZIP
		
		assertFalse(a.isValid());
		assertFalse(b.isValid());
		
		// n - 1
		ZipCode c = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40002"));	// Invalid ZIP (lower bound - 1)
		ZipCode d = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 42787"));	// Valid ZIP (upper bound - 1)
		
		assertFalse(c.isValid());
		assertTrue(d.isValid());
		
		// n
		ZipCode e = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40003"));	// Valid ZIP (Lower bound)
		ZipCode f = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 42788"));	// Valid ZIP (Upper bound)
		
		assertTrue(e.isValid());
		assertTrue(f.isValid());
		
		// Center
		ZipCode g = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40410"));	// Valid ZIP (Random ZIP in center of bounds)
		
		assertTrue(g.isValid());
		
		// n + 1
		ZipCode i = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40004"));	// Valid ZIP (Lower bound + 1)
		ZipCode j = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 42789"));	// Invalid ZIP (Upper bound + 1)
		
		assertTrue(i.isValid());
		assertFalse(j.isValid());
		
		// Empty
		ZipCode k = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, "));	// Invalid ZIP
		ZipCode l = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY,"));	// Invalid ZIP
		
		assertFalse(k.isValid());
		assertFalse(l.isValid());
		
		// Not a number
		ZipCode m = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, adf42x"));	// Invalid ZIP
		ZipCode n = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY,s40004"));		// Invalid ZIP
		
		assertFalse(m.isValid());
		assertFalse(n.isValid());
		
	}

}
