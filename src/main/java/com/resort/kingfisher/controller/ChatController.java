package com.resort.kingfisher.controller;




import com.resort.kingfisher.service.ChatMessageService;
import com.resort.kingfisher.service.CustomerService;
import com.resort.kingfisher.service.OwnerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {
	 @Autowired
	    private ChatMessageService chatMessageService;

	    @Autowired
	    private OwnerService ownerService;
	    @Autowired
	    public CustomerService customerservice;
	   
	
}
