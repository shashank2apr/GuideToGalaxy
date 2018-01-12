/**
 * 
 */
package com.galaxy.utilities;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;
import com.galaxy.Exceptions.CustomException;
import com.galaxy.Exceptions.ValidationFailException;
import com.galaxy.Exceptions.enums.ExceptionEnums;
import com.galaxy.utilities.RomanToNumericUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Shashnk Singh
 *
 */
public class RomanToNumericUtilsTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.galaxy.utilities.RomanToNumericUtils#convertRomanToNumeric(java.lang.String)}.
	 * @throws CustomException 
	 * @throws ValidationFailException 
	 */
	@Test
	public final void testConvertRomanToNumericCorrect() throws ValidationFailException, CustomException {
		assertEquals(RomanToNumericUtils.INSTANCE.convertRomanToNumeric("XVII"),17);
		assertEquals(RomanToNumericUtils.INSTANCE.convertRomanToNumeric("XCVIII"),98);
		assertEquals(RomanToNumericUtils.INSTANCE.convertRomanToNumeric("MMMCDLVI"),3456);
	}
	/**
	 * Test method for {@link com.galaxy.utilities.RomanToNumericUtils#convertRomanToNumeric(java.lang.String)}.
	 * @throws CustomException 
	 * @throws ValidationFailException 
	 */
	@Test
	public final void testConvertRomanToNumericInCorrect() throws ValidationFailException, CustomException {

		thrown.expect(ValidationFailException.class);
		RomanToNumericUtils.INSTANCE.convertRomanToNumeric("VXL");
		
		thrown.expect(ValidationFailException.class);
		RomanToNumericUtils.INSTANCE.convertRomanToNumeric("XXXX");
		
		thrown.expect(ValidationFailException.class);
		RomanToNumericUtils.INSTANCE.convertRomanToNumeric("ILX");
				
	}

}
