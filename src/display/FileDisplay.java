package display;

import java.io.IOException;
import singleton.ReadExcelInfo;

public class FileDisplay {
	public static void main(String[] args) throws IOException {
		ReadExcelInfo readFile = ReadExcelInfo.getInstance();
		readFile.save("zipCode_info.xlsx");
	}
}
