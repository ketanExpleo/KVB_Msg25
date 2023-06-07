package com.fss.card.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TRANSACTION_HISTORY")
public class TransactionHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="MSG_TYPE_INDICATOR")
	private String mti;
	
	@Column(name="CARD_NO")
	private String cardNo;
	
	@Column(name="PROCESSING_CODE")
	private String processingCode;
	
	@Column(name="TRANSACTION_AMOUNT")
	private String transactionAmount;

	@Column(name="TRANSACTION_DATE_TIME")
	private String transactionDateTime;
	
	@Column(name="STAN")
	private String stan;
	
	@Column(name="LOCAL_TRANSACTION_TIME")
	private String localTransactionTime;
	
	@Column(name="LOCAL_TRANSACTION_DATE")
	private String localTransactionDate;

	@Column(name="EXPIRATION_DATE")
	private String expirationDate;
	
	@Column(name="BUSINESS_DATE")
	private String businessDate;
	
	@Column(name="MERCHANT_CAT_CODE")
	private String merchantCatCode;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@Column(name="POS_ENTRY_MODE")
	private String pointOfServiceEntryMode;
	
	@Column(name="APP_PAN_SEQ_NO")
	private String appPanSeqNo;
	
	@Column(name="POS_CONDITION_CODE")
	private String pointOfServiceConditionCode;
	
	@Column(name="ACQ_INS_IDENTIFICATION_CODE")
	private String acquiringInstitutionIdentificationCode;

	@Column(name="TRACK2_DATA")
	private String track2Data;
	
	@Column(name="RETRIEVAL_REF_NO")
	private String retrievalRefNo;
	
	@Column(name="SERVICE_RESTRICTION_CODE")
	private String serviceRestrictionCode;
	
	@Column(name="TERMINAL_NAME")
	private String terminalName;
	
	@Column(name="TERMINAL_CODE")
	private String terminalCode;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="ADDITIONAL_DATA")
	private String additionalData;
	
	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	@Column(name="PRSNL_IDENTIFICATION_NO_DATA")
	private String personalIdentificationNoData;
	
	@Column(name="EMV_DATA")
	private String emvChipData;
	
	@Column(name="PRIVATE1")
	private String private1;
	
	@Column(name="PRIVATE2")
	private String private2;
	
	@Column(name="RES_MTI")
	private String resMTI;
	
	@Column(name="AUTH_IDENTIFICATION_RES")
	private String authIdentificationResponse;
	
	@Column(name="RESPONSE_CODE")
	private String responseCode;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CREATED_ON")
	private java.sql.Timestamp createdOn;
	
	@Column(name="MODIFIED_ON")
	private java.sql.Timestamp modifiedOn;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	@Column(name="DELETE_FLAG")
	private String deleteFlag;

	public TransactionHistory() {
		super();
	}

	public TransactionHistory(Long id, String mti, String cardNo, String processingCode, String transactionAmount,
			String transactionDateTime, String stan, String localTransactionTime, String localTransactionDate,
			String expirationDate, String businessDate, String merchantCatCode, String countryCode,
			String pointOfServiceEntryMode, String appPanSeqNo, String pointOfServiceConditionCode,
			String acquiringInstitutionIdentificationCode, String track2Data, String retrievalRefNo,
			String serviceRestrictionCode, String terminalName, String terminalCode, String location,
			String additionalData, String currencyCode, String personalIdentificationNoData, String emvChipData,
			String private1, String private2, String resMTI, String authIdentificationResponse, String responseCode,
			String status, Timestamp createdOn, Timestamp modifiedOn, String createdBy, String modifiedBy,
			String deleteFlag) {
		super();
		this.id = id;
		this.mti = mti;
		this.cardNo = cardNo;
		this.processingCode = processingCode;
		this.transactionAmount = transactionAmount;
		this.transactionDateTime = transactionDateTime;
		this.stan = stan;
		this.localTransactionTime = localTransactionTime;
		this.localTransactionDate = localTransactionDate;
		this.expirationDate = expirationDate;
		this.businessDate = businessDate;
		this.merchantCatCode = merchantCatCode;
		this.countryCode = countryCode;
		this.pointOfServiceEntryMode = pointOfServiceEntryMode;
		this.appPanSeqNo = appPanSeqNo;
		this.pointOfServiceConditionCode = pointOfServiceConditionCode;
		this.acquiringInstitutionIdentificationCode = acquiringInstitutionIdentificationCode;
		this.track2Data = track2Data;
		this.retrievalRefNo = retrievalRefNo;
		this.serviceRestrictionCode = serviceRestrictionCode;
		this.terminalName = terminalName;
		this.terminalCode = terminalCode;
		this.location = location;
		this.additionalData = additionalData;
		this.currencyCode = currencyCode;
		this.personalIdentificationNoData = personalIdentificationNoData;
		this.emvChipData = emvChipData;
		this.private1 = private1;
		this.private2 = private2;
		this.resMTI = resMTI;
		this.authIdentificationResponse = authIdentificationResponse;
		this.responseCode = responseCode;
		this.status = status;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.deleteFlag = deleteFlag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMti() {
		return mti;
	}

	public void setMti(String mti) {
		this.mti = mti;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getLocalTransactionTime() {
		return localTransactionTime;
	}

	public void setLocalTransactionTime(String localTransactionTime) {
		this.localTransactionTime = localTransactionTime;
	}

	public String getLocalTransactionDate() {
		return localTransactionDate;
	}

	public void setLocalTransactionDate(String localTransactionDate) {
		this.localTransactionDate = localTransactionDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getMerchantCatCode() {
		return merchantCatCode;
	}

	public void setMerchantCatCode(String merchantCatCode) {
		this.merchantCatCode = merchantCatCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPointOfServiceEntryMode() {
		return pointOfServiceEntryMode;
	}

	public void setPointOfServiceEntryMode(String pointOfServiceEntryMode) {
		this.pointOfServiceEntryMode = pointOfServiceEntryMode;
	}

	public String getAppPanSeqNo() {
		return appPanSeqNo;
	}

	public void setAppPanSeqNo(String appPanSeqNo) {
		this.appPanSeqNo = appPanSeqNo;
	}

	public String getPointOfServiceConditionCode() {
		return pointOfServiceConditionCode;
	}

	public void setPointOfServiceConditionCode(String pointOfServiceConditionCode) {
		this.pointOfServiceConditionCode = pointOfServiceConditionCode;
	}

	public String getAcquiringInstitutionIdentificationCode() {
		return acquiringInstitutionIdentificationCode;
	}

	public void setAcquiringInstitutionIdentificationCode(String acquiringInstitutionIdentificationCode) {
		this.acquiringInstitutionIdentificationCode = acquiringInstitutionIdentificationCode;
	}

	public String getTrack2Data() {
		return track2Data;
	}

	public void setTrack2Data(String track2Data) {
		this.track2Data = track2Data;
	}

	public String getRetrievalRefNo() {
		return retrievalRefNo;
	}

	public void setRetrievalRefNo(String retrievalRefNo) {
		this.retrievalRefNo = retrievalRefNo;
	}

	public String getServiceRestrictionCode() {
		return serviceRestrictionCode;
	}

	public void setServiceRestrictionCode(String serviceRestrictionCode) {
		this.serviceRestrictionCode = serviceRestrictionCode;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPersonalIdentificationNoData() {
		return personalIdentificationNoData;
	}

	public void setPersonalIdentificationNoData(String personalIdentificationNoData) {
		this.personalIdentificationNoData = personalIdentificationNoData;
	}

	public String getEmvChipData() {
		return emvChipData;
	}

	public void setEmvChipData(String emvChipData) {
		this.emvChipData = emvChipData;
	}

	public String getPrivate1() {
		return private1;
	}

	public void setPrivate1(String private1) {
		this.private1 = private1;
	}

	public String getPrivate2() {
		return private2;
	}

	public void setPrivate2(String private2) {
		this.private2 = private2;
	}

	public String getResMTI() {
		return resMTI;
	}

	public void setResMTI(String resMTI) {
		this.resMTI = resMTI;
	}

	public String getAuthIdentificationResponse() {
		return authIdentificationResponse;
	}

	public void setAuthIdentificationResponse(String authIdentificationResponse) {
		this.authIdentificationResponse = authIdentificationResponse;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(java.sql.Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public java.sql.Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(java.sql.Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "TransactionHistory [id=" + id + ", mti=" + mti + ", cardNo=" + cardNo + ", processingCode="
				+ processingCode + ", transactionAmount=" + transactionAmount + ", transactionDateTime="
				+ transactionDateTime + ", stan=" + stan + ", localTransactionTime=" + localTransactionTime
				+ ", localTransactionDate=" + localTransactionDate + ", expirationDate=" + expirationDate
				+ ", businessDate=" + businessDate + ", merchantCatCode=" + merchantCatCode + ", countryCode="
				+ countryCode + ", pointOfServiceEntryMode=" + pointOfServiceEntryMode + ", appPanSeqNo=" + appPanSeqNo
				+ ", pointOfServiceConditionCode=" + pointOfServiceConditionCode
				+ ", acquiringInstitutionIdentificationCode=" + acquiringInstitutionIdentificationCode + ", track2Data="
				+ track2Data + ", retrievalRefNo=" + retrievalRefNo + ", serviceRestrictionCode="
				+ serviceRestrictionCode + ", terminalName=" + terminalName + ", terminalCode=" + terminalCode
				+ ", location=" + location + ", additionalData=" + additionalData + ", currencyCode=" + currencyCode
				+ ", personalIdentificationNoData=" + personalIdentificationNoData + ", emvChipData=" + emvChipData
				+ ", private1=" + private1 + ", private2=" + private2 + ", resMTI=" + resMTI
				+ ", authIdentificationResponse=" + authIdentificationResponse + ", responseCode=" + responseCode
				+ ", status=" + status + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", deleteFlag=" + deleteFlag + "]";
	}
}
