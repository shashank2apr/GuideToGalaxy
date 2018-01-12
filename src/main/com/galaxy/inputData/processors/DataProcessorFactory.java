package com.galaxy.inputData.processors;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.inputData.processors.enums.DataProcessorTypeEnum;
import com.galaxy.inputData.processors.impl.DataProcessorTextFileImpl;
import com.galaxy.inputData.processors.interfaces.GenericDataProcessorIf;

/**
 * Factory for all Data Processors. Currently it is just for Text file input. In
 * future we might add XML or DB input too.
 * 
 * @author Shashank Singh
 */

public class DataProcessorFactory {

	private static DataProcessorTextFileImpl fileDataProcessor = null;

	/**
	 * Factory method to provide Required DataProcessor
	 * based on ProcessingDataTypeEnum passed
	 * @param type
	 * @return
	 * @throws CustomException
	 */
	public static GenericDataProcessorIf getDataProcessor(
			DataProcessorTypeEnum type) throws CustomException {

		switch (type) {

		case TEXT_FILE:
			if (fileDataProcessor == null) {

				fileDataProcessor = new DataProcessorTextFileImpl();
			}
			break;
		case XML_FILE:
			fileDataProcessor = null;
			//Generate DataProcessorXMLImpl here
			break;
		}
		
		if(fileDataProcessor == null)
			throw new CustomException(ExceptionEnums.DATA_PROCESSOR_NOT_FOUND.getValue());
		
		return fileDataProcessor;
	}

}
