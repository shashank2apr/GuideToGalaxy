/**
 * 
 */
package com.galaxy.utilities;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.utilities.FileUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Shashank Singh
 *
 */
public class FileUtilsTest {
	
	String correctFileName,notExistingFileName;
	List<String> correctTestList;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		correctFileName="Input.txt";
		notExistingFileName="Wrong.txt";
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
						"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
						);
		
	}

	/**
	 * Test method for {@link com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}.
	 * @throws CustomException 
	 */
	@Test
	public final void testReadFromFileNullFileName() throws CustomException {
		List<String> lines = new ArrayList<String>();

		thrown.expect(CustomException.class);
		thrown.expectMessage(startsWith(ExceptionEnums.FILE_CANT_BE_NULL_OR_EMPTY.getValue()));
		lines = FileUtils.readFromFile(null);
		
	}

	/**
	 * Test method for {@link com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}.
	 * @throws CustomException 
	 */
	@Test
	public final void testReadFromFileEmptyFileName() throws CustomException {
		List<String> lines = new ArrayList<String>();

		thrown.expect(CustomException.class);
		thrown.expectMessage(startsWith(ExceptionEnums.FILE_CANT_BE_NULL_OR_EMPTY.getValue()));
		lines = FileUtils.readFromFile("");
		
	}

	/**
	 * Test method for {@link com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}.
	 * @throws CustomException 
	 */
	@Test
	public final void testReadFromFileFileDoesNotExist() throws CustomException {
		List<String> lines = new ArrayList<String>();

		thrown.expect(CustomException.class);
		lines = FileUtils.readFromFile(notExistingFileName);
		
	}

	/**
	 * Test method for {@link com.galaxy.utilities.FileUtils#readFromFile(java.lang.String)}.
	 * @throws CustomException 
	 */
	@Test
	public final void testReadFromFileCorrect() throws CustomException{
		List<String> actual = new ArrayList<String>();
		List<String> expected = correctTestList;
		
		actual = FileUtils.readFromFile(correctFileName);
		assertEquals(actual,expected);
		
	}
}
