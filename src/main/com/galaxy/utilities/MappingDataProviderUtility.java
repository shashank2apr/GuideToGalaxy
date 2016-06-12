package main.com.galaxy.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingDataProviderUtility {

	private static final  Map<Character, Integer> romanToNumericMap = populateRomanToNumeric();//new HashMap<Character, Integer>();
	private static final Map<Character, String> substractMap = populateSubstractList();//new HashMap<Character, String>();
	private static final List<Character> repeatsList = populateRepeats();//new ArrayList<Character>();

	private static Map<Character, String> populateSubstractList() {
		Map<Character, String> substractMap = new HashMap<>();
		
		substractMap.put('I', "VX");
		substractMap.put('X', "LC");
		substractMap.put('C', "DM");
		
		return substractMap;
	}

	private static List<Character> populateRepeats() {
		List<Character> repeatsList = new ArrayList<Character>();
		
		repeatsList.add('I');
		repeatsList.add('X');
		repeatsList.add('C');
		repeatsList.add('M');
		
		return repeatsList;

	}

	private static Map<Character, Integer> populateRomanToNumeric() {
		
		Map<Character, Integer> romanToNumericMap= new HashMap<>();
		
		romanToNumericMap.put('I', 1);
		romanToNumericMap.put('V', 5);
		romanToNumericMap.put('X', 10);
		romanToNumericMap.put('L', 50);
		romanToNumericMap.put('C', 100);
		romanToNumericMap.put('D', 500);
		romanToNumericMap.put('M', 1000);
		
		return romanToNumericMap;
	}

	/**
	 * 
	 * @return substractMap
	 */
	public static Map<Character, String> getSubstractMap() {
		return Collections.unmodifiableMap(substractMap);
	}

	/**
	 * Returns Map of Roman to Decimal Values
	 * 
	 * @return romanToNumeric
	 */
	public static Map<Character, Integer> getRomanToNumeric() {
		return Collections.unmodifiableMap(romanToNumericMap);
	}

	/**
	 * @return the repeats
	 */
	public static List<Character> getRepeats() {
		return Collections.unmodifiableList(repeatsList);
	}
}
