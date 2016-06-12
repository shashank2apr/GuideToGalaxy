package test.com.galaxy.utilities.validators;

import java.util.List;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.Exceptions.ValidationFailException;
import main.com.galaxy.Exceptions.enums.ExceptionEnums;
import main.com.galaxy.readers.InputFileReaderFactory;
import main.com.galaxy.readers.enums.FileTypeEnum;
import main.com.galaxy.readers.interfaces.InputFileReaderIf;
import main.com.galaxy.utilities.validators.RomanNumberValidator;

public class RomanLiteralValidationTest {

	private static final String fileName="romanLiterals.txt";
	
	public static void main(String[] args) {
		InputFileReaderIf ip = null;
		try {
			ip = InputFileReaderFactory.getProcessor(FileTypeEnum.TEXT_FILE);
		} catch (CustomException e) {
			
			if(e.getMessage()==ExceptionEnums.FILE_READER_NOT_FOUND.getValue()){
				System.err.println(e.getMessage()+" System Exiting!");
				System.exit(-1);;
			}
			System.err.println(" "+e.getMessage()+"\n");
			e.printStackTrace();
		}

		// Getting raw file data
		List<String> fileData = null;
		try {
			fileData = ip.readInput(fileName);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String string : fileData) {
			System.out.println(string);
			try {
				RomanNumberValidator.validate(string);
			} catch (ValidationFailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
