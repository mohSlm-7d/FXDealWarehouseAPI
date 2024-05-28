package com.bloomberg.fxdealwarehouseapi.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloomberg.fxdealwarehouseapi.FxDealWarehouseApiApplication;
import com.bloomberg.fxdealwarehouseapi.beans.FXDeal;
import com.bloomberg.fxdealwarehouseapi.exceptions.IllegalDuplicateDealException;
import com.bloomberg.fxdealwarehouseapi.repositories.FXDealRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * This FXDealDAO class acts as an intermediate layer between the FXDealService and 
 * the FXDealRepository, FXDealDAO checks if the deal already exists in the DB 
 * before inserting it into the DB.
 */
@Component
public class FXDealDAO {
	private FXDealRepository fxDealRepository;
	
	
//	@Autowired
	private EntityManager entityManager;
	
	
	/**
	 * @param fxDealRepository
	 * 
	 * A parameterized constructor that is used 
	 * to Inject a bean instance of FXDealRepository which is a Repository, 
	 * and a bean instance of EntityManager
	 * Component type, 
	 * as dependencies(Constructor Injection) 
	 * by the Spring framework context-core(IOC Container).
	 */
	public FXDealDAO(FXDealRepository fxDealRepository, EntityManager entityManager){
		this.fxDealRepository = fxDealRepository;
		this.entityManager = entityManager;
	}

	
	/**
	 * @param newFxDeal
	 * @throws IllegalDuplicateDealException
	 * 
	 * The saveDeal Method checks if the newFxDeal, that is received as a parameter, 
	 * already exists in the DB, and if the deal already exists the saveDeal Method will throw a 
	 * IllegalDuplicateDealException that will be handled properly in the FXDealService and the 
	 * Service will return the appropriate response, otherwise, if the deal does not exist in the DB, 
	 * the saveDeal Method in the FXDealRepository will be invoked and the deal will be inserted into the DB.
	 * 
	 */
	public void saveDeal(FXDeal newFxDeal) throws IllegalDuplicateDealException{
		FxDealWarehouseApiApplication.logger.info("[FXDealDAO: invoked saveDeal Method]");
		
		FxDealWarehouseApiApplication.logger.info("[FXDealDAO: Checking the new FXDeal if it already exists in DB]");
		Query query =  this.entityManager.createQuery("from FXDeal where dealId = :dealId", FXDeal.class).setParameter("dealId", newFxDeal.getDealId());
		List<FXDeal> existingDealsOfSameId = (List<FXDeal>)query.getResultList();
		
		if(existingDealsOfSameId.size() > 0) {
			FxDealWarehouseApiApplication.logger.info("[FXDealDAO: The new FXDeal ALREADY EXISTS in DB]");
			throw new IllegalDuplicateDealException();
		}
		
		this.fxDealRepository.save(newFxDeal);
		FxDealWarehouseApiApplication.logger.info("[FXDealDAO: Successfully persisted a new FXDeal]");
	}
}
