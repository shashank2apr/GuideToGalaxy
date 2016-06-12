package main.com.galaxy.main;

/**
 * ****************************** DESCRIPTION *******************************************
 * This is the Starting point of running this application.
 * It performs below 4 steps to solve the problem:
 * 	1- Read Input by finding a suitable InputFileProcessor.
 * 	2- Process the input according to the requirements and store it in suitable format.
 * 	3- Process Input data stored in Step 2 to form meaningful Output.
 * 	4- Present the output in desired format. 
 * 
 * @author Shashank Singh
 * ****************************************************************************************
 */

import java.util.List;
import java.util.Map;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.inputData.processors.DataProcessorFactory;
import main.com.galaxy.inputData.processors.enums.DataProcessorTypeEnum;
import main.com.galaxy.inputData.processors.interfaces.GenericDataProcessorIf;
import main.com.galaxy.outputData.processors.OutputProcessor;
import main.com.galaxy.readers.InputFileReaderFactory;
import main.com.galaxy.readers.enums.FileTypeEnum;
import main.com.galaxy.readers.interfaces.InputFileReaderIf;

public class Application {

	public static String inputFileName = "Input.txt";

	public static void main(String[] args) {

		startGuideToGalaxy();

	}

	/**
	 * This is the Starting point of running this application.<br>
	 * It performs below four steps to solve the problem:<br>
	 * 1- Read Input by finding a suitable InputFileProcessor. <br>
	 * 2- Process the input according to the requirements and store it in
	 * suitable format.<br>
	 * 3- Process Input data stored in Step 2 to form meaningful Output.<br>
	 * 4- Present the output in desired format.
	 * 
	 */
	private static void startGuideToGalaxy() {
		/*
		 * STEP 1: Read Input by finding a suitable InputFileProcessor.
		 */
		List<String> fileData = null;
		try {
			fileData = performReadInput();
		} catch (CustomException e) {
			System.err.println(e.getMessage() + " System Exiting!\n");
			e.printStackTrace();
			System.exit(-1);
		}

		/*
		 * STEP 2: Process this input and store all important information in
		 * required format so that it can be manipulated later on.
		 */
		OutputProcessor processedInputData = null;
		try {
			processedInputData = performProcessInputData(fileData);
		} catch (CustomException e) {
			System.err.println(e.getMessage() + " System Exiting!\n");
			e.printStackTrace();
			System.exit(-1);
		}

		/*
		 * STEP 3: Manipulate the processed input to form meaningful output
		 */
		Map<Integer, String> outputData = performGenerateOutputData(processedInputData);

		/*
		 * STEP 4: Now that we have got the final Results, we need to format in
		 * some desired output format and present/save/publish it.
		 */
		performFormatOutputData(processedInputData, outputData);
	}

	/**
	 * Reads Data from Input source in following two steps: <br>
	 * 1- Calls Factory method,to get the InputFileProcessor to be used. <br>
	 * 2- Call readInput for appropriate InputFileProcessor.
	 * 
	 * @return List<String> containing all data in input source.
	 * @throws CustomException
	 */
	private static List<String> performReadInput() throws CustomException {

		InputFileReaderIf inputProcessor = InputFileReaderFactory
				.getProcessor(FileTypeEnum.TEXT_FILE);
		List<String> fileData = inputProcessor.readInput(inputFileName);

		return fileData;
	}

	/**
	 * Processes the input data and perform required processing on it. This
	 * processed input data can be manipulated later to generate meaningful
	 * output.
	 * 
	 * @param fileData
	 * @return
	 * @throws CustomException
	 */
	private static OutputProcessor performProcessInputData(List<String> fileData)
			throws CustomException {
		// Getting Data processor
		GenericDataProcessorIf dataProcessor = null;
		dataProcessor = DataProcessorFactory
				.getDataProcessor(DataProcessorTypeEnum.TEXT_FILE);

		// Processing raw input Data
		OutputProcessor processedInputData = null;
		processedInputData = dataProcessor.processInputData(fileData);
		return processedInputData;
	}

	/**
	 * Uses the processed Input Data generated in previous step to generate
	 * meaningful output data.
	 * 
	 * @param processedInputData
	 * @return
	 */
	private static Map<Integer, String> performGenerateOutputData(
			OutputProcessor processedInputData) {
		Map<Integer, String> outputData = processedInputData.generateOutput();
		return outputData;
	}

	/**
	 * Delivers the output data in desired format. Currently this methos simply
	 * print output to console. In future, it can be extended to form more
	 * complex output structure to more output sources.
	 * 
	 * Current rules-: If there is a problem with Validation of Roman Numerals,
	 * then output is:
	 * "Problem in Input Data : Not able to calculate the value/credits for :".
	 * Else, for any other problem in input question, be it wrong metal name,
	 * wrong InterGalacticUnit name or anything else, out put is -
	 * "I have no idea what you are talking about"
	 * 
	 * These output Texts are modifiable using Language.properties file
	 * 
	 * @param processedInputData
	 * @param output
	 */
	private static void performFormatOutputData(
			OutputProcessor processedInputData, Map<Integer, String> output) {

		for (Map.Entry<Integer, String> entry : output.entrySet()) {
			System.out.println(entry.getValue());
		}

		/*
		 * System.out.println("Credit Map ==> " +
		 * processedInputData.getCreditsData() + "\n");
		 * System.out.println("Currency Map ==> " +
		 * processedInputData.getCurrencyMap() + "\n");
		 * System.out.println("InputData Map ==> " +
		 * processedInputData.getInputData() + "\n");
		 * System.out.println("OutputData Map ==> " + output +"\n");
		 */

	}

}
