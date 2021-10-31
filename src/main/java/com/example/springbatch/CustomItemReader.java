package com.example.springbatch;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import org.springframework.batch.item.ItemReader;

public class CustomItemReader implements ItemReader<Employee> {
    private final CSVReader csvReader;
    public CustomItemReader(String filePath){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            csvReader = new CSVReader(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Employee read() throws Exception {
        String[] data=csvReader.readNext();
        if(data==null){
            return null;
        }
        String id=data[0];
        String firstName=data[1];
        String lastName=data[2];
        return new Employee(id,firstName,lastName);
    }

}