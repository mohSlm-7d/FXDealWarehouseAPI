package com.bloomberg.fxdealwarehouseapi.validations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.bloomberg.fxdealwarehouseapi.FxDealWarehouseApiApplication;
import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;
import com.bloomberg.fxdealwarehouseapi.exceptions.InvalidDealFieldsException;

/**
 * 
 */
@Component
public class FXDealValidator {
	/**
	 * The FXDealValidator Default Constrctor, used to build a bean 
	 * of the FXDealValidator class.
	 */
	public FXDealValidator() {
		super();
	}
	
	
	
	
	/**
	 * @param dealToValidate
	 * @throws InvalidDealFieldsException
	 * 
	 * The validateFXDeal Method is the only public Method 
	 * that is used to accept the FXDeal that needs to be validated
	 * and in the inside of this validateFXDeal Method it invokes
	 * the other Methods to validate the deal. In case that the FXDeal is not valid, 
	 * this Method will throw an InvalidDealFieldsException with a 
	 * proper error message.
	 */
	public void validateFXDeal(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Invoked validateFXDeal Method]");
		
		this.checkIfFieldsEmpty(dealToValidate);
		
		this.validateDealId(dealToValidate.getDealId());
		this.validateDealCurrencyISOCode(dealToValidate.getFromCurrencyISOCode());
		this.validateDealAmount(dealToValidate.getDealAmount());
		this.validateDealCurrencyISOCode(dealToValidate.getToCurrencyISOCode());
		this.validateDealTimestamp(dealToValidate.getDealTimestamp());
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's Fields are all valid]");
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal is valid]");
	}
	
	
	
	
	
	
	/**
	 * @param dealToValidate
	 * @throws InvalidDealFieldsException
	 * 
	 * The checkIfFieldsEmpty Method is used to ensure that all of the FXDeal fields 
	 * are not empty, otherwise it will throw an InvalidDealFieldsException with a 
	 * proper error message.
	 */
	private void checkIfFieldsEmpty(FXDeal dealToValidate) throws InvalidDealFieldsException{
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking the new FXDeal's Fields whether they are empty or not]");
		
		if(dealToValidate.getDealId().equals("")) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealId Field is empty]");
			throw new InvalidDealFieldsException("The deal's ID cannot be empty.");
		}
		
		if(dealToValidate.getDealTimestamp() == null ) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealTimestamp Field is empty]");
			throw new InvalidDealFieldsException("The Timestamp of the deal cannot be empty.");
		}
		
		if(dealToValidate.getDealAmount().equals("")) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field is empty]");
			throw new InvalidDealFieldsException("The deal amount cannot be empty.");
		}
		
		
		if(dealToValidate.getFromCurrencyISOCode().equals("")) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's fromCurrencyISOCode Field is empty]");
			throw new InvalidDealFieldsException("The deal's from currency ISO code cannot be empty.");
		}
		
		if(dealToValidate.getToCurrencyISOCode().equals("")) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's toCurrencyISOCode Field is empty]");
			throw new InvalidDealFieldsException("The deal's to currency ISO code cannot be empty.");
		}
		
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's Fields are not empty]");
		
	}
	
	
	
	
	
	/**
	 * @param dealId
	 * @throws InvalidDealFieldsException
	 * 
	 * The validateDealId Method is a method that is invoked to ensure that 
	 * the FXDeal ID is only an alphanumeric String, it does not contain any spaces, 
	 * and its length is in between 8 and 20 characters (inclusive), 
	 * otherwise it will throw an InvalidDealFieldsException with a 
	 * proper error message.
	 */
	private void validateDealId(String dealId) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's dealId Field is valid or not]");

		Pattern dealIdPattern = Pattern.compile("^[a-z0-9]{8,20}$", Pattern.CASE_INSENSITIVE);
		Matcher dealIdMatcher = dealIdPattern.matcher(dealId);
		
		if(!dealIdMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealId Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's ID must be an Alphanumeric String and its length must be at least of 8 characters and does not exceed 20 characters. The FX Deal's ID cannot contain spaces.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealId Field IS VALID]");
	}
	
	
	
	
	
	/**
	 * @param isoCode
	* @throws InvalidDealFieldsException
	 * 
	 * The validateDealCurrencyISOCode Method is a method that is invoked to ensure that 
	 * the FXDeal's From and To CurrencyISOCode Fields are 3-digit Strings, 
	 * they do not contain any spaces, 
	 * and its length is exactly 3 digits and they only contain numbers, 
	 * otherwise it will throw an InvalidDealFieldsException with a 
	 * proper error message.
	 */
	private void validateDealCurrencyISOCode(String isoCode) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's fromCurrencyISOCode Field is valid or not]");

		Pattern dealCurrencyISOCodePattern = Pattern.compile("^[0-9]{3}$");
		Matcher dealCurrencyISOCodeMatcher = dealCurrencyISOCodePattern.matcher(isoCode);
		
		if(!dealCurrencyISOCodeMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's From or/and To CurrencyISOCode Fields are NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's Currency ISO Codes must be String that contain exactly 3 digits with no spaces.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's From or/and To CurrencyISOCode Fields ARE VALID]");
	}
	
		
	
	
	/**
	 * @param dealTimestamp
	 * @throws InvalidDealFieldsException
	 * 
	 * The validateDealTimestamp Method is invoked to ensure that 
	 * the FXDeal's Timestamp must be today or in future and it cannot be in the past, 
	 * and it cannot be empty(null), otherwise it will throw an InvalidDealFieldsException with a 
	 * proper error message.
	 */
	private void validateDealTimestamp(Timestamp dealTimestamp) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's dealTimestamp Field is valid or not]");

		if(dealTimestamp.before(Timestamp.valueOf(LocalDateTime.now()))) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealTimestamp Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's Timestamp must be today or in future.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealTimestamp Field IS VALID]");
	}
	
	
	/**
	 * @param dealAmount
	 * @throws InvalidDealFieldsException
	 * The validateDealAmount is used to check that the deal amount is valid, 
	 * by ensuring that it contains only digits and no decimal point, and
	 * at least its length is 1 digit and maximum 9 digits.
	 */
	private void validateDealAmount(String dealAmount) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's dealAmount Field is valid or not]");

		Pattern dealAmountPattern = Pattern.compile("^[0-9]{1,9}$");
		Matcher dealAmountMatcher = dealAmountPattern.matcher(dealAmount);
		
		if(!dealAmountMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's amount must be a positive whole number and do not contain a decimal point. The Deal Amount must have at least one digit and do not exceed 9 digits.");
		}
		
		if(Integer.valueOf(dealAmount) == 0) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field is NOT VALID]");
			throw new InvalidDealFieldsException("The deal amount must be greater than 0.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field IS VALID]");
	}

}
