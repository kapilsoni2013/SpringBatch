package com.example.springbatch;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.StatefulBeanToCsv;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.example.springbatch.CSVUtils.*;

@Component
public class Task2 implements Tasklet {

    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Value("${input.file.valid-output}")
    private String validDataClaimPath;

    @Value("${input.file.valid-output-final}")
    private String validDataClaimFinalPath;

    @Value("${input.file.invalid-output-final}")
    private String invalidDataClaimFinalPath;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("My-Step2");
        // Reading CSV File
        BufferedReader reader = Files.newBufferedReader(Paths.get(validDataClaimPath));
        CsvToBean<Claim> claimCsvToBean = readCSVByPath(reader);
        Iterator<Claim> iterator = claimCsvToBean.iterator();

        List<Claim> validClaimList=new ArrayList<>();
        List<Claim> invalidClaimList=new ArrayList<>();

        while(iterator.hasNext()){
            Claim aClaim = iterator.next();
            try{
                LocalDate claimSubmissionDate=LocalDate.parse(aClaim.getClaimSubmissionDate(), formatters);
                LocalDate policyStartDate=LocalDate.parse(aClaim.getPolicyStartDate(), formatters);
                LocalDate policyEndDate=LocalDate.parse(aClaim.getPolicyEndDate(), formatters);

                LocalDate admissionDate=LocalDate.parse(aClaim.getAdmissionDate(), formatters);
                LocalDate dischargeDate=LocalDate.parse(aClaim.getDischargeDate(), formatters);
                if(policyStartDate.isBefore(policyEndDate) && isNonFraudCheckUser(aClaim.getBeneficiaryEmail())){
                    aClaim.setIsValidData(true);
                    validClaimList.add(aClaim);
                }else {
                    aClaim.setIsValidData(false);
                    invalidClaimList.add(aClaim);
                }
            }catch (Exception e){
                System.out.println(e.toString());
                aClaim.setIsValidData(false);
                invalidClaimList.add(aClaim);
            }
        }
        reader.close();

        //Writing CSV File
        BufferedWriter validWriter = Files.newBufferedWriter(Paths.get(validDataClaimFinalPath));
        BufferedWriter inValidWriter = Files.newBufferedWriter(Paths.get(invalidDataClaimFinalPath));
        StatefulBeanToCsv<Claim> validBuildBeanToCsv = getStatefulBeanToCsv(getClaimCustomMappingStrategy(), validWriter);
        StatefulBeanToCsv<Claim> invalidBuildBeanToCsv = getStatefulBeanToCsv(getClaimCustomMappingStrategy(), inValidWriter);

        validBuildBeanToCsv.write(validClaimList);
        invalidBuildBeanToCsv.write(invalidClaimList);
        validWriter.close();
        inValidWriter.close();

        return RepeatStatus.FINISHED;
    }

    public static boolean isNonFraudCheckUser(String beneficiaryEmail) {
        return StringUtils.hasText(beneficiaryEmail);
    }
}
