/**
 * 
 */
package test.com.galaxy.readers.impl;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.Exceptions.enums.ExceptionEnums;
import main.com.galaxy.utilities.FileUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Shashank Singh
 *
 */
public class InputFileReaderTextImplTest {

	String correctFileName, incorrectFileName;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		correctFileName = "Input.txt";
		incorrectFileName = "Wrong.txt";
	}

	/**
	 * Test method for
	 * {@link main.com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testReadInputFileNullFileName() throws CustomException {
		List<String> lines = new ArrayList<String>();

		thrown.expect(CustomException.class);
		thrown.expectMessage(startsWith(ExceptionEnums.FILE_CANT_BE_NULL_OR_EMPTY
				.getValue()));
		lines = FileUtils.readFromFile(null);

	}

	/**
	 * Test method for
	 * {@link main.com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testReadInputFileEmptyFileName() throws CustomException {
		List<String> lines = new ArrayList<String>();

		thrown.expect(CustomException.class);
		thrown.expectMessage(startsWith(ExceptionEnums.FILE_CANT_BE_NULL_OR_EMPTY
				.getValue()));
		lines = FileUtils.readFromFile("");

	}

	/**
	 * Test method for
	 * {@link main.com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testReadInputFileThrowsCustomException()
			throws CustomException {
		List<String> lines = new ArrayList<String>();

		thrown.expect(CustomException.class);
		lines = FileUtils.readFromFile(incorrectFileName);

	}

	/**
	 * Test method for
	 * {@link main.com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}
	 * .
	 * 
	 * @throws CustomException
	 */
	@Test
	public final void testReadInputFileCorrect() throws CustomException {
		List<String> actual = new ArrayList<String>();
		List<String> expected = Arrays
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
						"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		actual = FileUtils.readFromFile(correctFileName);
		
		assertEquals(actual, expected);

	}
}
