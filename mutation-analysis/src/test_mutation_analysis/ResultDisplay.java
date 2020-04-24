package test_mutation_analysis;

 

import static org.junit.jupiter.api.Assertions.*;

 

import org.junit.jupiter.api.Test;

 

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

 

import DAO.FileIO;
import business_logic.DistinguishZipCodeFactory;
import business_logic.District;
import business_logic.InValidZipCode;
import business_logic.Selection;
import business_logic.ValidZipCode;
import business_logic.Phaser;

 

class ResultDisplay {
    
    @Test
    /**
     * Check if the results are correctly matched
     * @throws IOException
     */
    void A_test() throws IOException {
        
        // FileIO setup
        FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
        FileIO.PersonalAddress_InputPath = "input_sample3.txt";
                        
        // Blue and red group make sure not modified
        assertEquals(Selection.SelectionBlueStateList.size(), 22);
        assertEquals(Selection.SelectionRedStateList.size(), 30);
        
        // Region
        assertEquals(District.officialRegionList.size(), 4);
        assertEquals(District.NortheastAbbreviationList.size(), 9);
        assertEquals(District.MidwestAbbreviationList.size(), 12);
        assertEquals(District.SouthAbbreviationList.size(), 17);
        assertEquals(District.WestAbbreviationList.size(), 13);
        
        // District
        assertEquals(District.Division1_List.size(), 6);
        assertEquals(District.Division2_List.size(), 3);
        assertEquals(District.Division3_List.size(), 5);
        assertEquals(District.Division4_List.size(), 7);
        assertEquals(District.Division5_List.size(), 9);
        assertEquals(District.Division6_List.size(), 4);
        assertEquals(District.Division7_List.size(), 4);
        assertEquals(District.Division8_List.size(), 8);
        assertEquals(District.Division9_List.size(), 5);
        
    }
    
    @Test
    /** 
     * Validate the total number of zipcodes processed
     * @throws IOException
     */
    void B_test() throws IOException {
        
        // FileIO setup
        FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
        FileIO.PersonalAddress_InputPath = "input_sample3.txt";
                
        DistinguishZipCodeFactory DZ = new DistinguishZipCodeFactory();
        
        Phaser valid = DZ.GetPhaser("valid");
        Phaser invalid = DZ.GetPhaser("invalid");
        
        int valSize = valid.GetSize();
        int invSize = invalid.GetSize();
        int totSize = valSize + invSize;
        
        assertEquals(totSize, 30);
        
    }
    
    @Test
    /**
     * Validate output type (factory)
     * @throws IOException
     */
    void C_test() throws IOException {
        
        // FileIO setup
        FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
        FileIO.PersonalAddress_InputPath = "input_sample3.txt";
        
        // Set to a valid zip
        Map<Integer, String> temp = new HashMap<Integer, String>();
        temp.put(1, "name: Rachel Sanders,address: 777 Story Rd, San Jose, CA, 95122, 2");
        FileIO.InputPeopleInfo = temp;
        
        // Determine if valid or invalid
        DistinguishZipCodeFactory factory = new DistinguishZipCodeFactory();
        Phaser output = factory.GetPhaser("Valid");
        
        // Check if output is correct class type
        assertTrue(output instanceof ValidZipCode);
        
        // Set to a invalid zip (incorrect ZIP code)
        temp.put(1, "name: Rachel Sanders,address: 777 Story Rd, San Jose, CA, 45122, 2");
        FileIO.InputPeopleInfo = temp;
        
        // Determine if valid or invalid
        output = factory.GetPhaser("inValid");
        
        // Check if output is correct class type
        assertTrue(output instanceof InValidZipCode);
        
    }
    
    @Test
    /**
     * Validate level outputs
     * @throws IOException
     * @throws CloneNotSupportedException
     */
    void D_test() throws IOException, CloneNotSupportedException {
        
        // FileIO setup
        FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
        FileIO.PersonalAddress_InputPath = "input_sample3.txt";
        
        // Setup user data and level
        Map<Integer, String> temp = new HashMap<Integer, String>();
        temp.put(1, "name: Rachel Sanders,address: 777 Story Rd, San Jose, CA, 95122, 2");
        
        FileIO.InputPeopleInfo = temp;
        
        // Parse data through district object
        District district = new District();
        district.DistrictPhaser("District");
        
        // Obtain result
        String result = District.AllDistrictResultList.get(0);
        
        // Check result
        assertEquals(result, "name: Rachel Sanders; District information: West --> Division 9");
        
    }
    
    @Test
    /**
     * Validate error handling
     * @throws IOException
     * @throws CloneNotSupportedException
     */
    void E_test() throws IOException, CloneNotSupportedException {
        
        // FileIO setup
        FileIO.ZipCodeInfo_InputPath = "zipCode_info.xlsx";
        FileIO.PersonalAddress_InputPath = "input_sample3.txt";
        
        FileIO.InputPeopleInfo = new HashMap<Integer, String>();
        
        FileIO instance = FileIO.getInstance();
        instance.GetPeopleInfo();
        
        // Setup user data
        FileIO.InputPeopleInfo.put(1, "name: Rachel Sanders,address: 777 Story Rd, San Jose, CA, 95122, 2, 2");
        
        DistinguishZipCodeFactory factory = new DistinguishZipCodeFactory();
        factory.GetPhaser("Valid");
        
        // Parse data through district object
        District district = new District();
        district.DistrictPhaser("District");
        
        // Obtain result
        String result = District.AllDistrictResultList.get(1);
        
        // Check result
        assertEquals(result, "#######Invalid District information or format######, Check error input: name: Rachel Sanders");

 

    }

 

}