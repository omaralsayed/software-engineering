package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileIORegion {
	private static Map<String, String> map = new HashMap<String, String>();

	private static FileIORegion instance = new FileIORegion();

	private FileIORegion() {
		// Default constructor
	}

	// Get instance of FileIORegion
	public static FileIORegion getInstance() {
		return instance;
	}
	
	// Read .txt file, read data and save data in map
	private static void readFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		List<String> tempList = new ArrayList<String>();
		
		while ((st = br.readLine()) != null) {
			tempList.add(st.trim().toString());
		}
		
		// Put contents from "region.txt" into a map
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).toString().contains("state:")) {
				map.put(tempList.get(i).trim().substring(7).toString(), tempList.get(i + 1).substring(8).toString());	// Store in map
			}
		}
		
		br.close();		// Close file
	}

	// Return map information
	public Map<String, String> getMap(String file) throws IOException {
		readFile(file);
		
		return map;
	}
}
