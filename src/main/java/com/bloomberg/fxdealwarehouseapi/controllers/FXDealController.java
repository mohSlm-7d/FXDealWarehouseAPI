package com.bloomberg.fxdealwarehouseapi.controllers;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloomberg.fxdealwarehouseapi.FxDealWarehouseApiApplication;
import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;
import com.bloomberg.fxdealwarehouseapi.services.FXDealService;

@RestController
@RequestMapping("/bloomberg/warehouse/fxdeal/")
public class FXDealController {
	@Autowired
	private FXDealService fxDealService;	
	
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
