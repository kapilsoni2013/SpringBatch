package com.example.springbatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.PreDestroy;

public class CustomItemWriter implements ItemWriter<Employee> {

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
    public void write(List<? extends Employee> data) throws Exception {
        List<? extends Employee> trueList = data.stream()
                .filter(Employee::getDecider)
                .collect(Collectors.toList());
        List<? extends Employee> falseList = data.stream()
                .filter(e->!e.getDecider())
                .collect(Collectors.toList());


        trueCSVWriter.writeAll(trueList.stream()
                .map(e->new String[]{e.getId(),e.getFirstName(),e.getLastName()})
                .collect(Collectors.toList()));
        falseCSVWriter.writeAll(falseList.stream()
                .map(e->new String[]{e.getId(),e.getFirstName(),e.getLastName()})
                .collect(Collectors.toList()));

    }

    @PreDestroy
    private void close() throws IOException {
        this.trueCSVWriter.close();
        this.falseCSVWriter.close();
    }

}