package com.works.services;

import com.works.entities.Customer;
import com.works.entities.Role;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final DB db;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(username);
        if (optionalCustomer.isPresent()) {
            Customer c = optionalCustomer.get();
            return new User(
                    c.getEmail(),
                    c.getPassword(),
                    c.getEnabled(),
                    true,
                    true,
                    true,
                    parseRole(c.getRoles())
            );
        }
        throw new UsernameNotFoundException("Username not Found!");
    }

    private Collection<? extends GrantedAuthority> parseRole(List<Role> roles) {
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            for (Role role : roles) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
            }
            return grantedAuthorityList;
    }

    public ResponseEntity register(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent()) {
           return new ResponseEntity("Email in use", HttpStatus.BAD_REQUEST);
        }else {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
            return new ResponseEntity(customer, HttpStatus.OK);
        }
    }


    public ResponseEntity login(Customer customer) {
        try {
            //String sql = "select * from customer where email = '"+ customer.getEmail() +"' and password = '"+ customer.getPassword() +"'";
            //System.out.println(sql);
            // select * from customer where email = 'a@mail.com' and password = '' or 1 = 1 --'
            String sql = "select * from customer where email = ? and password = ?";
            PreparedStatement pre = db.dataSource().getConnection().prepareStatement(sql);
            pre.setString(1, customer.getEmail());
            pre.setString(2, customer.getPassword());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new ResponseEntity("Login Success", HttpStatus.OK);
            }
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Username or Password", HttpStatus.BAD_REQUEST);
    }


}
