package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository repository;

    @Autowired
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Should be able to add a customer
     * @param customer
     */
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
        repository.save(customer);
    }

    /**
     * Should be able to update a customer
     * @param customer
     */
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        repository.save(customer);
    }

    /**
     * Should be able to delete a customer
     * @param customerId
     */
    @DeleteMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int customerId) {
        try {
            repository.deleteById(customerId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer with id " + customerId + " not found.");
        }
    }

    /**
     * Should be able to get a customer by id
     * @param customerId
     * @return
     */

    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable int customerId) {
        return repository.findById(customerId).orElse(null);
    }

    /**
     * Should be able to get all customers by state
     * @return
     */

    @GetMapping("/customers/state/{state}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return repository.findAllByState(state);
    }

}
