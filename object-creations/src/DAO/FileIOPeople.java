package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileIOPeople {
	private static Map<String, String> map = new HashMap<String, String>();

	private static FileIOPeople instance = new FileIOPeople();

	private FileIOPeople() {
		// Default constructor
	}

	// Get instance of FileIOPeople
	public static FileIOPeople getInstance() {
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
		
		// Put contents from "input_sample2.txt" into a map
		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).toString().contains("name:")) {
				map.put(tempList.get(i).trim().substring(6).toString(), tempList.get(i + 1).substring(9).toString());
			}
		}
		
		br.close();		// Close the file
	}

	// Return map information
	public Map<String, String> getMap(String file) throws IOException {
		readFile(file);
		
		return map;
	}
}
