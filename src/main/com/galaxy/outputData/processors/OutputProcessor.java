package com.galaxy.outputData.processors;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.galaxy.inputData.processors.enums.LangEnums;
import com.galaxy.utilities.RomanToNumericUtils;

public final class OutputProcessor {

	private final Map<String, Character> interGalacticToRomanMap;
	private final Map<Integer, String> inputQuestionsMap;
	private final Map<String, Float> creditsPerUnitVal;
	private final Map<Integer, String> outputAnswersMap;

	public OutputProcessor(Map<String, Character> interGalacticToRomanMap,
			Map<Integer, String> inputQuestionsMap,
			Map<String, Float> creditsPerUnitVal) {
		this.interGalacticToRomanMap = interGalacticToRomanMap;
		this.inputQuestionsMap = inputQuestionsMap;
		this.creditsPerUnitVal = creditsPerUnitVal;
		this.outputAnswersMap = new LinkedHashMap<Integer, String>();
	}

	public Map<Integer, String> generateOutput() {

		Set<Entry<Integer, String>> inputQuestionsSet = inputQuestionsMap
				.entrySet();
		StringBuffer currencyList, sb = new StringBuffer();
		Float elementVal;
		boolean isCredit, isError;

		for (Entry<Integer, String> entry : inputQuestionsSet) {

			String[] split = splitQuestion(entry);

			isCredit = false;
			isError = false;

			sb = new StringBuffer();
			elementVal = 0.0f;

			String elementName = "";

			currencyList = new StringBuffer();

			for (int i = 0; i < split.length; i++) {

				String currWord = split[i];
				//Should be seperate class
				if (checkIFInterGalacticUnit(currWord)) {
					prepareInterGalacticInputForCalculation(currencyList, sb,
							currWord);

				} else if (checkIFCreditsVal(currWord)) {
					elementName = currWord;
					sb.append(currWord);
					sb.append(" ");
					isCredit = true;
					elementVal = creditsPerUnitVal.get(currWord);
				} else if (!(currWord.isEmpty()
						|| currWord.equalsIgnoreCase(" ")
						|| currWord.equalsIgnoreCase(LangEnums.IS.getTextVal())
						|| currWord.equalsIgnoreCase(LangEnums.CREDITS
								.getTextVal()) || currWord
							.equalsIgnoreCase(LangEnums.QUESTION_MARK
									.getTextVal()))) {
					isError = true;
				}
			}

			sb.append(LangEnums.IS.getTextVal() + " ");

			int currencyVal = 0;

			if (!isError) {
				try {
					currencyVal = RomanToNumericUtils.INSTANCE
							.convertRomanToNumeric(currencyList.toString());
				} catch (Exception e) {

					System.err.println(e.getMessage());

					outputAnswersMap.put(entry.getKey(),
							LangEnums.CANT_CALC_VALUE.getTextVal()
									+ elementName);
					continue;

				}
			}
			updateCorrectAnswerInMap(sb, elementVal, isCredit, isError, entry,
					elementName, currencyVal);
		}

		return outputAnswersMap;
	}

	/**
	 * We can modify the split logic anytime inside this method
	 * 
	 * @param entry
	 * @return
	 */
	private String[] splitQuestion(Entry<Integer, String> entry) {
		String[] split = entry.getValue().split(
				"(?i)" + LangEnums.HOW_MANY.getTextVal() +LangEnums.HOW_MUCH_IS.getTextVal() + "|(?i)"
						+ LangEnums.HOW_MANY_CREDITS.getTextVal() + "|\\s+");
		// split = (entry.getValue().s).split("\\s+");
		return split;
	}

	/**
	 * @param currencyList
	 * @param sb
	 * @param currWord
	 */
	private void prepareInterGalacticInputForCalculation(
			StringBuffer currencyList, StringBuffer sb, String currWord) {
		currencyList.append(interGalacticToRomanMap.get(currWord));
		sb.append(currWord);
		sb.append(" ");
	}

	/**
	 * @param currWord
	 * @return
	 */
	private boolean checkIFCreditsVal(String currWord) {
		return creditsPerUnitVal.containsKey(currWord);
	}

	/**
	 * @param currWord
	 * @return
	 */
	private boolean checkIFInterGalacticUnit(String currWord) {
		return interGalacticToRomanMap.containsKey(currWord);
	}

	/**
	 * Currently we are getting two type of questions. For example,<br>
	 * 1- how much is pish tegj glob glob ?<br>
	 * 2- how many Credits is glob prok Silver ?<br>
	 * So we need to prepare two different Answers for them. This method solves
	 * above problem.
	 * 
	 * @param sb
	 * @param elementVal
	 * @param isCredit
	 * @param isError
	 * @param entry
	 * @param elementName
	 * @param currencyVal
	 */
	private void updateCorrectAnswerInMap(StringBuffer sb, Float elementVal,
			boolean isCredit, boolean isError, Entry<Integer, String> entry,
			String elementName, int currencyVal) {

		if (isCredit) {
			if (elementVal == null) {
				outputAnswersMap.put(entry.getKey(),
						LangEnums.CANT_CALC_VALUE.getTextVal() + elementName);
				return;
			}
			sb.append(currencyVal * elementVal);
			sb.append(" " + LangEnums.CREDITS.getTextVal());
		} else {
			sb.append(currencyVal);
		}

		if (isError) {
			outputAnswersMap.put(entry.getKey(),
					LangEnums.UNKNOWN_QUESTION.getTextVal());
		} else {
			outputAnswersMap.put(entry.getKey(), sb.toString());
		}
	}

	public Map<String, Float> getCreditsData() {
		return Collections.unmodifiableMap(creditsPerUnitVal);
	}

	public Map<String, Character> getCurrencyMap() {
		return Collections.unmodifiableMap(interGalacticToRomanMap);
	}

	public Map<Integer, String> getInputData() {
		return Collections.unmodifiableMap(inputQuestionsMap);
	}

}
