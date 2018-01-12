package com.galaxy.readers.interfaces;

/**
 * Interface for all Input File Processors.
 * Currently there is Implementation just for Text.
 * In future we might add XML or DB Processing too.
 * @author Shashank Singh
 */

import java.util.List;

import com.galaxy.Exceptions.CustomException;


public interface InputFileReaderIf {
	
	/**
	 * This method should return list of Strings present in ANY Allowed type of Input File.
	 * @return List<String>
	 * @throws CustomException 
	 */
	
	List<String> readInput(String filePath) throws CustomException;

}
