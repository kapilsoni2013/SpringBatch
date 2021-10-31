package com.example.springbatch;

public class ProcessorOutput {
    private Employee employee;
    private Boolean decider;

    public ProcessorOutput(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
                "employee=" + employee +
                ", decider=" + decider +
                '}';
    }
}
