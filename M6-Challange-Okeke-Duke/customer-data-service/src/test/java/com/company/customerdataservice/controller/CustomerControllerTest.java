package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    //Create Customer Test
    @Test
    public void shouldAddCustomer() throws Exception {
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

        String customerString = mapper.writeValueAsString(customer);

        doReturn(customer).when(repository).save(customer);

        mockMvc.perform(post("/customers")
                .content(customerString)
                .contentType("application/json"))
                .andExpect(status().isCreated());

    }

    //Update Customer Test
    @Test
    public void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("kmc@gmail.com");
        customer.setCompany("Acme");
        customer.setPhone("123-456-7890");
        customer.setAddress1("419 Agada Street");
        customer.setAddress2("Suite 100");
        customer.setCity("Any town");
        customer.setState("PA");
        customer.setPostalCode("12345");
        customer.setCountry("USA");

        String customerString = mapper.writeValueAsString(customer);

        doReturn(customer).when(repository).save(customer);

        mockMvc.perform(put("/customers")
                        .content(customerString)
                        .contentType("application/json"))
                .andExpect(status().isNoContent());
    }

    //Delete Customer Test
    @Test
    public void shouldDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());
    }

    //Get Customer Test
    @Test
public void shouldGetCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Emmanuel");
        customer.setLastName("Jerico");
        customer.setEmail("emajerc@gmail.com");
        customer.setCompany("Bible Foundation");
        customer.setPhone("826-456-9234");
        customer.setAddress1("07 Ororo Street");
        customer.setAddress2("Suite Goat");
        customer.setCity("Baller Town");
        customer.setState("GA");
        customer.setPostalCode("85625");
        customer.setCountry("Nigeria");

        String customerString = mapper.writeValueAsString(customer);

        doReturn(customer).when(repository).save(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk());
    }

    // Get All Customers from a state Test
    @Test
    public void shouldGetAllCustomersFromState() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Emmanuel");
        customer.setLastName("Jerico");
        customer.setEmail("emajerc@gmail.com");
        customer.setCompany("Bible Foundation");
        customer.setPhone("826-456-9234");
        customer.setAddress1("07 Ororo Street");
        customer.setAddress2("Suite Goat");
        customer.setCity("Baller Town");
        customer.setState("GA");
        customer.setPostalCode("85625");
        customer.setCountry("Nigeria");

        List<Customer> customerList = List.of(customer);

        mockMvc.perform(get("/customers/state/GA"))
                .andExpect(status().isOk());

    }


}
