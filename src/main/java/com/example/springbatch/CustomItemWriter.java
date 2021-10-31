package com.example.springbatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.PreDestroy;

public class CustomItemWriter implements ItemWriter<Claim> {

    private final CSVWriter trueCSVWriter;
    private final CSVWriter falseCSVWriter;

    public CustomItemWriter(String trueOutputFilePath,String falseOutputFilePath) {
        try {
            trueCSVWriter=new CSVWriter(Files.newBufferedWriter(Paths.get(trueOutputFilePath)));
            falseCSVWriter=new CSVWriter(Files.newBufferedWriter(Paths.get(falseOutputFilePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(List<? extends Claim> data) throws Exception {
        List<? extends Claim> trueList = data.stream()
                .filter(Claim::getDecider)
                .collect(Collectors.toList());

        List<? extends Claim> falseList = data.stream()
                .filter(e->!e.getDecider())
                .collect(Collectors.toList());


        System.out.println(trueList);
        System.out.println(falseList);

    }

    @PreDestroy
    private void close() throws IOException {
        this.trueCSVWriter.close();
        this.falseCSVWriter.close();
    }

}