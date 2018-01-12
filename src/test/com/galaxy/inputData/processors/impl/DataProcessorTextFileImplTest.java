/**
 * 
 */
package com.galaxy.inputData.processors.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.ValidationFailException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.inputData.processors.DataProcessorFactory;
import com.galaxy.inputData.processors.enums.DataProcessorTypeEnum;
import com.galaxy.inputData.processors.interfaces.GenericDataProcessorIf;
import com.galaxy.outputData.processors.OutputProcessor;
import static org.hamcrest.core.StringStartsWith.startsWith;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Shashnk Singh
 *
 */
public class DataProcessorTextFileImplTest {

	private List<String> correctTestList, incorrectTestList, testListDataSaved;

	// Getting Data processor
	GenericDataProcessorIf dataProcessor = null;

	// Processing raw input Data
	OutputProcessor myReturnedObject;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		// Getting Data processor
		dataProcessor = DataProcessorFactory
				.getDataProcessor(DataProcessorTypeEnum.TEXT_FILE);

		correctTestList = Arrays
				.asList("glob is I",
						"prok is V",
						"pish is X",
						"tegj is L",
						"glob glob Silver is 34 Credits",
						"glob prok Gold is 57800 Credits",
						"pish pish Iron is 3910 Credits",
						"how much is pish tegj glob glob ?",
						"how many Credits is glob prok Silver ?",
						"how many Credits is glob prok Gold ?",
						"how many Credits is glob prok Iron ?",
						"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?",
						"how many Credits is prok prok Gold ?");
		incorrectTestList = Arrays
				.asList("glob is E");
		
		testListDataSaved = Arrays
				.asList("glob is I",
						"glob Silver is 34 Credits",
						"how much is glob glob ?",
						"how many Credits is glob glob Silver ?");
		
	}

	/**
	 * Test method for
	 * {@link com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl#processInputData(java.util.List)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testProcessInputDataCorrectObjectReturned()
			throws CustomException {

		try {
			myReturnedObject = dataProcessor.processInputData(correctTestList);

			assertNotNull(myReturnedObject);
			assertTrue(myReturnedObject instanceof OutputProcessor);
		} catch (Exception e) {
			fail("got Exception, i want an Expression");
		}

	}

	/**
	 * Test method for
	 * {@link com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl#processInputData(java.util.List)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testProcessInputDataPassNull() throws CustomException {
				thrown.expect(IllegalArgumentException.class);
				thrown.expectMessage(startsWith(ExceptionEnums.PARAM_CANT_BE_NULL_OR_EMPTY.getValue()));
		 	 	myReturnedObject = dataProcessor
						.processInputData(null);
		
	}
	/**
	 * Test method for
	 * {@link com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl#processInputData(java.util.List)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testProcessInputDataAllDataCorrectlySaved() throws CustomException {
				myReturnedObject = dataProcessor
						.processInputData(testListDataSaved);
				assertEquals(myReturnedObject.getCurrencyMap().get("glob"),new Character('I'));
				assertEquals(myReturnedObject.getCreditsData().get("Silver"),(Float)34.0f);
				assertEquals(myReturnedObject.getInputData().get(0),"how much is glob glob ?");
				assertEquals(myReturnedObject.getInputData().get(1),"how many Credits is glob glob Silver ?");
			}
	/**
	 * Test method for
	 * {@link com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl#processInputData(java.util.List)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testProcessInputDataWrongData() throws CustomException {
			
		thrown.expect(CustomException.class);
 	 	myReturnedObject = dataProcessor
						.processInputData(incorrectTestList);
			}

}
