package com.resort.kingfisher.service;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.kingfisher.model.Admin;
import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Login;
import com.resort.kingfisher.model.Owner; // Import the Owner entity

import com.resort.kingfisher.repo.LoginRepository;
// Import the Owner repository

@Service
public class CheckLoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Map<String, Long> checkLogin(Login log) {
        Map<String, Long> map = new HashMap<>();

        String user = log.getUsername();
        String pass = log.getPassword();

        Login customLogin = loginRepository.findByUsername(user);

        if (customLogin != null && customLogin.getPassword().equals(pass)) {
            switch (customLogin.getRole()) {
                case "CUSTOMER":
                    Customer customer = customLogin.getCustomer();
                    map.put("STATUS", 200L);
                    map.put("ROLE", 2L);
                    map.put("ID", customer.getId());
                    break;
                case "ADMIN":
                    Admin admin = customLogin.getAdmin();
                    map.put("STATUS", 200L);
                    map.put("ROLE", 1L);
                    map.put("ID", admin.getId());
                    break;
                case "OWNER": // Handle the OWNER role
                    Owner owner = customLogin.getOwner();
                    map.put("STATUS", 200L);
                    map.put("ROLE", 3L); // Assuming 3 is the role code for OWNER
                    map.put("ID", owner.getId());
                    break;
            }
        } else {
            map.put("STATUS", 505L);
            map.put("ROLE", 3L); // Assuming 3 is the role code for unauthorized access
            map.put("ID", 0L);
        }

        return map;
    }

	
   
}
