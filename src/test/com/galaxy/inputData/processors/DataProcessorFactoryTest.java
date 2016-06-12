/**
 * 
 */
package test.com.galaxy.inputData.processors;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertTrue;
import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.Exceptions.enums.ExceptionEnums;
import main.com.galaxy.inputData.processors.DataProcessorFactory;
import main.com.galaxy.inputData.processors.enums.DataProcessorTypeEnum;
import main.com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl;
import main.com.galaxy.inputData.processors.interfaces.GenericDataProcessorIf;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Shashank Singh
 *
 */
public class DataProcessorFactoryTest {

	DataProcessorTypeEnum correctProcessor, incorrectProcessor;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		correctProcessor=DataProcessorTypeEnum.TEXT_FILE;
		incorrectProcessor=DataProcessorTypeEnum.XML_FILE;
	}

	/**
	 * Test method for
	 * {@link main.com.galaxy.inputData.processors.DataProcessorFactory#getDataProcessor(main.com.galaxy.inputData.processors.enums.DataProcessorTypeEnum)}
	 * .
	 * @throws CustomException 
	 */
	@Test
	public final void testGetDataProcessorIncorrect() throws CustomException {
	
		thrown.expect(CustomException.class);
		thrown.expectMessage(startsWith(ExceptionEnums.DATA_PROCESSOR_NOT_FOUND.getValue()));
		GenericDataProcessorIf dataProcessor = DataProcessorFactory.getDataProcessor(incorrectProcessor);
	}
	/**
	 * Test method for
	 * {@link main.com.galaxy.inputData.processors.DataProcessorFactory#getDataProcessor(main.com.galaxy.inputData.processors.enums.DataProcessorTypeEnum)}
	 * .
	 * @throws CustomException 
	 */
	@Test
	public final void testGetDataProcessorCorrect() throws CustomException {
	
		GenericDataProcessorIf dataProcessor = DataProcessorFactory.getDataProcessor(correctProcessor);
		assertTrue(dataProcessor instanceof DataProcessorTextFileImpl);
	}

}
