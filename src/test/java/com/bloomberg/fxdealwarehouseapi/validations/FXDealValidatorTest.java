/**
 * 
 */
package com.bloomberg.fxdealwarehouseapi.validations;

import static org.junit.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bloomberg.fxdealwarehouseapi.FxDealWarehouseApiApplication;
import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;
import com.bloomberg.fxdealwarehouseapi.exceptions.InvalidDealFieldsException;

/**
 * 
 */
public class FXDealValidatorTest {
	
	
	@Test
	public void testCheckIfDealIDEmpty() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "", "400", "740", LocalDateTime.now(), "7854123");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The deal's ID cannot be empty.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);	

	}
	
	@Test
	public void testCheckIfDealAmountEmpty() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345566", "400", "740", LocalDateTime.now(), "");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The deal amount cannot be empty.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testCheckIfDealFromCurrencyEmpty() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345566", "", "740", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The deal's from currency ISO code cannot be empty.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testCheckIfDealToCurrencyEmpty() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345566", "400", "", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The deal's to currency ISO code cannot be empty.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testCheckIfDealDateTimeEmpty() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345566", "400", "700", null, "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The Timestamp of the deal cannot be empty.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testValidateDealIDSpecialCharacter() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345566?", "400", "700", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's ID must be an Alphanumeric String and its length must be at least of 8 characters and does not exceed 20 characters. The FX Deal's ID cannot contain spaces.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testValidateDealIDLessThan8() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "1234", "400", "700", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's ID must be an Alphanumeric String and its length must be at least of 8 characters and does not exceed 20 characters. The FX Deal's ID cannot contain spaces.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testValidateDealIDMoreThan20() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "123456789123456789123", "400", "700", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's ID must be an Alphanumeric String and its length must be at least of 8 characters and does not exceed 20 characters. The FX Deal's ID cannot contain spaces.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	
	@Test
	public void testCurrncyIsoCodeLength() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345678", "9087", "700", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's Currency ISO Codes must be String that contain exactly 3 digits with no spaces.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testCurrncyIsoCodeWithAlpha() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345678", "908", "ISO", LocalDateTime.now(), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's Currency ISO Codes must be String that contain exactly 3 digits with no spaces.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	
	@Test
	public void testDealTimestampInPast() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345678", "908", "123", LocalDateTime.now().minus(1, ChronoUnit.DAYS), "8774");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's Timestamp must be today or in future.";
        String actualMessage = exception.getMessage();
        System.out.println("actualMessage : " + actualMessage);
        
        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testDealAmountLessThanZero() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345678", "908", "ISO", LocalDateTime.now(), "0");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The deal amount must be greater than 0.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	@Test
	public void testDealAmountLength() {
		
		FXDealValidator validator = new FXDealValidator();
		FXDeal dealToValidate = new FXDeal(123456, "12345678", "908", "ISO", LocalDateTime.now(), "789456123658");
	
		InvalidDealFieldsException exception = assertThrows(InvalidDealFieldsException.class, () -> {
           validator.validateFXDeal(dealToValidate) ;
        });

        String expectedMessage = "The FX Deal's amount must be a positive whole number and do not contain a decimal point. The Deal Amount must have at least one digit and do not exceed 9 digits.";
        String actualMessage = exception.getMessage();

        // Asserting the exception message
        assert actualMessage.contains(expectedMessage);
	}
	
	
	
	
	



}
