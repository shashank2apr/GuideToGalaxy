package main.com.galaxy.readers.impl;

/**  
 * Implementation of FileProcessor Interface to handle TEXT.
 * @author Shashank Singh
 *
 */

import java.util.ArrayList;
import java.util.List;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.readers.interfaces.InputFileReaderIf;
import main.com.galaxy.utilities.FileUtils;

public class InputFileReaderTextImpl implements InputFileReaderIf {

	List<String> inputLines = new ArrayList<String>();

	/** 
	 * This method returns list of Strings present in Text Input File.
	 * In future, this can be modified to contain some more logic/validation
	 * @throws CustomException 
	 * @see main.com.galaxy.InputFileReaderIf.readers.InputFileReader#readInput(java.lang.String)
	 */
	public List<String> readInput(String filePath) throws CustomException {
		
		List<String> lines = new ArrayList<String>();

		lines = FileUtils.readFromFile(filePath);
		
		return lines;
	}

	public InputFileReaderTextImpl() {
		//Add any functionality if needed.
	}
}
