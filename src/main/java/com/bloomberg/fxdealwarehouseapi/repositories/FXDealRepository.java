package com.bloomberg.fxdealwarehouseapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;

@Repository
public interface FXDealRepository extends CrudRepository<FXDeal, Integer>{
	
}
