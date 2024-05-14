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

@Service
public class FXDealService {
	private FXDealDAO fxDealDao;	
	
	public FXDealService(FXDealDAO fxDealDao) {
		this.fxDealDao = fxDealDao;
	}
	
	
	public ResponseEntity<Object> saveDeal(FXDeal dealToImport) {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();

		try {
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Invoked the saveDeal Method]");
			
//			FX Deal Validation (ensuring that details(fields) of the deal are valid).
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Invoking the validateFXDeal Method from FXDealValidator.class]");
			FXDealValidator dealValidator = new FXDealValidator();
			dealValidator.validateFXDeal(dealToImport);
			
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
			
//			return ResponseEntity.badRequest().body(e.getMessage());
		}catch(IllegalDuplicateDealException e) {
//			Logger (log out the exception)
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Bad Request: " + e.getClass().getName() + "]");
			FxDealWarehouseApiApplication.logger.info(e.getMessage());
			
			responseMap.put("mssg", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
			
//			return ResponseEntity.badRequest().body(e.getMessage());
		}catch(Exception e){
//			Logger (log out the exception)
			FxDealWarehouseApiApplication.logger.info("[FXDealService: Internal Server Error: " + e.getClass().getName() + "]");
			FxDealWarehouseApiApplication.logger.info(e.getMessage());

			responseMap.put("mssg", e.getMessage());
			return ResponseEntity.badRequest().body(responseMap);
			
//			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}
