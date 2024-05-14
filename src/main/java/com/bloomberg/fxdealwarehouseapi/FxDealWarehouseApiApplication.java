package com.bloomberg.fxdealwarehouseapi;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;

@SpringBootApplication
public class FxDealWarehouseApiApplication {
	public static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(FxDealWarehouseApiApplication.class, args);
		logger.info("[FxDealWarehouseApiApplication: Started FxDealWarehouseApiApplication Application]");
		logger.info("[Time: " + Timestamp.valueOf(LocalDateTime.now()).toString() + "]");
	}

}
