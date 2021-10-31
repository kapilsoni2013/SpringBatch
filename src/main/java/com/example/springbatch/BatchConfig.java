package com.example.springbatch;

import com.example.springbatch.batches.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;

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

    @Value("${input.file.valid-output}")
    private String validDataClaimPath;

    @Value("${input.file.invalid-output}")
    private String invalidDataClaimPath;

    @Value("${input.file.valid-output-final}")
    private String validDataClaimFinalPath;

    @Value("${input.file.invalid-output-final}")
    private String invalidDataClaimFinalPath;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                    .<Claim, Claim>chunk(100)
                .reader(reader1())
                .processor(processor1())
                .writer(writer1())
                .writer(writer2())
                .build();
    }

   /* @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Claim, Claim>chunk(100)
                .reader(reader2())
                .processor(processor2())
                .writer(writer2())
                .build();
    }*/

    @Bean
    @Lazy
    public FlatFileItemReader<Claim> reader1() {
        FlatFileItemReader<Claim> reader = new FlatFileItemReader<Claim>();

        //Set input file location
        reader.setResource(new FileSystemResource(inputFilePath));

        //Set number of lines to skips. Use it if file has header rows.
        reader.setLinesToSkip(1);

        //Configure how each line will be parsed and mapped to different values
        reader.setLineMapper(new DefaultLineMapper() {
            {
                //3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "id", "firstName", "lastName" });
                    }
                });
                //Set values in Employee class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Claim>() {
                    {
                        setTargetType(Claim.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    public ItemProcessor<Claim, Claim> processor1(){
        return new CustomItemProcessor1();
    }

    @Bean
    public FlatFileItemWriter<Claim> writer1()
    {
        //Create writer instance
        FlatFileItemWriter<Claim> writer = new FlatFileItemWriter<>();

        //Set output file location
        writer.setResource(new FileSystemResource(validDataClaimPath));

        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);

        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<Claim>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Claim>() {
                    {
                        setNames(new String[] { "claimId","customerId","claimAmount","claimSubmissionDate","admissionDate","dischargeDate","policyReferenceId","beneficiaryName","beneficiaryEmail","beneficiaryMobile","policyStartDate","policyEndDate" });
                    }
                });
            }
        });
        return writer;
    }

    @Bean
    public FlatFileItemWriter<Claim> writer2()
    {
        //Create writer instance
        FlatFileItemWriter<Claim> writer = new FlatFileItemWriter<>();

        //Set output file location
        writer.setResource(new FileSystemResource(invalidDataClaimPath));

        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);

        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<Claim>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Claim>() {
                    {
                        setNames(new String[] { "claimId","customerId","claimAmount","claimSubmissionDate","admissionDate","dischargeDate","policyReferenceId","beneficiaryName","beneficiaryEmail","beneficiaryMobile","policyStartDate","policyEndDate" });
                    }
                });
            }
        });
        return writer;
    }
}