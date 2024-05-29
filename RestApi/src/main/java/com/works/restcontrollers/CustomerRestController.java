package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.models.Currency;
import com.works.services.CustomerService;
import com.works.xxe.XmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody Customer customer){
        return customerService.register(customer);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Customer customer){
        return customerService.login(customer);
    }

    @GetMapping("xml")
    public List<Currency> getCurrency(){
        XmlService xmlService = new XmlService();
        return xmlService.xml();
    }

}
