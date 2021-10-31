package com.example.springbatch;

import com.opencsv.bean.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class CSVUtils {
    public static StatefulBeanToCsv<Claim> getStatefulBeanToCsv(CustomMappingStrategy<Claim> mappingStrategy, BufferedWriter validWriter) {
        return new StatefulBeanToCsvBuilder<Claim>(validWriter)
                .withMappingStrategy(mappingStrategy)
                .build();
    }

    public static CsvToBean<Claim> readCSVByPath(BufferedReader reader) throws IOException {
        ColumnPositionMappingStrategy<Claim> ms = getClaimCustomMappingStrategy();
        return new CsvToBeanBuilder<Claim>(reader)
                .withType(Claim.class)
                .withMappingStrategy(ms)
                .withSkipLines(1)
                .build();
    }
    public static  CustomMappingStrategy<Claim> getClaimCustomMappingStrategy() {
        CustomMappingStrategy<Claim> mappingStrategy = new CustomMappingStrategy<Claim>();
        mappingStrategy.setType(Claim.class);
        return mappingStrategy;
    }
}
