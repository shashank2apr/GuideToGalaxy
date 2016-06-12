/**
 * 
 */
package test.com.galaxy.main;

import main.com.galaxy.main.Application;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Shashank Singh
 *
 */
public class ApplicationTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link main.com.galaxy.main.Application#main(java.lang.String[])}.
	 */
	@Test
	public final void testNullFileAsInputToMain() {
		
		Application.inputFileName = null;
		new Application();
		
		
	}


	/**
	 * Test method for {@link main.com.galaxy.main.Application#main(java.lang.String[])}.
	 */
	@Test
	public final void testEmptyFileAsInputToMain() {
		
		Application.inputFileName = "";
		new Application();
		
		
	}


	/**
	 * Test method for {@link main.com.galaxy.main.Application#main(java.lang.String[])}.
	 */
	@Test
	public final void testFileWithCorrectDataAsInputToMain() {
		
		Application.inputFileName = "Input.txt";
		new Application();
		
		
	}


	/**
	 * Test method for {@link main.com.galaxy.main.Application#main(java.lang.String[])}.
	 */
	@Test
	public final void testFileWithIncorrectDataAsInputToMain() {
		
		Application.inputFileName = "";
		new Application();
		
		
	}

}
