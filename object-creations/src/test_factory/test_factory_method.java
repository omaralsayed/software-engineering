package test_factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import factory.InvalidZip;
import factory.Person;
import factory.ValidZip;
import factory.ZipCode;
import factory.ZipFactory;

public class test_factory_method {
	
	
	//	Prove that the code is a proper factory
	@Test
	void A_checkIfIsFactory() {
		
		//		ZipFactory uses the singleton from part1. See Singleton tests in test_singleton.java
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
		
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 41094"));
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 541094"));
		
		//	Check if a is different object than b (the factory successfully returned 2 separate new objects)
		assertNotEquals(a, b);
		
		//	Check that the code properly checked the validity of a and b
		assertTrue(a.isValid());
		assertFalse(b.isValid());
		
		//	Check if a and b are of the proper object types
		assertTrue(a instanceof ValidZip);
		assertTrue(b instanceof InvalidZip);
		assertFalse(a instanceof InvalidZip);
		assertFalse(b instanceof ValidZip);
		assertTrue(a instanceof ZipCode);
		assertTrue(b instanceof ZipCode);
	}
	
	
	//	Check States that don't exist / are not 2 char
	@Test
	void B_checkStateInvalid() {
		
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
		
		//	2 that do not exist
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, AA, 41094"));
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, ZZ, 41094"));
		
		assertFalse(a.isValid());
		assertFalse(b.isValid());
		
		//	2 of incorrect length (which include a real state as a substring)
		ZipCode c = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, ARR, 41094"));
		ZipCode d = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, RAR, 41094"));
		
		assertFalse(c.isValid());
		assertFalse(d.isValid());
		
		//	2 of incorrect length (which are a substring of a real state)
		ZipCode e = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, O, 41094"));
		ZipCode f = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, H, 41094"));
		
		assertFalse(e.isValid());
		assertFalse(f.isValid());
		
		//	2 that exist
		ZipCode g = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 41094"));
		ZipCode h = testFact.getZip(new Person("Jane Smith", "375 Dixmyth Ave, Cincinnati, OH, 45220"));
		
		assertTrue(g.isValid());
		assertTrue(h.isValid());
		
		
	}
	
	
	//	Check ZIPs that don't exist / are not 5 char
	@Test
	void C_checkStateInvalid() {
		
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
		
		//		2 that do not exist
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, AA, 41094"));
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, 55, 41094"));
		
		assertFalse(a.isValid());
		assertFalse(b.isValid());
		
		//	2 of incorrect length (which include a real state as a substring)
		ZipCode c = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KYY, 41094"));
		ZipCode d = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, YKY, 41094"));
		
		assertFalse(c.isValid());
		assertFalse(d.isValid());
		
		//	2 of incorrect length (which are a substring of a real state)
		ZipCode e = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, Y, 41094"));
		ZipCode f = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, K, 41094"));
		
		assertFalse(e.isValid());
		assertFalse(f.isValid());
		
		//	2 that exist
		ZipCode g = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 41094"));
		ZipCode h = testFact.getZip(new Person("Jane Smith", "375 Dixmyth Ave, Cincinnati, OH, 45220"));
		
		assertTrue(g.isValid());
		assertTrue(h.isValid());
		
		//	Empty
		ZipCode i = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton,, 41094"));
		ZipCode j = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, , 41094"));
		
		assertFalse(i.isValid());
		assertFalse(j.isValid());
	}
	
	
	//	Check ZIPs that are invalid and bounds, etc
	@Test
	void D_checkZipInvalid() {
			
		ZipFactory testFact = new ZipFactory("zipCode_info.xlsx");
			
		//		2 that do not exist
		ZipCode a = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 00000"));
		ZipCode b = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 99999"));
		
		assertFalse(a.isValid());
		assertFalse(b.isValid());
		
		//	n - 1
		ZipCode c = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40002"));
		ZipCode d = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 42787"));
		
		assertFalse(c.isValid());
		assertTrue(d.isValid());
		
		//	n
		ZipCode e = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40003"));
		ZipCode f = testFact.getZip(new Person("Jane Smith", "13019 Walton-Verona Rd, Walton, KY, 42788"));
		
		assertTrue(e.isValid());
		assertTrue(f.isValid());
		
		//	center
		ZipCode g = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40410"));
		
		assertTrue(g.isValid());
		
		//	n + 1
		ZipCode i = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 40004"));
		ZipCode j = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, 42789"));
		
		assertTrue(i.isValid());
		assertFalse(j.isValid());
		
		//	empty
		ZipCode k = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, "));
		ZipCode l = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY,"));
		
		assertFalse(k.isValid());
		assertFalse(l.isValid());
		
		//	Not a number
		ZipCode m = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY, adf42x"));
		ZipCode n = testFact.getZip(new Person("John Smith", "13019 Walton-Verona Rd, Walton, KY,s40004"));
		
		assertFalse(m.isValid());
		assertFalse(n.isValid());
		
	}
	
	
}
