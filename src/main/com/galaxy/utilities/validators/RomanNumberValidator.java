package com.galaxy.utilities.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.ValidationFailException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.outputData.processors.OutputProcessor;
import com.galaxy.utilities.MappingDataProviderUtility;

public class RomanNumberValidator {

	private static final int MAX_REPEAT_DIGITS = 4;

	/**
	 * Performs below validation on Roman Numerals: <br>
	 * 1- Validation for repeats. <br>
	 * 2- Validation for Subtractions.
	 * 
	 * @param roman
	 * @return TODO
	 * @throws ValidationFailException 
	 */
	public static boolean validate(String roman) throws ValidationFailException  {
		boolean isValid=false;
		
		try {
			if(isRomanNumeral(roman) && validateRepeats(roman) && validateSubtracts(roman))
				isValid=true;
		} catch (CustomException e) {
			throw new ValidationFailException(e.getMessage());
		}
		
		return isValid;
	}
	/**
	 * Checks if input is a Roman numeral
	 * @param roman
	 * @throws CustomException 
	 */
	private static boolean isRomanNumeral(String roman) throws CustomException {
		
		if(null==roman||roman.isEmpty()){
			throw new CustomException(ExceptionEnums.PARAM_CANT_BE_NULL_OR_EMPTY.getValue());
		}
		if(MappingDataProviderUtility.getRomanToNumeric()==null)
			throw new CustomException(ExceptionEnums.NO_ROMAN_NUMERALS.getValue());
		
		for(Character c: roman.toCharArray()){
			//System.out.println((MappingDataProviderUtility.getRomanToNumeric().containsKey(c)));
			if(!(MappingDataProviderUtility.getRomanToNumeric().containsKey(c)))
				throw new CustomException(ExceptionEnums.NOT_ROMAN.getValue());
		}
		
		return true;
		
	}

	/**
	 * Performs below validations: <br>
	 * 1- The symbols "I", "X", "C", and "M" can be repeated three times in
	 * succession, but no more. They may appear four times if the third and
	 * fourth are separated by a smaller value, such as XXXIX<br>
	 * 2- "D", "L", and "V" can never be repeated.
	 * 
	 * @param romanString
	 * @return 
	 * @throws CustomException
	 */

	private static boolean validateRepeats(String romanString)
			throws CustomException {

		char[] romanChar = romanString.toCharArray();

		Map<Character, Integer> repeats = new HashMap<Character, Integer>();
		Character prevChar = null;
		for (Character currChar : romanChar) {

			if (repeats.containsKey(currChar)) {
				checkIfCurrentCharAllowedToRepeat(currChar);
				if (repeats.get(currChar) == MAX_REPEAT_DIGITS) {
					if (currChar == prevChar)
						throw new CustomException("Character" + currChar
								+ " repeats more than " + MAX_REPEAT_DIGITS
								+ " times!");
				}
				repeats.put(currChar, repeats.get(currChar) + 1);
			} else {
				// repeats.clear();
				repeats.put(currChar, 1);
			}
			prevChar = currChar;
		}
		return true;
	}

	/**
	 * Currently D, V, L are not allowed to repeat. In future this method can be
	 * update to disallow more Characters.
	 * 
	 * @param c
	 * @throws CustomException
	 */
	private static void checkIfCurrentCharAllowedToRepeat(Character c)
			throws CustomException {
		List<Character> repeats = MappingDataProviderUtility.getRepeats();
		if (repeats != null && !(repeats.contains(c))) {
			throw new CustomException("D, L or V cant be repeated!" + repeats);
		}
	}

	/**
	 * Performs below validations: <br>
	 * 1- "I" can be subtracted from "V" and "X" only.<br>
	 * 2- "X" can be subtracted from "L" and "C" only.<br>
	 * 3- "C" can be subtracted from "D" and "M" only.<br>
	 * 4- "V", "L", and "D" can never be subtracted.<br>
	 * 5- Only one small-value symbol may be subtracted from any large-value
	 * symbol.
	 * 
	 * @param roman
	 * @throws CustomException
	 */
	private static boolean validateSubtracts(String roman) throws CustomException {

		char[] ch = roman.toCharArray();
		Map<Character, Integer> romanToNumericMap = MappingDataProviderUtility
				.getRomanToNumeric();
		Map<Character, String> substractMap = MappingDataProviderUtility
				.getSubstractMap();

		for (int i = 0; i <= ch.length - 2; i++) {
			if (romanToNumericMap.get(ch[i]) < romanToNumericMap.get(ch[i + 1])
					&& substractMap.containsKey(ch[i])) {
				if (!canBeSubtracted(ch, substractMap, i)) {
					throw new CustomException(ch[i]
							+ " Can only be substracted from :"
							+ substractMap.get(ch[i]));
				}
			} else {
				if (romanToNumericMap.get(ch[i]) < romanToNumericMap
						.get(ch[i + 1])) {
					throw new CustomException(ch[i]
							+ " Can not be subtracted !");
				}
			}
		}
		return true;
	}

	/**
	 * @param ch
	 * @param substractMap
	 * @param i
	 * @return
	 */
	private static boolean canBeSubtracted(char[] ch,
			Map<Character, String> substractMap, int i) {
		char[] subLst = new char[2];
		subLst = substractMap.get(ch[i]).toCharArray();

		boolean flag = false;
		for (char c : subLst) {
			if (c == ch[i + 1] || ch[i] == ch[i + 1]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
