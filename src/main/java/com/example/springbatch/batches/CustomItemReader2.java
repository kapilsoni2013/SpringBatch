package com.example.springbatch.batches;

import com.example.springbatch.Claim;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;

import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CustomItemReader2 implements ItemReader<Claim> {
    private final Iterator<Claim> claimIterator;
    private final BufferedReader reader;

    public CustomItemReader2(String filePath){
        try {
            System.out.println("CustomItemReader2 Construct");
            ColumnPositionMappingStrategy<Claim> ms = new ColumnPositionMappingStrategy<Claim>();
            ms.setType(Claim.class);

            reader = Files.newBufferedReader(Paths.get(filePath));
            CsvToBean<Claim> claimCsvToBean = new CsvToBeanBuilder<Claim>(reader)
                    .withType(Claim.class)
                    .withSkipLines(1)
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
        System.out.println(aClaim);
        return aClaim;
    }

    @PreDestroy
    public void close() throws IOException {
        System.out.println("@PreDestroy= Reader2 Closed");
        reader.close();
    }

}