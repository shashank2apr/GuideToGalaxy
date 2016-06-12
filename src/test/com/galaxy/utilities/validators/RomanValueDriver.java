package test.com.galaxy.utilities.validators;

import java.util.List;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.Exceptions.ValidationFailException;
import main.com.galaxy.readers.InputFileReaderFactory;
import main.com.galaxy.readers.enums.FileTypeEnum;
import main.com.galaxy.readers.interfaces.InputFileReaderIf;
import main.com.galaxy.utilities.RomanToNumericUtils;

public class RomanValueDriver {
	
	private static final String fileName="romanLiterals.txt";

	public static void main(String[] args)  {
	
		InputFileReaderIf ip = null;
		try {
			ip = InputFileReaderFactory.getProcessor(FileTypeEnum.TEXT_FILE);
		} catch (CustomException e) {
			e.printStackTrace();
			System.err.println(e.getMessage()+" System Exiting!");
			System.exit(-1);
		}
		// Getting raw file data
		List<String> fileData = null;
		try {
			fileData = ip.readInput(fileName);
		} catch (CustomException e) {
			System.err.println(e.getMessage()+" System Exiting!\n");
			e.printStackTrace();
			System.exit(-1);
			}
		
		for (String string : fileData) {
			System.out.print(string);
			try {
				System.out.print(" " + RomanToNumericUtils.INSTANCE.convertRomanToNumeric(string));
			} catch (ValidationFailException e) {
				System.err.println(" "+e.getMessage()+"\n");
				e.printStackTrace();
				
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}
	}

}
