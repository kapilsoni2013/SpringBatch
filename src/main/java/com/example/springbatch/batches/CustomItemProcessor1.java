package com.example.springbatch.batches;

import com.example.springbatch.Claim;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CustomItemProcessor1 implements ItemProcessor<Claim, Claim> {

    private Random random=new Random(100);
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public Claim process(Claim data) throws Exception {
        try{
            LocalDate claimSubmissionDate=LocalDate.parse(data.getClaimSubmissionDate(), formatters);
            LocalDate policyStartDate=LocalDate.parse(data.getDischargeDate(), formatters);
            LocalDate policyEndDate=LocalDate.parse(data.getDischargeDate(), formatters);

            LocalDate admissionDate=LocalDate.parse(data.getAdmissionDate(), formatters);
            LocalDate dischargeDate=LocalDate.parse(data.getDischargeDate(), formatters);
            if(admissionDate.isBefore(dischargeDate)){
                data.setIsValidData(true);
            }else {
                data.setIsValidData(false);
            }
        }catch (Exception e){
            System.out.println(e.toString());
            data.setIsValidData(false);
        }
        return data;
    }

}