package com.resort.kingfisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Login;

import com.resort.kingfisher.repo.CustomerRepository;
import com.resort.kingfisher.repo.LoginRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private LoginRepository loginRepo;

    public Customer saveCustomer(Customer customer) {
        Login login = new Login();
        login.setUsername(customer.getUsername());
        login.setPassword(customer.getPassword());
        login.setRole("CUSTOMER");
        customer.setRole(login.getRole());
        Customer savedCustomer = customerRepository.save(customer);
        login.setCustomer(savedCustomer);
        loginRepo.save(login);
        return savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        if (customerRepository.existsById(id)) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + id + " does not exist.");
        }
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Customer with ID " + id + " does not exist.");
        }
    }
    public Customer findCustomerByUsername(String username) {
        // Query the database to find a customer by username
        return customerRepository.findByUsername(username);
    }
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email); // Use your repository method to find by email
    }



   
	
    
}
