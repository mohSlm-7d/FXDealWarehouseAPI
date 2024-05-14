package com.bloomberg.fxdealwarehouseapi.beans;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/**
 * 
 */
@Component
@Entity
public class FXDeal {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="dealSequentialIdGen")
	@SequenceGenerator(name = "dealSequentialIdGen", sequenceName = "deal_sequential_id")
	@Column(name = "deal_sequential_id")
	private int sequentialId;
	
	@Column(nullable = false, unique = true)
	private String dealId;
	
	@Column(name = "from_currency_iso_code", nullable = false)
	private String fromCurrencyISOCode; // "Ordering Currency"
	
	@Column(name = "to_currency_iso_code", nullable = false)
	private String toCurrencyISOCode;
	
	@Column(nullable = false)
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dealTimestamp;
	
	@Column(nullable = false)
	private String dealAmount; // in ordering currency

	
	
	
	
//	
	
	/**
	 * @return
	 */
	public int getSequentialId() {
		return sequentialId;
	}

	public FXDeal(int sequentialId, String dealId, String fromCurrencyISOCode, String toCurrencyISOCode,
		LocalDateTime dealTimestamp, String dealAmount) {
	super();
	this.sequentialId = sequentialId;
	this.dealId = dealId;
	this.fromCurrencyISOCode = fromCurrencyISOCode;
	this.toCurrencyISOCode = toCurrencyISOCode;
	this.dealTimestamp = dealTimestamp;
	this.dealAmount = dealAmount;
}

	public void setSequentialId(int sequentialId) {
		this.sequentialId = sequentialId;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getFromCurrencyISOCode() {
		return fromCurrencyISOCode;
	}

	public void setFromCurrencyISOCode(String fromCurrencyISOCode) {
		this.fromCurrencyISOCode = fromCurrencyISOCode;
	}

	public String getToCurrencyISOCode() {
		return toCurrencyISOCode;
	}

	public void setToCurrencyISOCode(String toCurrencyISOCode) {
		this.toCurrencyISOCode = toCurrencyISOCode;
	}

	public LocalDateTime getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(LocalDateTime dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	
	
	@Override
	public String toString() {
		return "FXDeal [sequentialId=" + sequentialId + ", dealId=" + dealId + ", fromCurrencyISOCode="
				+ fromCurrencyISOCode + ", toCurrencyISOCode=" + toCurrencyISOCode + ", dealTimestamp=" + dealTimestamp
				+ ", dealAmount=" + dealAmount + "]";
	}	
	
	
}
