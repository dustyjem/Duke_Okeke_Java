package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();

        for (String[] data : customerData) {
            String customerId = data[0];
            String companyName = data[1];
            String paidCharges = data[2];
            String chargeDate = data[3];

            Customer customer = null;

            int id = Integer.parseInt(customerId);
            int charge = Integer.parseInt(paidCharges);

            for (Customer existingCustomer : customers) {
                if (existingCustomer.getId() == id) {
                    customer = existingCustomer;
                    break;
                }
            }

            if (customer == null) {
                customer = new Customer();
                customer.setId(id);
                customer.setName(companyName);
                customers.add(customer);
            }

            AccountRecord accountRecord = new AccountRecord(paidCharges, chargeDate);
            customer.getCharges().add(accountRecord);
        }

        System.out.println();

        for (Customer customer : customers) {
            System.out.println("This is a list of all customers");
            System.out.println(customer.toString());
        }

        System.out.println();

        System.out.println("Positive accounts:");
        for (Customer customer : customers) {
            if (customer.getBalance() >= 0) {
                System.out.println(customer.toString());
            }
        }

        System.out.println();

        System.out.println("Negative accounts:");
        for (Customer customer : customers) {
            if (customer.getBalance() < 0) {
                System.out.println(customer.toString());
            }
        }
    }
}
