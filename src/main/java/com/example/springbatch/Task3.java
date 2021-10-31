package com.example.springbatch;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.StatefulBeanToCsv;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.example.springbatch.CSVUtils.*;
@Component
public class Task3 implements Tasklet {
    @Value("${input.file.valid-output-final}")
    private String validDataClaimFinalPath;

    @Value("${input.file.valid-output-most-final}")
    private String mostFinal;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("My-Step2");
        // Reading CSV File
        BufferedReader reader = Files.newBufferedReader(Paths.get(validDataClaimFinalPath));
        CsvToBean<Claim> claimCsvToBean = readCSVByPath(reader);
        Iterator<Claim> iterator = claimCsvToBean.iterator();

        List<Claim> finalList=new ArrayList<>();

        while(iterator.hasNext()){
            Claim aClaim = iterator.next();
            if(aClaim.getClaimId()%2==0){
                aClaim.setMaxEligibleAmt("10");
            }else{
                aClaim.setMaxEligibleAmt("15");
            }
            finalList.add(aClaim);
        }
        reader.close();

        //Writing CSV File
        BufferedWriter validWriter = Files.newBufferedWriter(Paths.get(mostFinal));
        StatefulBeanToCsv<Claim> validBuildBeanToCsv = getStatefulBeanToCsv(getClaimCustomMappingStrategy(), validWriter);

        validBuildBeanToCsv.write(finalList);
        validWriter.close();

        return RepeatStatus.FINISHED;
    }
}
