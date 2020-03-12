package business_logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import DAO.FileIOPeople;


public class FindPeople {
	
	private Map<String, String> mapList;

	public FindPeople(String file) throws IOException {
		FileIOPeople f = FileIOPeople.getInstance();
		mapList = f.getMap(file);
	}

	// Print name and zip code
	public List<String> printNameAndState() {
		Set<Entry<String, String>> set = mapList.entrySet(); 	// Converting to Set so that we can traverse
		Iterator<Entry<String, String>> itr = set.iterator();
		List<String> printList = new ArrayList<String>();
		
		while (itr.hasNext()) {
			
			// Converting to Map.Entry so that we can get key and value separately
			Map.Entry<String, String> entry = (Map.Entry<String, String>) itr.next();
			
			// Finding values from the map
			String firstName = entry.getKey().toString().split(" ")[0].toString();
			String lastName = entry.getKey().toString().split(" ")[1].toString();
			String[] address = entry.getKey().toString().split(",");
			String state = entry.getValue().toString().split(",")[address.length + 1];
			
			// Adding name and state to printList
			printList.add("Name: " + firstName + " " + lastName + " ");
			printList.add("State: " + state);
		}
		
		return printList;	// Return List<String> of names and states
	}
}
