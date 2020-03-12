package business_logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import factory.Person;
import DAO.FileIO;

public class Computation {

	public int counter = 0;

	private Map<String, String> mapList;

	public Computation(String file) throws IOException {
		FileIO f = FileIO.getInstance();
		mapList = f.getMap(file);
	}

	// Print name and zip code
	public List<String> printAllNameAndZipCode() {
		Set<Entry<String, String>> set = mapList.entrySet();// Converting to Set so that we can traverse
		Iterator<Entry<String, String>> itr = set.iterator();
		List<String> printList = new ArrayList<String>();
		while (itr.hasNext()) {
			// Converting to Map.Entry so that we can get key and value separately
			Map.Entry<String, String> entry = (Map.Entry<String, String>) itr.next();
			String firstName = entry.getKey().toString().split(" ")[0].toString();
			String lastName = entry.getKey().toString().split(" ")[1].toString();
			String[] address = entry.getValue().toString().split(",");
			String zipCode = entry.getValue().toString().split(",")[address.length - 1];
			printList.add("First name: " + firstName);
			printList.add("Last name: " + lastName);
			printList.add("ZIP Code: " + zipCode);
		}
		return printList;
	}
	
	
	public List<Person> getAllNameAndZipCode() {
		Set<Entry<String, String>> set = mapList.entrySet();// Converting to Set so that we can traverse
		Iterator<Entry<String, String>> itr = set.iterator();
		List<Person> printList = new ArrayList<Person>();
		while (itr.hasNext()) {
			// Converting to Map.Entry so that we can get key and value separately
			Map.Entry<String, String> entry = (Map.Entry<String, String>) itr.next();
			String firstName = entry.getKey().toString().split(" ")[0].toString();
			String lastName = entry.getKey().toString().split(" ")[1].toString();
			String address = entry.getValue().toString();
			printList.add(new Person(firstName + " " + lastName, address));
		}
		return printList;
	}
}
