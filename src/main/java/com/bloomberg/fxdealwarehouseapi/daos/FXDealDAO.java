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
//import org.springframework.data.jpa.repository.Query;

@Component
public class FXDealDAO {
	private FXDealRepository fxDealRepository;
	
	
	@Autowired
	private EntityManager entityManager;
	
	public FXDealDAO(FXDealRepository fxDealRepository){
		this.fxDealRepository = fxDealRepository;		
	}
	
//	@Query(value = "select from fxdeal where dealId = :dealId", nativeQuery = true)
//	public List<FXDeal> findDealsById(int dealId);
	
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
