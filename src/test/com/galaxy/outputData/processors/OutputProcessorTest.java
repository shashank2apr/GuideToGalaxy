/**
 * 
 */
package com.galaxy.outputData.processors;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.inputData.processors.DataProcessorFactory;
import com.galaxy.inputData.processors.enums.DataProcessorTypeEnum;
import com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl;
import com.galaxy.inputData.processors.interfaces.GenericDataProcessorIf;
import com.galaxy.outputData.processors.OutputProcessor;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Shashnk Singh
 *
 */
public class OutputProcessorTest {

	private String correctFileName;
	private String incorrectFileName;
	private List<String> correctTestList;
	private Map<Integer, String> correctOutput;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		correctFileName = "Input.txt";
		incorrectFileName = "Wrong.txt";
		correctTestList = Arrays
				.asList("glob is I", "prok is V", "pish is X", "tegj is L",
						"glob glob Silver is 34 Credits",
						"glob prok Gold is 57800 Credits",
						"pish pish Iron is 3910 Credits",
						"how much is pish tegj glob glob ?",
						"how many Credits is glob prok Silver ?",
						"how many Credits is glob prok Gold ?",
						"how many Credits is glob prok Iron ?",
						"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		populateCorrectOutput();

	}

	/**
	 * 
	 */
	private void populateCorrectOutput() {
		correctOutput = new LinkedHashMap<>();
		correctOutput.put(0, "pish tegj glob glob is 42");
		correctOutput.put(1, "glob prok Silver is 68.0 credits");
		correctOutput.put(2, "glob prok Gold is 57800.0 credits");
		correctOutput.put(3, "glob prok Iron is 782.0 credits");
		correctOutput.put(4, "I have no Idea What you are talking about!");
	}

	/**
	 * Test method for
	 * {@link com.galaxy.outputData.processors.OutputProcessor#generateOutput()}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testGenerateOutput() throws CustomException {

		OutputProcessor processedInputData = new DataProcessorTextFileImpl()
				.processInputData(correctTestList);
		Map<Integer, String> actualOutput = processedInputData.generateOutput();

		assertEquals(actualOutput, correctOutput);
	}

}
