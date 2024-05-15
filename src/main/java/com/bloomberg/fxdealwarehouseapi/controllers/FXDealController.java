package com.bloomberg.fxdealwarehouseapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloomberg.fxdealwarehouseapi.FxDealWarehouseApiApplication;
import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;
import com.bloomberg.fxdealwarehouseapi.services.FXDealService;

/**
 * FXDealController is a RESTful Controller that is mapped to the path (/bloomberg/warehouse/fxdeal/).
 * The FXDealController handles the RESTful request and represents the RESTful API of the FX Deal Warehouse System.
 * It has fxDealService field of type FXDealService as a dependency bean instance to be injected 
 * by Spring framework(Field Injection), to use the fxDealService bean instance to handle the 
 * business logic layer.
 */
@RestController
@RequestMapping("/bloomberg/warehouse/fxdeal/")
public class FXDealController {
	@Autowired
	private FXDealService fxDealService;	
	
	/**
	 * @param dealToImport
	 * @return response
	 * 
	 * The saveDeal Method handles the requests to import the new FXDeals into the DB, and returns a proper response, which is returned 
	 * by the saveDeal Method from the FXDealService that is invoked by the fxDealService bean instance.
	 */
	@PostMapping("save")
	public ResponseEntity<Object> saveDeal(@RequestBody FXDeal dealToImport){
		FxDealWarehouseApiApplication.logger.info("[FXDealController: Received a new RESTful POST request]");
		FxDealWarehouseApiApplication.logger.info("[FXDealController: Request Body(dealToImport): " +dealToImport + "]");
		
		FxDealWarehouseApiApplication.logger.info("[FXDealController: Invoking the saveDeal Method in FXDealService.class]");
		ResponseEntity<Object> response = this.fxDealService.saveDeal(dealToImport);
		FxDealWarehouseApiApplication.logger.info("[FXDealController: Returning the ResponseEntity that is returned from saveDeal Method in FXDealService.class]");
		FxDealWarehouseApiApplication.logger.info("[FXDealController: the ResponseEntity that is returned from saveDeal Method in FXDealService.class. \n ResponseEntity(response): " + response + "]");
		return response;
	}
}
