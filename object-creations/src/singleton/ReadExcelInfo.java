package singleton;

import java.io.File;  
import java.io.FileInputStream; 
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class ReadExcelInfo {
	private static ReadExcelInfo single_instance = null;
	
	private static ArrayList<String> id = new ArrayList<String>();
	private static ArrayList<String> name = new ArrayList<String>();
	private static ArrayList<String> st = new ArrayList<String>();
	private static ArrayList<String> zipMin = new ArrayList<String>();
	private static ArrayList<String> zipMax = new ArrayList<String>();
	
	private ReadExcelInfo() {
		// Default constructor
	}
	
	// Create a single instance of ReadExcelInfo 
	public static ReadExcelInfo getInstance() {
		if (single_instance == null) {
			single_instance = new ReadExcelInfo(); 
		}
		return single_instance;
	}

	public ArrayList<String> getId() {
		return id;
	}
	
	public ArrayList<String> getName() {
		return name;
	}
	
	public ArrayList<String> getSt() {
		return st;
	}
	
	public ArrayList<String> getZipMin() {
		return zipMin;
	}
	
	public ArrayList<String> getZipMax() {
		return zipMax;
	}
	
	public void save(String path) {
		try	{
			File file = new File(path);

			// Get bytes from spreadsheet
			FileInputStream fis = new FileInputStream(file);
	
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			// Create a sheet object to retrieve information
			XSSFSheet sheet = wb.getSheetAt(0);
						
			for (Row r : sheet) {
			   Cell c = r.getCell(0);
			   if (c != null && c.getCellType() == Cell.CELL_TYPE_NUMERIC)
				   id.add(String.valueOf((int)c.getNumericCellValue())); // Cast then append
			   else
				   id.add(c.getStringCellValue());
			}
						
			id.remove(0);
					
			for (Row r : sheet) {
			   Cell c = r.getCell(1);
			   if (c != null && c.getCellType() == Cell.CELL_TYPE_STRING)
				   name.add(c.getStringCellValue());
			}
				
			name.remove(0);

			for (Row r : sheet) {
			   Cell c = r.getCell(2);
			   if (c != null && c.getCellType() == Cell.CELL_TYPE_STRING)
				   st.add(c.getStringCellValue());
			}
			
			st.remove(0);
			
			for (Row r : sheet) {
			   Cell c = r.getCell(3);
			   if (c != null && c.getCellType() == Cell.CELL_TYPE_NUMERIC)
				   zipMin.add(String.valueOf((int)c.getNumericCellValue())); // Cast then append
			   else
				   zipMin.add(c.getStringCellValue());
			}
			
			zipMin.remove(0);
			
			for (Row r : sheet) {
			   Cell c = r.getCell(4);
			   if (c != null && c.getCellType() == Cell.CELL_TYPE_NUMERIC)
				   zipMax.add(String.valueOf((int)c.getNumericCellValue())); // Cast then append
			   else
				   zipMax.add(c.getStringCellValue());
			}
			
			zipMax.remove(0);
			wb.close();
		}
				
		catch(Exception e) {
		e.printStackTrace();  
		}
	}
}
