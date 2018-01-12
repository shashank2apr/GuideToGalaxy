package com.galaxy.utilities;

/**
 * Currently this class provides utility of converting from Roman to Decimal
 * System. In future it might provide more such functionality.
 * 
 * @author Shashnk Singh
 *
 */

import java.util.Map;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.ValidationFailException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.utilities.validators.RomanNumberValidator;

public enum RomanToNumericUtils {

	INSTANCE;

	private Map<Character, Integer> romanToNumeric = MappingDataProviderUtility
			.getRomanToNumeric();

	public int convertRomanToNumeric(String roman) throws ValidationFailException, CustomException {

		RomanNumberValidator.validate(roman);

		char[] romanNum = roman.toCharArray();

		return calculateRomanValue(romanNum);
	}

	/**
	 * Numbers are formed by combining symbols together and adding the values.
	 * For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are
	 * placed in order of value, starting with the largest values. When smaller
	 * values precede larger values, the smaller values are subtracted from the
	 * larger values, and the result is added to the total. For example MCMXLIV
	 * = 1000 + (1000 - 100) + (50 - 10) + (5 - 1) = 1944.
	 * 
	 * @param romanNum
	 * @return
	 * @throws CustomException 
	 */
	private int calculateRomanValue(char[] romanNum) throws CustomException {
		
		if(null==romanNum || romanNum.length==0)
			throw new CustomException(ExceptionEnums.PARAM_CANT_BE_NULL_OR_EMPTY.getValue()); 
			
		int resultInDecimal = 0;
		int previousDigit = 0;
		int currentDigit = 0;

		for (int i = romanNum.length - 1; i >= 0; i--) {
			currentDigit = romanToNumeric.get(romanNum[i]);
			if (currentDigit < previousDigit)
				resultInDecimal -= currentDigit;
			else
				resultInDecimal += currentDigit;
			previousDigit = currentDigit;
		}

		return resultInDecimal;
	}
}
