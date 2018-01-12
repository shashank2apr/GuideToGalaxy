package com.galaxy.inputData.processors.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.ValidationFailException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.inputData.processors.enums.LangEnums;
import com.galaxy.inputData.processors.interfaces.GenericDataProcessorIf;
import com.galaxy.outputData.processors.OutputProcessor;
import com.galaxy.utilities.RomanToNumericUtils;
import com.galaxy.utilities.validators.RomanNumberValidator;

public class DataProcessorTextFileImpl implements GenericDataProcessorIf {

	private OutputProcessor pi;
	final Map<String, Character> interGalacticToRomanMap = new HashMap<String, Character>();

	final Map<Integer, String> inputQuestionsMap = new LinkedHashMap<Integer, String>();

	final Map<String, Float> creditsPerUnitVal = new HashMap<String, Float>();

	public OutputProcessor getPi() {
		return pi;
	}

	public Map<String, Character> getInterGalacticToRomanMap() {
		return Collections.unmodifiableMap(interGalacticToRomanMap);
	}

	public Map<Integer, String> getInputQuestionsMap() {
		return Collections.unmodifiableMap(inputQuestionsMap);
	}

	public Map<String, Float> getCreditsPerUnitVal() {
		return Collections.unmodifiableMap(creditsPerUnitVal);
	}

	public OutputProcessor processInputData(List<String> rawInputData) throws CustomException {

		if (null == rawInputData || rawInputData.isEmpty()) {
			System.out.println("OutputProcessor processInputData");
			throw new IllegalArgumentException(ExceptionEnums.PARAM_CANT_BE_NULL_OR_EMPTY.getValue());
		}
		return createProcessedInput(rawInputData);
	}

	private OutputProcessor createProcessedInput(List<String> rawInputData) throws CustomException {

		Integer counter = 0;

		for (String line : rawInputData) {

			String arr[] = line.split("\\s+");

			if (checkIfAmountsQuestion(line)) {
				counter = processQuestion(counter, line);
			} else if (checkIfCreditsQuestion(line)) {
				processCredits(line, arr);
			} else if (checkIfCurrencyMapping(arr)) {
				processCurrencyMapping(arr);
			}
		}

		pi = new OutputProcessor(interGalacticToRomanMap, inputQuestionsMap, creditsPerUnitVal);
		return pi;
	}

	/**
	 * @param line
	 * @return
	 */
	private boolean checkIfCreditsQuestion(String line) {
		return line.toLowerCase().endsWith(LangEnums.CREDITS.getTextVal());
	}

	/**
	 * @param arr
	 * @return
	 */
	private boolean checkIfCurrencyMapping(String[] arr) {
		return arr.length == 3 && arr[1].equalsIgnoreCase(LangEnums.IS.getTextVal());
	}

	/**
	 * @param line
	 * @return
	 */
	private boolean checkIfAmountsQuestion(String line) {
		return line.endsWith("?");
	}

	/**
	 * @param arr
	 * @throws CustomException
	 */
	private void processCurrencyMapping(String[] arr) throws CustomException {
		Character value = (arr[arr.length - 1]).charAt(0);
		try {
			if (null != interGalacticToRomanMap && RomanNumberValidator.validate(Character.toString(value)))
				interGalacticToRomanMap.put(arr[0], value);
		} catch (ValidationFailException e) {
			throw new CustomException(e.getMessage());
		}
	}

	/**
	 * @param qNumber
	 * @param line
	 * @return
	 */
	private Integer processQuestion(Integer qNumber, String line) {
		if (null != inputQuestionsMap)
			inputQuestionsMap.put(qNumber++, line);
		return qNumber;
	}

	private void processCredits(String query, String[] array) {

		int creditValue = 0;
		String element = null;
		List<String> valueofElement = new ArrayList<String>();

		for (int i = 0; i < array.length; i++) {
			if (array[i].equalsIgnoreCase("credits")) {
				creditValue = Integer.parseInt(array[i - 1]);
			} else if (array[i].equalsIgnoreCase("is")) {

				element = array[i - 1];
			} else {
				valueofElement.add(array[i]);

			}
		}

		valueofElement.remove(element);
		valueofElement.remove(Integer.toString(creditValue));

		char[] currencyToRomanList = mapCurrencyToRoman(valueofElement);

		calculateCreditValue(creditValue, element, currencyToRomanList);

	}

	/**
	 * @param creditValue
	 * @param element
	 * @param currencyToRomanList
	 */
	private void calculateCreditValue(int creditValue, String element, char[] currencyToRomanList) {
		try {
			int romanValue = RomanToNumericUtils.INSTANCE.convertRomanToNumeric(new String(currencyToRomanList));

			if (null != creditsPerUnitVal)
				creditsPerUnitVal.put(element, new Float(creditValue * 1f / romanValue));
		} catch (Exception e) {
			if (null != creditsPerUnitVal)
				creditsPerUnitVal.put(element, null);

			System.err.println("wrong roman value :" + new String(currencyToRomanList) + " for " + element
					+ " --- ERROR: " + e.getMessage());

		}
	}

	private char[] mapCurrencyToRoman(List<String> currency) {
		// List<Character> romanList = new ArrayList<Character>();

		char[] romanList = new char[currency.size()];
		int i = 0;
		for (String string : currency) {
			// romanList.add(currencyMap.get(string));

			romanList[i++] = interGalacticToRomanMap.get(string);
		}
		return romanList;
	}

}
