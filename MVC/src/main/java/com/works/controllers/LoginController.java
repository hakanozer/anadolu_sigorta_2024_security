package com.works.controllers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final CustomerService customerService;

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam String email, @RequestParam String password){
        Customer customer = customerService.login(email, password);
        if(customer == null){
            System.out.println("email or password is incorrect");
            return "login";
        }
        return "redirect:/dashboard";
    }

}
