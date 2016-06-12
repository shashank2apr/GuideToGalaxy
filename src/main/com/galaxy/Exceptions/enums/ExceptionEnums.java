package main.com.galaxy.Exceptions.enums;

public enum ExceptionEnums {
	FILE_READER_NOT_FOUND("File Reader Not Found"), FILE_CANT_BE_NULL_OR_EMPTY(
			"File Name can not be  Null or Empty"), CANT_READ_FILE(
			"Problem in Reading Input File"), DATA_PROCESSOR_NOT_FOUND(
			"Data Processor Not Found"), PARAM_CANT_BE_NULL_OR_EMPTY(
			"Input parameter can not be  Null or Empty"), NOT_ROMAN(
			"Not a Roman Numeral"), NO_ROMAN_NUMERALS(
			"No Roman Numerals Present in Input");

	private final String value;

	private ExceptionEnums(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

}
