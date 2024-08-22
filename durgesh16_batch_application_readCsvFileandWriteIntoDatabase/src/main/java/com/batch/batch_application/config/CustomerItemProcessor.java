package com.batch.batch_application.config;

import com.batch.batch_application.model.Customer;
import org.springframework.batch.item.ItemProcessor;


//Implements Spring Batch's ItemProcessor interface.
//Defines a process method where business logic can be applied to each Customer item before writing to the database.
//In the provided code, it simply logs the Customer object
public class CustomerItemProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer item) throws Exception {
        System.out.println(item);
        return item;
    }
}
