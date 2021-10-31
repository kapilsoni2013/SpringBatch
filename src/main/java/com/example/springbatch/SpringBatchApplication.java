package com.example.springbatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class SpringBatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {

	}
}
