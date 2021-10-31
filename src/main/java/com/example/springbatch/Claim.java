package com.example.springbatch;

import java.math.BigDecimal;


public class Claim {
    private Integer claimId;
    private String customerId;
    private BigDecimal claimAmount;
    private String claimSubmissionDate;
    private String admissionDate;
    private String dischargeDate;
    private String policyReferenceId;
    private String beneficiaryName;
    private String beneficiaryEmail;
    private String beneficiaryMobile;
    private String policyStartDate;
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

    private Boolean decider;

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

    public Boolean getDecider() {
        return decider;
    }

    public void setDecider(Boolean decider) {
        this.decider = decider;
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
                ", decider=" + decider +
                '}';
    }
}