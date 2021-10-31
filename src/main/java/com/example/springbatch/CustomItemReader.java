package com.example.springbatch;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.batch.item.ItemReader;

public class CustomItemReader implements ItemReader<Claim> {
    private final CSVReader csvReader;
    public CustomItemReader(String filePath){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Claim read() throws Exception {
        String[] data=csvReader.readNext();
        if(data==null){
            return null;
        }
        Claim aClaim = new Claim();
        Integer claimId=Integer.valueOf(data[0]);
        String customerId=data[1];
        BigDecimal claimAmount=BigDecimal.valueOf(Long.valueOf(data[2]));
        String claimSubmissionDate=data[3];
        String admissionDate=data[4];
        String dischargeDate=data[5];
        String policyReferenceId=data[6];
        String beneficiaryName=data[7];
        String beneficiaryEmail=data[8];
        String beneficiaryMobile=data[9];
        String policyStartDate=data[10];
        String policyEndDate=data[11];

        aClaim.setClaimId(claimId);
        aClaim.setCustomerId(customerId);
        aClaim.setClaimAmount(claimAmount);
        aClaim.setClaimSubmissionDate(claimSubmissionDate);
        aClaim.setAdmissionDate(admissionDate);
        aClaim.setDischargeDate(dischargeDate);
        aClaim.setPolicyReferenceId(policyReferenceId);
        aClaim.setBeneficiaryName(beneficiaryName);
        aClaim.setBeneficiaryEmail(beneficiaryEmail);
        aClaim.setBeneficiaryMobile(beneficiaryMobile);
        aClaim.setPolicyStartDate(policyStartDate);
        aClaim.setPolicyEndDate(policyEndDate);

        return aClaim;
    }

}