package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        customerRepository.deleteAll();
    }

    //Create Customer Test
    @Test
    public void shouldAddCustomer() {

        Customer customer = new Customer();

//        customer.customerId;
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@gmail.com");
        customer.setCompany("Acme");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 100");
        customer.setCity("Any town");
        customer.setState("PA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");

        customerRepository.save(customer);

       Optional<Customer> foundCustomer = customerRepository.findById(customer.getCustomerId());
       assertEquals(customer, foundCustomer.orElse(null));
    }

    //Update Customer Test
    @Test
    public void shouldUpdateCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@gmail.com");
        customer.setCompany("Acme");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 100");
        customer.setCity("Any town");
        customer.setState("PA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");

        customerRepository.save(customer);

//       Update Customer First name and Email
        customer.setFirstName("Jane");
        customer.setEmail("kmc@gmail.com");


        customerRepository.save(customer);

        Optional<Customer> updatedCustomer = customerRepository.findById(customer.getCustomerId());
        assertEquals(customer, updatedCustomer.orElse(null));
    }

    //Find Customer By ID
    @Test
    public void shouldGetCustomerById() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("kmc@gmail.com");
        customer.setCompany("Acme");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 100");
        customer.setCity("Any town");
        customer.setState("PA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");

        customerRepository.save(customer);

        Optional<Customer> customerFoundById = customerRepository.findById(customer.getCustomerId());
        assertEquals(customer, customerFoundById.orElse(null));
    }

    //Find Customer by State
    @Test
    public void shouldGetCustomerByState() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("kmc@gmail.com");
        customer.setCompany("Acme");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 100");
        customer.setCity("Any town");
        customer.setState("PA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");

        customerRepository.save(customer);

        Optional<Customer> customerFoundByState = customerRepository.findById(customer.getCustomerId());
        assertEquals(customer, customerFoundByState.orElse(null));
    }

    //Should delete customer by Id
    @Test
    public void shouldDeleteCustomerById() {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("kmc@gmail.com");
        customer.setCompany("Acme");
        customer.setPhone("123-456-7890");
        customer.setAddress1("123 Main St");
        customer.setAddress2("Suite 100");
        customer.setCity("Any town");
        customer.setState("PA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");

        customerRepository.save(customer);

        customerRepository.deleteById(customer.getCustomerId());

Optional<Customer> deletedById = customerRepository.findById(customer.getCustomerId());
        assertFalse(deletedById.isPresent());
    }
}
