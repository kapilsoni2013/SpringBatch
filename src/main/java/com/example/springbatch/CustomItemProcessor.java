package com.example.springbatch;

import org.springframework.batch.item.ItemProcessor;

import java.util.Random;

public class CustomItemProcessor implements ItemProcessor<Employee, Employee> {

    Random random=new Random(100);
    @Override
    public Employee process(Employee data) throws Exception {
        if(random.nextInt()%2==0){
            data.setDecider(true);
        }else {
            data.setDecider(false);
        }
        return data;
    }

}