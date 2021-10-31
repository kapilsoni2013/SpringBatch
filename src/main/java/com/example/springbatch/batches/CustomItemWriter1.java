package com.example.springbatch.batches;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springbatch.Claim;
import com.example.springbatch.CustomMappingStrategy;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.PreDestroy;

public class CustomItemWriter1 implements ItemWriter<Claim> {

    private final StatefulBeanToCsv<Claim> validCSVWriter;
    private final StatefulBeanToCsv<Claim> invalidCSVWriter;
    private final BufferedWriter validRWriter;
    private final BufferedWriter invalidWriter;

    public CustomItemWriter1(String validDataClaimPath, String invalidDataClaimPath) {
        try {
            CustomMappingStrategy<Claim> mappingStrategy = new CustomMappingStrategy<Claim>();
            mappingStrategy.setType(Claim.class);


            validRWriter = Files.newBufferedWriter(Paths.get(validDataClaimPath));
            StatefulBeanToCsvBuilder<Claim> builder =
                    new StatefulBeanToCsvBuilder<Claim>(validRWriter);
            validCSVWriter = builder
                    .withMappingStrategy(mappingStrategy)
                    .build();


            invalidWriter = Files.newBufferedWriter(Paths.get(invalidDataClaimPath));
            StatefulBeanToCsvBuilder<Claim> builder1 =
                    new StatefulBeanToCsvBuilder<Claim>(invalidWriter);
            invalidCSVWriter = builder1
                    .withMappingStrategy(mappingStrategy)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(List<? extends Claim> data) throws Exception {
        System.out.println("Writer1 Count "+data.size());
        List<Claim> validList = data.stream()
                .filter(Claim::getIsValidData)
                .collect(Collectors.toList());

        List<Claim> invalidList = data.stream()
                .filter(e->!e.getIsValidData())
                .collect(Collectors.toList());

        validCSVWriter.write(validList);
        invalidCSVWriter.write(invalidList);

        System.out.println("validList"+validList.size());
        System.out.println("invalidList"+invalidList.size());
    }

    @PreDestroy
    private void close() throws IOException {
        System.out.println("@PreDestroy= Writer1 Closed");
        this.validRWriter.close();
        this.invalidWriter.close();
    }

}