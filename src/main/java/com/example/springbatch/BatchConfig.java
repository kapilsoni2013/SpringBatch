package com.example.springbatch;

import com.opencsv.bean.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Task1 task1;
    @Autowired
    private Task2 task2;
    @Autowired
    private Task3 task3;


    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("My-Step1")
                .tasklet(task1).build();
    }

    @Bean
    public Step step2() {
        return this.stepBuilderFactory.get("My-Step2")
                .tasklet(task2).build();
    }

    @Bean
    public Step step3() {
        return this.stepBuilderFactory.get("My-Step3")
                .tasklet(task3).build();
    }





}