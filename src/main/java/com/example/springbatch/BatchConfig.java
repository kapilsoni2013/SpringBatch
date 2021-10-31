package com.example.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig 
{
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
     
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${input.file.input}")
    private String inputFilePath;

    @Value("${input.file.true-output}")
    private String trueOutputFilePath;

    @Value("${input.file.false-output}")
    private String falseOutputFilePath;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .start(step2())
                .build();
    }
 
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                    .<Claim, Claim>chunk(1)
                .reader(reader())
                .processor(processor1())
                .writer(writer())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step1")
                .<Claim, Claim>chunk(1)
                .reader(reader())
                .processor(processor2())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<Claim> reader() {
        return new CustomItemReader(inputFilePath);
    }

    @Bean
    public ItemProcessor<Claim, Claim> processor1(){
        return new CustomItemProcessor1();
    }

    @Bean
    public ItemProcessor<Claim, Claim> processor2(){
        return new CustomItemProcessor1();
    }
     
    @Bean
    public ItemWriter<Claim> writer(){
        return new CustomItemWriter(trueOutputFilePath,falseOutputFilePath);
    }
}