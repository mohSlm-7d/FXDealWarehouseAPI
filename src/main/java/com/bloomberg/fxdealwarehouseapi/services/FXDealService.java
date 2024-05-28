package com.bloomberg.fxdealwarehouseapi.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bloomberg.fxdealwarehouseapi.FxDealWarehouseApiApplication;
import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;
import com.bloomberg.fxdealwarehouseapi.daos.FXDealDAO;
import com.bloomberg.fxdealwarehouseapi.exceptions.IllegalDuplicateDealException;
import com.bloomberg.fxdealwarehouseapi.exceptions.InvalidDealFieldsException;
import com.bloomberg.fxdealwarehouseapi.validations.FXDealValidator;

/**
 * The FXDealService class is a Service class that represents the business logic layer 
 * in the application, which is to save the new FXDeal into the DB appropriately.
 */
@Service
public class FXDealService {
	private FXDealDAO fxDealDao;	
	private FXDealValidator dealValidator;
	/**
	 * @param fxDealDao
	 * A parameterized constructor that is used 
	 * to Inject a bean instance of FXDealDAO and a bean instance of FXDealValidator Component types
	 * as dependencies(Constructor Injection) 
	 * by the Spring framework context-core(IOC Container).
	 */
	public FXDealService(FXDealDAO fxDealDao, FXDealValidator dealValidator) {
		this.fxDealDao = fxDealDao;
		this.dealValidator = dealValidator;
	}
	
	
	/**
	 * @param dealToImport
	 * @return ResponseEntity<Object>
	 * 
	 * The saveDeal Method handles the business logic of the application, which is to 
	 * insert the new FXDeal appropriately, in order to achieve that, the saveDeal Method 
	 * starts with validating the FX Deal(dealToImport) and its fields by invoking the validateFXDeal from the 
	 * FXDealValidator class, then, it invokes the saveDeal Method from the FXDealDAO class.
	 * 
	 * If there was any violation in the previous two steps, in situations like 
	 * when the deal is not valid or when the deal already exists in the DB, a customized exception with a proper error message 
	 * will be thrown and it will be handled in the saveDeal Method of FXDealService class
	 * and a proper ResponseEntity, which represents the response will be returned to the RESTful Controller.
	 */
	public ResponseEntity<Object> saveDeal(FXDeal dealToImport) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();

		try {
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Invoked the saveDeal Method]");
			
//			FX Deal Validation (ensuring that details(fields) of the deal are valid).
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Invoking the validateFXDeal Method from FXDealValidator.class]");
//			FXDealValidator dealValidator = new FXDealValidator();
//			dealValidator.validateFXDeal(dealToImport);
			this.dealValidator.validateFXDeal(dealToImport);
			
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Invoking the saveDeal Method from FXDealDAO.class]");
			this.fxDealDao.saveDeal(dealToImport);
			
			FxDealWarehouseApiApplication.logger.info("[FXDealService: The deal is persisted successfully]");
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Building the HashMap of the Success Response]");
			responseMap.put("mssg", "The deal is persisted successfully!");
			
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Returning the ResponseEntity of the Success Response]");
			return ResponseEntity.ok(responseMap);
		}catch(InvalidDealFieldsException e) {
//			Logger (log out the exception)
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Bad Request: " + e.getClass().getName() + "]");
			FxDealWarehouseApiApplication.logger.info(e.getMessage());
			
			responseMap.put("mssg", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
			
		}catch(IllegalDuplicateDealException e) {
//			Logger (log out the exception)
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Bad Request: " + e.getClass().getName() + "]");
			FxDealWarehouseApiApplication.logger.info(e.getMessage());
			
			responseMap.put("mssg", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
			
		}catch(Exception e){
//			Logger (log out the exception)
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Internal Server Error: " + e.getClass().getName() + "]");
			FxDealWarehouseApiApplication.logger.info(e.getMessage());

			responseMap.put("mssg", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
			
		}
	}
}
