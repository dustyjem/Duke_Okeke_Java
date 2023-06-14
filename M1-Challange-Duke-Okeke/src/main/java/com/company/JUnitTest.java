package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitTest {

    Customer customer = new Customer();


    @Test
    public void testGetBalance() {
        // Create a customer object
        Customer customer = new Customer();

        // Add account records to the customer's charges
        //This is to check of the AccountRecord function is doing addition
        AccountRecord record1 = new AccountRecord("100", "01-01-2022");
        AccountRecord record2 = new AccountRecord("-50", "01-02-2022");
        AccountRecord record3 = new AccountRecord("-50", "01-02-2022");

        customer.getCharges().add(record1);
        customer.getCharges().add(record2);
        customer.getCharges().add(record3);

        // Calculate the expected balance
        int expectedBalance = 0;

        // Verify that the getBalance() method returns the expected balance
        assertEquals(expectedBalance, customer.getBalance());
    }

}