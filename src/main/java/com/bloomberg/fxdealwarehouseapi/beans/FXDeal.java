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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dealTimestamp;
	
//	dealAmount in the fromCurrency be converted to the toCurrency.
	@Column(nullable = false)
	private String dealAmount;

	
	
	
	
	/**
	 * @param sequentialId
	 * @param dealId
	 * @param fromCurrencyISOCode
	 * @param toCurrencyISOCode
	 * @param dealTimestamp
	 * @param dealAmount
	 * 
	 * A parameterized constructor to build a bean of the FXDeal class by providing 
	 * its details as arguments to the constructor.
	 */
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

	
	/**
	 * @return sequentialId
	 * A getter for the sequentialId field.
	 */
	public int getSequentialId() {
		return sequentialId;
	}


	/**
	 * @param sequentialId
	 *  A setter for the sequentialId field.
	 */
	public void setSequentialId(int sequentialId) {
		this.sequentialId = sequentialId;
	}

	
	/**
	 * @return dealId
	 * A getter for the dealId field.
	 */
	public String getDealId() {
		return dealId;
	}

	
	
	/**
	 * @param dealId
	 * A setter for the dealId field.
	 */
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	
	
	/**
	 * @return fromCurrencyISOCode
	 * A getter for the FromCurrencyISOCode field.
	 */
	public String getFromCurrencyISOCode() {
		return fromCurrencyISOCode;
	}

	
	/**
	 * @param fromCurrencyISOCode
	 * A setter for the fromCurrencyISOCode field.
	 */
	public void setFromCurrencyISOCode(String fromCurrencyISOCode) {
		this.fromCurrencyISOCode = fromCurrencyISOCode;
	}

	
	
	/**
	 * @return toCurrencyISOCode
	 * A getter for the toCurrencyISOCode field.
	 */
	public String getToCurrencyISOCode() {
		return toCurrencyISOCode;
	}

	
	/**
	 * @param toCurrencyISOCode
	 * A setter for the toCurrencyISOCode field.
	 */
	public void setToCurrencyISOCode(String toCurrencyISOCode) {
		this.toCurrencyISOCode = toCurrencyISOCode;
	}

	
	
	/**
	 * @return dealTimestamp
	 * A getter for the dealTimestamp field.
	 */
	public LocalDateTime getDealTimestamp() {
		return dealTimestamp;
	}

	
	/**
	 * @param dealTimestamp
	 * A setter for the dealTimestamp field.
	 */
	public void setDealTimestamp(LocalDateTime dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	
	
	/**
	 * @return dealAmount
	 * A getter for the dealAmount field.
	 */
	public String getDealAmount() {
		return dealAmount;
	}

	
	/**
	 * @param dealAmount
	 * A setter for the dealAmount field.
	 */
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	
	
	/**
	 * Overriding of the toString Method that is provided by the Object class
	 * , to be used in logging when needed, to print the details of a 
	 * bean instance of the FXDeal class type.
	 */
	@Override
	public String toString() {
		return "FXDeal [sequentialId=" + sequentialId + ", dealId=" + dealId + ", fromCurrencyISOCode="
				+ fromCurrencyISOCode + ", toCurrencyISOCode=" + toCurrencyISOCode + ", dealTimestamp=" + dealTimestamp
				+ ", dealAmount=" + dealAmount + "]";
	}	
	
	
}
