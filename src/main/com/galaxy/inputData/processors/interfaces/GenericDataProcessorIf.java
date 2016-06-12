package main.com.galaxy.inputData.processors.interfaces;

/**
 * Interface for all Data Processors.
 * Currently there is implementation Only for Text.
 * In future we might add XML Processing too.
 * @author Shashank Singh
 */

import java.util.List;

import main.com.galaxy.Exceptions.CustomException;
import main.com.galaxy.outputData.processors.OutputProcessor;

public interface GenericDataProcessorIf {

	OutputProcessor processInputData(List<String> rawInputData) throws CustomException;

}
