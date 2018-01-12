package com.galaxy.inputData.processors.enums;

/**
 *  This class is being used to replace symbols/phrases used in input file.
 * Currently, it reads values from a properties file.
 * In future, if need be, it can be extended to read from DB or XML,etc.
 * 
 * @author Shashank Singh
 */

import java.io.InputStream;
import java.util.Properties;

public enum LangEnums {

	IS, HOW_MUCH_IS, HOW_MANY_CREDITS,HOW_MANY, CREDITS, QUESTION_MARK, CANT_CALC_VALUE, UNKNOWN_QUESTION;

	private static final String mapperFileName = "LanguageMapper.properties";
	private static final String propertyVal = "VAL";

	Properties prop = null;

	public String getProperty(String val) {
		readProperties();
		return prop.getProperty(this.name() + "." + val);
	}

	/**
	 * Properties are read from File ONLY Once. These values are loaded once and
	 * reused every time an Enum Constant is initialized
	 * 
	 * @return void
	 */
	private void readProperties() {
		if (null == prop) {
			prop = new Properties();
			try {
				InputStream is = ClassLoader
						.getSystemResourceAsStream(mapperFileName);
				prop.load(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getTextVal() {
		return getProperty(LangEnums.propertyVal);
	}
}
