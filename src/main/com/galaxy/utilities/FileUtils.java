package com.galaxy.utilities;

/*
 * Utility class for File related operations
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.enums.ExceptionEnums;

public class FileUtils {

	/**
	 * @param fileName
	 * @return
	 * @throws CustomException 
	 */
	public static List<String> readFromFile(String fileName) throws CustomException {
		
		if(fileName==null||fileName.isEmpty()){
			throw new CustomException(ExceptionEnums.FILE_CANT_BE_NULL_OR_EMPTY.getValue()+" : "+fileName);
		}
		
		InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
		if(is==null){
			throw new CustomException(ExceptionEnums.CANT_READ_FILE.getValue()+" : "+fileName);
		}
		
		BufferedReader br = null;
		List<String> lines = new ArrayList<String>();

		try {
			String sCurrentLine;

			br = new BufferedReader(new InputStreamReader(is)); //new FileReader(is));

			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}

		} catch(FileNotFoundException e){
			lines= null;
			e.printStackTrace();
		}
		catch (IOException e) {
			lines=null;
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		if(lines==null){
			throw new CustomException(ExceptionEnums.CANT_READ_FILE.getValue()+" : "+fileName);
		}
		return lines;
	}
}