package main.com.galaxy.readers;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.Exceptions.enums.ExceptionEnums;
import main.com.galaxy.readers.enums.FileTypeEnum;
import main.com.galaxy.readers.impl.InputFileReaderTextImpl;
import main.com.galaxy.readers.interfaces.InputFileReaderIf;

/**
 * Factory for all File Processors. 
 * Currently it is just for Text. In future
 * we might add XML Processor or DBProcessor.
 * @author Shashank Singh
 */

public class InputFileReaderFactory {

	public static InputFileReaderTextImpl tfp = null;

	/**
	 * Factory method returns which Processor to be used.
	 * 
	 * @return InputProcessor
	 * @throws CustomException 
	 */
	public static InputFileReaderIf getProcessor(FileTypeEnum type) throws CustomException {

		switch (type) {

		case TEXT_FILE:
			if (tfp == null) {
				return tfp = new InputFileReaderTextImpl();
			}
			return tfp;
		}
		throw new CustomException(ExceptionEnums.FILE_READER_NOT_FOUND.getValue());
	}

	private InputFileReaderFactory() {

	}

}
