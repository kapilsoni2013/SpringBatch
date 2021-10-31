package com.example.springbatch.batches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

import com.example.springbatch.Claim;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.batch.item.ItemReader;

import javax.annotation.PreDestroy;

public class CustomItemReader1 implements ItemReader<Claim> {
    private final Iterator<Claim> claimIterator;
    private final BufferedReader reader;

    public CustomItemReader1(String filePath){
        try {

            ColumnPositionMappingStrategy<Claim> ms = new ColumnPositionMappingStrategy<Claim>();
            ms.setType(Claim.class);

            reader = Files.newBufferedReader(Paths.get(filePath));
            CsvToBean<Claim> claimCsvToBean = new CsvToBeanBuilder<Claim>(reader)
                    .withType(Claim.class)
                    .withMappingStrategy(ms)
                    .build();
            claimIterator=claimCsvToBean.iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Claim read() throws Exception {
        boolean isFound = claimIterator.hasNext();
        if(!isFound){
            return null;
        }

        Claim aClaim=claimIterator.next();

//        Claim aClaim = new Claim();
//        Integer claimId=Integer.valueOf(data[0]);
//        String customerId=data[1];
//        BigDecimal claimAmount=BigDecimal.valueOf(Long.valueOf(data[2]));
//        String claimSubmissionDate=data[3];
//        String admissionDate=data[4];
//        String dischargeDate=data[5];
//        String policyReferenceId=data[6];
//        String beneficiaryName=data[7];
//        String beneficiaryEmail=data[8];
//        String beneficiaryMobile=data[9];
//        String policyStartDate=data[10];
//        String policyEndDate=data[11];
//
//        aClaim.setClaimId(claimId);
//        aClaim.setCustomerId(customerId);
//        aClaim.setClaimAmount(claimAmount);
//        aClaim.setClaimSubmissionDate(claimSubmissionDate);
//        aClaim.setAdmissionDate(admissionDate);
//        aClaim.setDischargeDate(dischargeDate);
//        aClaim.setPolicyReferenceId(policyReferenceId);
//        aClaim.setBeneficiaryName(beneficiaryName);
//        aClaim.setBeneficiaryEmail(beneficiaryEmail);
//        aClaim.setBeneficiaryMobile(beneficiaryMobile);
//        aClaim.setPolicyStartDate(policyStartDate);
//        aClaim.setPolicyEndDate(policyEndDate);

        return aClaim;
    }

    @PreDestroy
    public void close() throws IOException {
        reader.close();
    }

}