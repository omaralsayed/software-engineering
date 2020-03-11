package prototype;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Hashtable;

import prototype.FindResult;
import business_logic.FindPeople;
import prototype.Midwest;
import prototype.Northeast;
import prototype.South;
import prototype.West;

public abstract class FindRegions {
	public static Hashtable<String, Person> personMap = new Hashtable<String, Person>();

	public FindRegions() {
		// Default constructor
	}
	
	// Cloning the people
	public static Person getPerson(String nameAndState) {
		Person cachedPerson = personMap.get(nameAndState);
		
		return (Person) cachedPerson.clone();
	}
	
	// For every person, based off of their state find out if the person is from the Midwest, South, West, or Northeast.
	public static void findRegionsOfPeople() throws IOException {
		FindResult region = new FindResult("region.txt");	// Create instance of FindResult
		String regionMatch;
		
		FindPeople people = new FindPeople("input_sample2.txt"); 		// Getting the names and states of people from "input_sample2.txt"
		List<String> peopleToClassify = people.printNameAndState();		// Get List<String> of people and their states
		
		// Loop through peopleToClassify to find region of each person based off of their state
		for (int currPerson = 0; currPerson < peopleToClassify.size() - 2; currPerson++) {
			regionMatch = region.match(peopleToClassify.get(currPerson + 1).substring(8).toString());	// Find region of each person
					
			// Check for region
			if (regionMatch.equals("Midwest")) {
				Midwest temp = new Midwest();							// Create person
				
				temp.setName(peopleToClassify.get(currPerson));			// Set name
				temp.setState(peopleToClassify.get(currPerson + 1));	// Set state
				
				personMap.put(temp.getName() + temp.getState(), temp);  // Put the name and state of person as key, the Person Object as value
			} 
			
			else if (regionMatch.equals("Northeast")) {
				Northeast temp = new Northeast();
				
				temp.setName(peopleToClassify.get(currPerson));
				temp.setState(peopleToClassify.get(currPerson + 1));
				
				personMap.put(temp.getName() + temp.getState(), temp);
			} 
			
			else if (regionMatch.equals("West")) {
				West temp = new West();
				
				temp.setName(peopleToClassify.get(currPerson));
				temp.setState(peopleToClassify.get(currPerson + 1));
				
				personMap.put(temp.getName() + temp.getState(), temp);
			} 
			
			else if (regionMatch.equals("South")) {
				South temp = new South();
				
				temp.setName(peopleToClassify.get(currPerson));
				temp.setState(peopleToClassify.get(currPerson + 1));
				
				personMap.put(temp.getName() + temp.getState(), temp);
			}

			currPerson++; // Need to index twice to get a person, the structure is: name, state
		}
	}

	// Print all people
	public static void loadPeople() {
		Set<String> keys = personMap.keySet();
		
		// Loop through entire map
		for (String key : keys) {
			Person clonePerson = (Person) FindRegions.getPerson(key);	// Clones people based off of key (which is name and state)
			
			System.out.print(key + "\nRegion: " + clonePerson.getRegion() + "\n\n");	// Printing out the name, state, and region of each person
		}
	}
}
