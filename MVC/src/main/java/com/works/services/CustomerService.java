package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest req;

    public Customer saveCustomer(Customer customer){
        String cipherPassword = tinkEncDec.encrypt(customer.getPassword());
        customer.setPassword(cipherPassword);
        customerRepository.save(customer);
        return customer;
    }

    public Customer login(String email, String password){
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(email);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            String dbPassword = tinkEncDec.decrypt(customer.getPassword());
            if (dbPassword.equals(password)){
                customer.setPassword(null);
                req.getSession().setAttribute("customer", customer);
                return customer;
            }
        }
        return null;
    }


}
