package com.example.springbatch;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;


public class Claim {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "claimId", required = true)
    private Integer claimId;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "customerId", required = true)
    private String customerId;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "claimAmount", required = true)
    private BigDecimal claimAmount;
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "claimSubmissionDate", required = true)
    private String claimSubmissionDate;
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "admissionDate", required = true)
    private String admissionDate;
    @CsvBindByPosition(position =5)
    @CsvBindByName(column = "dischargeDate", required = true)
    private String dischargeDate;
    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "policyReferenceId", required = true)
    private String policyReferenceId;
    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "beneficiaryName", required = true)
    private String beneficiaryName;
    @CsvBindByPosition(position = 8)
    @CsvBindByName(column = "beneficiaryEmail", required = true)
    private String beneficiaryEmail;
    @CsvBindByPosition(position = 9)
    @CsvBindByName(column = "beneficiaryMobile", required = true)
    private String beneficiaryMobile;
    @CsvBindByPosition(position = 10)
    @CsvBindByName(column = "policyStartDate", required = true)
    private String policyStartDate;
    @CsvBindByPosition(position = 11)
    @CsvBindByName(column = "policyEndDate", required = true)
    private String policyEndDate;

    public String getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(String policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public String getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(String policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    private Boolean isValidData;

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getClaimSubmissionDate() {
        return claimSubmissionDate;
    }

    public void setClaimSubmissionDate(String claimSubmissionDate) {
        this.claimSubmissionDate = claimSubmissionDate;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getPolicyReferenceId() {
        return policyReferenceId;
    }

    public void setPolicyReferenceId(String policyReferenceId) {
        this.policyReferenceId = policyReferenceId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryEmail() {
        return beneficiaryEmail;
    }

    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
    }

    public String getBeneficiaryMobile() {
        return beneficiaryMobile;
    }

    public void setBeneficiaryMobile(String beneficiaryMobile) {
        this.beneficiaryMobile = beneficiaryMobile;
    }

    public Boolean getIsValidData() {
        return isValidData;
    }

    public void setIsValidData(Boolean isValidData) {
        this.isValidData = isValidData;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", customerId='" + customerId + '\'' +
                ", claimAmount=" + claimAmount +
                ", claimSubmissionDate='" + claimSubmissionDate + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", dischargeDate='" + dischargeDate + '\'' +
                ", policyReferenceId='" + policyReferenceId + '\'' +
                ", beneficiaryName='" + beneficiaryName + '\'' +
                ", beneficiaryEmail='" + beneficiaryEmail + '\'' +
                ", beneficiaryMobile='" + beneficiaryMobile + '\'' +
                ", policyStartDate='" + policyStartDate + '\'' +
                ", policyEndDate='" + policyEndDate + '\'' +
                ", decider=" + isValidData +
                '}';
    }
}