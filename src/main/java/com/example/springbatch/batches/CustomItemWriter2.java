package com.example.springbatch.batches;

import com.example.springbatch.Claim;
import com.opencsv.CSVWriter;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CustomItemWriter2 implements ItemWriter<Claim> {

    private final String trueOutputFilePath;
    private final String falseOutputFilePath;

    public CustomItemWriter2(String trueOutputFilePath, String falseOutputFilePath) {
       this.trueOutputFilePath=trueOutputFilePath;
       this.falseOutputFilePath=falseOutputFilePath;
    }

    @Override
    public void write(List<? extends Claim> data) throws Exception {
        System.out.println("Writer2 Count "+data.size());
        CSVWriter trueCSVWriter=new CSVWriter(Files.newBufferedWriter(Paths.get(trueOutputFilePath)));
        CSVWriter falseCSVWriter=new CSVWriter(Files.newBufferedWriter(Paths.get(falseOutputFilePath)));


        List<? extends Claim> trueList = data.stream()
                .filter(Claim::getIsValidData)
                .collect(Collectors.toList());

        List<? extends Claim> falseList = data.stream()
                .filter(e->!e.getIsValidData())
                .collect(Collectors.toList());


        System.out.println(trueList);
        System.out.println(falseList);

      trueCSVWriter.close();
       falseCSVWriter.close();

    }

    @PreDestroy
    private void close() throws IOException {
        System.out.println("@PreDestroy= Writer2 Closed");
//        this.trueCSVWriter.close();
//        this.falseCSVWriter.close();
    }

}