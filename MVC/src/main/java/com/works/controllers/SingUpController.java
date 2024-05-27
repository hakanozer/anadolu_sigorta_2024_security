package com.works.controllers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SingUpController {

    final CustomerService customerService;

    @GetMapping("/singup")
    public String index() {
        return "singup";
    }

    @PostMapping("singup")
    public String singup(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            model.addAttribute("errors", errors);
        }else {
            customerService.saveCustomer(customer);
        }
        model.addAttribute("name", customer.getName());
        return "singup";
    }

}
