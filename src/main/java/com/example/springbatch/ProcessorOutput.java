package com.example.springbatch;

public class ProcessorOutput {
    private Claim claim;
    private Boolean decider;

    public ProcessorOutput(Claim claim) {
        this.claim = claim;
    }

    public Claim getEmployee() {
        return claim;
    }

    public void setEmployee(Claim claim) {
        this.claim = claim;
    }

    public Boolean getDecider() {
        return decider;
    }

    public void setDecider(Boolean decider) {
        this.decider = decider;
    }

    @Override
    public String toString() {
        return "ProcessorOutput{" +
                "employee=" + claim +
                ", decider=" + decider +
                '}';
    }
}
