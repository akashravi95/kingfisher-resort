//package com.resort.kingfisher.service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.resort.kingfisher.model.Admin;
//import com.resort.kingfisher.model.Customer;
//import com.resort.kingfisher.model.Login;
//import com.resort.kingfisher.model.Owner; // Import the Owner entity
//import com.resort.kingfisher.repo.AdminRepository;
//import com.resort.kingfisher.repo.CustomerRepository;
//import com.resort.kingfisher.repo.LoginRepository;
//import com.resort.kingfisher.repo.OwnerRepository; // Import the Owner repository
//
//@Service
//public class regservice {
//
//    @Autowired
//    private AdminRepository adminRepo;
//    
//    @Autowired
//    private CustomerRepository customerRepo;
//    
//    @Autowired
//    private OwnerRepository ownerRepo; // Inject the Owner repository
//    
//    @Autowired
//    private LoginRepository loginRepo;
//
//    public Customer savecustomer(Customer cust) {
//        Login log = new Login();
//        log.setUsername(cust.getUsername());
//        log.setPassword(cust.getPassword());
//        log.setRole("CUSTOMER");
//        cust.setRole(log.getRole());
//        Customer savedCustomer = customerRepo.save(cust);
//        log.setCustomer(savedCustomer);
//        loginRepo.save(log);
//        return savedCustomer;
//    }
//
//    public Admin saveadmin(Admin adm) {
//        Login log = new Login();
//        log.setUsername(adm.getUsername());
//        log.setPassword(adm.getPassword());
//        log.setRole("ADMIN");
//        adm.setRole(log.getRole());
//        Admin admsaved = adminRepo.save(adm);
//        log.setAdmin(admsaved);
//        loginRepo.save(log);
//        return admsaved;
//    }
//
//    public Owner saveowner(Owner owner) {
//        Login log = new Login();
//        log.setUsername(owner.getUsername());
//        log.setPassword(owner.getPassword());
//        log.setRole("OWNER");
//        owner.setRole(log.getRole());
//        Owner ownerSaved = ownerRepo.save(owner);
//        log.setOwner(ownerSaved);
//        loginRepo.save(log);
//        return ownerSaved;
//    }
//}
