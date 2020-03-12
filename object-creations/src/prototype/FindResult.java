package prototype;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import DAO.FileIORegion;

// Match person to the region/color they are from and return 
public class FindResult {
	public int counter = 0;

	private Map<String, String> mapList;

	public FindResult(String file) throws IOException {
		
		if (file == "region.txt") {
			FileIORegion f = FileIORegion.getInstance();
			mapList = f.getMap(file);
		}
	}

	// Find the region/state color for the person, returns the match
	public String match(String state) {
		Set<Entry<String, String>> set = mapList.entrySet(); 	// Converting to Set in order to traverse
		Iterator<Entry<String, String>> i = set.iterator(); 
		String result = new String();
		
		// Iterate until you find the state and region
		while (i.hasNext()) {
			
			// Convert to Map.Entry to get key and value separately
			Map.Entry<String, String> entry = (Map.Entry<String, String>) i.next(); 
			
			// Finding values from map in order to get the correct value associated with the state
			String compareState = entry.getKey().toString();
			String[] region = entry.getKey().toString().split(",");
			String match = entry.getValue().toString().split(",")[region.length - 1]; 
			
			// Found match
			if (compareState.equals(state)) {
				result = match;
			}
		}
		
		return result;	// Returns the match of given state, i.e. State: ID Region: West
	}
}
