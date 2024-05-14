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

@Component
public class FXDealValidator {
	public FXDealValidator() {
		super();
	}
	
	private void checkIfFieldsEmpty(FXDeal dealToValidate) throws InvalidDealFieldsException{
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking the new FXDeal's Fields whether they are empty or not]");
		
		if(dealToValidate.getDealId().equals("")) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealId Field is empty]");
			throw new InvalidDealFieldsException("The deal's ID cannot be empty.");
		}
		
		if(dealToValidate.getDealTimestamp().equals(null) ) {
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
	
	private void validateDealId(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's dealId Field is valid or not]");

		Pattern dealIdPattern = Pattern.compile("^[a-z0-9]{8,20}$", Pattern.CASE_INSENSITIVE);
		Matcher dealIdMatcher = dealIdPattern.matcher(dealToValidate.getDealId());
		
		if(!dealIdMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealId Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's ID must be an Alphanumeric String and its length must be at least of 8 characters and does not exceed 20 characters. The FX Deal's ID cannot contain spaces.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealId Field IS VALID]");
	}
	
	
	private void validateDealFromCurrencyISOCode(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's fromCurrencyISOCode Field is valid or not]");

		Pattern dealFromCurrencyISOCodePattern = Pattern.compile("^[0-9]{3}$");
		Matcher dealFromCurrencyISOCodeMatcher = dealFromCurrencyISOCodePattern.matcher(dealToValidate.getFromCurrencyISOCode());
		
		if(!dealFromCurrencyISOCodeMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's fromCurrencyISOCode Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's From Currency ISO Code must be a String that contains exactly 3 digits with no spaces.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's fromCurrencyISOCode Field IS VALID]");
	}
	
	private void validateDealToCurrencyISOCode(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's toCurrencyISOCode Field is valid or not]");
		
		Pattern dealToCurrencyISOCodePattern = Pattern.compile("^[0-9]{3}$");
		Matcher dealToCurrencyISOCodeMatcher = dealToCurrencyISOCodePattern.matcher(dealToValidate.getToCurrencyISOCode());
		
		if(!dealToCurrencyISOCodeMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's toCurrencyISOCode Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's To Currency ISO Code must be a String that contains exactly 3 digits with no spaces.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's toCurrencyISOCode Field IS VALID]");
	}
	
	private void validateDealTimestamp(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's dealTimestamp Field is valid or not]");

		if(dealToValidate.getDealTimestamp().isBefore(LocalDateTime.now())) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealTimestamp Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's From Currency ISO Code must be a String that contains exactly 3 digits with no spaces.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealTimestamp Field IS VALID]");
	}
	
	private void validateDealAmount(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Checking whether the new FXDeal's dealAmount Field is valid or not]");

		Pattern dealAmountPattern = Pattern.compile("^[0-9]{1,9}$");
		Matcher dealAmountMatcher = dealAmountPattern.matcher(dealToValidate.getDealAmount());
		
		if(!dealAmountMatcher.find()) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field is NOT VALID]");
			throw new InvalidDealFieldsException("The FX Deal's amount must be a positive whole number and do not contain a decimal point. The Deal Amount must have at least one digit and do not exceed 9 digits.");
		}
		
		if(Integer.valueOf(dealToValidate.getDealAmount()) == 0) {
			FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field is NOT VALID]");
			throw new InvalidDealFieldsException("The deal amount must be greater than 0.");
		}
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's dealAmount Field IS VALID]");
	}

	public void validateFXDeal(FXDeal dealToValidate) throws InvalidDealFieldsException{
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: Invoked validateFXDeal Method]");
		
		this.checkIfFieldsEmpty(dealToValidate);
		
		this.validateDealId(dealToValidate);
		this.validateDealFromCurrencyISOCode(dealToValidate);
		this.validateDealAmount(dealToValidate);
		this.validateDealToCurrencyISOCode(dealToValidate);
		this.validateDealTimestamp(dealToValidate);
		
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal's Fields are all valid]");
		FxDealWarehouseApiApplication.logger.info("[FXDealValidator: The new FXDeal is valid]");
//		return true;
	}
}
